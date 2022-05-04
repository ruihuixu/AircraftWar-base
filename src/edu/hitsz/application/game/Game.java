package edu.hitsz.application.game;

import dao.Record;
import dao.RecordDaoImpl;
import edu.hitsz.aircraft.Observer;
import edu.hitsz.application.HeroController;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.prop.BombProp;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.factory.BossFactory;
import edu.hitsz.factory.EliteFactory;
import edu.hitsz.factory.EnemyFactory;
import edu.hitsz.factory.MobFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public abstract class Game extends JPanel {

    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;

    private final HeroAircraft heroAircraft;
    private final List<AbstractEnemy> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    private final List<AbstractProp> props;
    /**
     * 不同难度下不同的定义项
     * bossNeeded指示是否生成boss机；enemyMaxNumber为敌机同时出现的最大数量；eliteChance表示精英机生成的概率;
     * bossLimit为boss机每次生成的得分阈值；hpAdded为敌机逐渐增加的血量;difficulty为难度系数（初始值为1）
     * 周期（ms)，指示子弹的发射、敌机的产生频率
     */
    protected int enemyMaxNumber;
    protected boolean bossNeeded;
    protected double eliteChance;
    protected int bossLimit;
    protected int hpAdded;
    protected double difficulty = 1;
    protected int cycleDuration;

    private boolean gameOverFlag = false;
    private int score = 0;
    private int time = 0;
    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleTime = 0;
    /**
     * flag指示Boss机的存在，bossNumber指示目前已出现boss机数量；
     */
    private boolean bossFlag = false;
    private int bossNumber = 0;
    /**
     * 建立工厂
     */
    private EnemyFactory enemyFactory;
    /**
     * 播放音乐 线程
     */
    MusicThread backgroundMusic;
    MusicThread bossMusic;
    ExecutorService musicThreadPool = Executors.newCachedThreadPool();

    public Game() {
        heroAircraft = HeroAircraft.getInstance();
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        props = new LinkedList<>();
        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {

        if(Main.soundEffect){
            backgroundMusic = new MusicThread("src/videos/bgm.wav");
            backgroundMusic.setLoop(true);
            musicThreadPool.execute(backgroundMusic);
        }

        initialise();

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;


            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                difficulty = getDifficulty(time);
                // 新敌机产生
                //若无boss机存在，boss敌机会每隔250分产生一架
                if(bossNeeded&&score!=0&&score%bossLimit==0&&!bossFlag){
                    hpAdded = getHpAdded(bossNumber);
                    enemyFactory = new BossFactory();
                    enemyAircrafts.add(enemyFactory.create(hpAdded));
                    bossNumber ++;
                    bossFlag = true;
                    if(Main.soundEffect){
                        bossMusic = new MusicThread("src/videos/bgm_boss.wav");
                        bossMusic.setLoop(true);
                        backgroundMusic.setEnd(true);
                        musicThreadPool.execute(bossMusic);
                    }
                }
                //精英机与普通机的生产
                if (enemyAircrafts.size()< enemyMaxNumber) {
                    double ran1 = Math.random();
                    hpAdded = (int) (difficulty*30-30);
                    if(ran1<(eliteChance*difficulty)){
                        enemyFactory = new MobFactory();
                        enemyAircrafts.add(enemyFactory.create(hpAdded));

                    }else{
                        enemyFactory = new EliteFactory();
                        enemyAircrafts.add(enemyFactory.create(hpAdded));
                    }
                }
                // 飞机射出子弹
                shootAction();
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            //道具移动
            propsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;
                if(Main.soundEffect){
                    backgroundMusic.setEnd(true);
                    if(bossFlag){
                        bossMusic.setEnd(true);
                    }
                    musicThreadPool.execute(new MusicThread("src/videos/game_over.wav"));
                    musicThreadPool.shutdown();
                }
                //控制台打印排行榜
                try {
                    paintScoreRank();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("Game Over!");
                synchronized (Main.OBJECT){
                    Main.OBJECT.notify();
                }
            }

        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    /**
     * 对不同难度下的一些值的不同进行初始化
     */
    protected abstract void initialise();
    /**
     * 得到boss机血量的递增量
     * @param bossNumber 已出现的boss机架数
     * @return hpAdded
     */
    protected abstract int getHpAdded(int bossNumber);
    /**
     * 得到难度系数随时间的改变值
     * @param time
     * @return difficulty
     */
    abstract double getDifficulty(int time);

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        // TODO 敌机射击
        for(AbstractEnemy example:enemyAircrafts) {
            enemyBullets.addAll(example.shoot(example));
        }
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot(heroAircraft));
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractEnemy enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    private void propsMoveAction(){
        for(AbstractProp temp:props){
            temp.forward();
        }
    }

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for (BaseBullet bullet : enemyBullets) {
            if(bullet.notValid()) {
                continue;
            }
            if(bullet.crash(heroAircraft)) {
                //英雄机损失一定生命值
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractEnemy enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if(Main.soundEffect){
                        musicThreadPool.execute(new MusicThread("src/videos/bullet_hit.wav"));
                    }
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
                        score += enemyAircraft.getScore();
                        props.addAll(enemyAircraft.addProp(enemyAircraft));
                        if(enemyAircraft.getKind()==3){
                            //boss机存在状态修改为不存在
                            bossFlag = false;
                            if(Main.soundEffect){
                                bossMusic.setEnd(true);
                                backgroundMusic = new MusicThread("src/videos/bgm.wav");
                                backgroundMusic.setLoop(true);
                                musicThreadPool.execute(backgroundMusic);
                            }
                        }
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // Todo: 我方获得道具，道具生效
        for(AbstractProp temp:props){
            if(temp.notValid()){
                continue;
            }
            if(heroAircraft.crash(temp)){
                temp.vanish();
                if(Main.soundEffect){
                    musicThreadPool.execute(new MusicThread("src/videos/get_supply.wav"));
                }
                //将现存的敌机与敌机炸弹加入观察者序列（boss机除外）
                if(temp.getPropkind()==2){
                    for(AbstractEnemy enemy:enemyAircrafts){
                        if (enemy.getKind() != 3) {
                            ((BombProp)temp).attach((Observer) enemy);
                            score += enemy.getScore();
                        }
                    }
                    for(BaseBullet bullet:enemyBullets){
                        ((BombProp)temp).attach((Observer) bullet);
                    }
                }
                temp.work(heroAircraft);
            }
        }
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 检查英雄机生存
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        props.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     * @param  g
     */
    @Override
    public void paint(Graphics g) {
        Map<Main.GameMode,BufferedImage> backgroundImage = new EnumMap<>(Main.GameMode.class);
        backgroundImage.put(Main.GameMode.EASY, ImageManager.BACKGROUND_IMAGE_EASY);
        backgroundImage.put(Main.GameMode.NORMAL,ImageManager.BACKGROUND_IMAGE_NORMAL);
        backgroundImage.put(Main.GameMode.HARD,ImageManager.BACKGROUND_IMAGE_HARD);
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(backgroundImage.get(Main.gameMode), 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(backgroundImage.get(Main.gameMode), 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，再绘制道具，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        paintImageWithPositionRevised(g, props);
        paintImageWithPositionRevised(g, enemyAircrafts);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }

    /**
     * 画出得分排行榜
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void paintScoreRank() throws IOException, ClassNotFoundException {
        RecordDaoImpl recordDao = new RecordDaoImpl();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        Record record = new Record(dateFormat.format(date),this.score);
        //玩家记录自己的游戏名
        String userName = JOptionPane.showInputDialog("游戏结束，你的得分为" + score + ",\n请输入名字记录得分:");
        if(userName != null){
            if("".equals(userName)){
                userName = "testUserName";
            }
            record.setName(userName);
        }
        recordDao.read();
        recordDao.doAdd(record);
        recordDao.ranking();
        recordDao.store();
        String recordName;
        String recordTime;
        int recordScore;
        System.out.println("***********************\n" +
                "          得分排行榜         \n" +
                "************************");
        for(int i=0;i<recordDao.getAllRecords().size();i++){
            int j = i + 1;
            recordName = recordDao.getAllRecords().get(i).getName();
            recordTime = recordDao.getAllRecords().get(i).getTime();
            recordScore = recordDao.getAllRecords().get(i).getScore();
            System.out.println("第"+j+"名"+": "+recordName+"，"+recordScore+"，"+recordTime);
        }
    }
}
