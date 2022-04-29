package swing;

import dao.Record;
import dao.RecordDaoImpl;
import edu.hitsz.application.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author 一徐
 */
public class Ranking {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JScrollPane tableScrollPane;
    private JTable scoreTable;
    private JButton deleteButton;
    private JLabel headerLabel;
    private JLabel tabLabel;

    private DefaultTableModel model;
    private String[] columnName = {"名次","玩家名","得分","记录时间"};
    private String[][] tableData;
    private RecordDaoImpl recordDao = new RecordDaoImpl();
    private static final Map<Main.GameMode,String> TAB = new EnumMap<Main.GameMode, String>(Main.GameMode.class);
    static {
        TAB.put(Main.GameMode.EASY,"简单模式");
        TAB.put(Main.GameMode.NORMAL,"普通模式");
        TAB.put(Main.GameMode.HARD,"困难模式");
    }

    public Ranking() throws IOException, ClassNotFoundException {
        recordDao.read();
        tabLabel.setText(TAB.get(Main.gameMode));

        //表格模型
        model = new DefaultTableModel(getTableData(),columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        //从表格模型那里获取数据
        scoreTable.setModel(model);
        tableScrollPane.setViewportView(scoreTable);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = scoreTable.getSelectedRow();
                int ans = JOptionPane.showConfirmDialog(mainPanel, "确定要删除该项吗？");
                if (ans == JOptionPane.YES_OPTION) {
                    recordDao.doDelete(row);
                    try {
                        recordDao.store();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //重新绘制排行榜
                    refreshTable();
                }
            }
        });

    }

    private String[][] getTableData(){
        ArrayList<Record> records = recordDao.getAllRecords();
        tableData = new String[records.size()][4];
        for(int i=0;i<records.size();i++){
            int j = i+1;
            tableData[i][0] = String.valueOf(j);
            tableData[i][1] = records.get(i).getName();
            tableData[i][2] = String.valueOf(records.get(i).getScore());
            tableData[i][3] = records.get(i).getTime();
        }
        return tableData;
    }

    /**
     * 更新排行榜
     */
    private void refreshTable(){
        String[][] newData;
        newData = getTableData();
        model.setDataVector(newData,columnName);
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}
