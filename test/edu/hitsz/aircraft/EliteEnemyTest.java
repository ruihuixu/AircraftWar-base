package edu.hitsz.aircraft;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class EliteEnemyTest {

    EliteEnemy eliteEnemy;

    @BeforeAll
    static void beforeAll() {
        System.out.println("**--- Executed once before all test methods in this class ---**");
    }

    @BeforeEach
    void setUp() {
        System.out.println("**--- Executed before each test method in this class ---**");
        eliteEnemy = new EliteEnemy(100,100,0,0,100);
    }

    @AfterEach
    void tearDown() {
        System.out.println("**--- Executed after each test method in this class ---**");
        eliteEnemy = null;
    }

    @Test
    void getHp() {
        System.out.println("**--- Test getHp method executed ---**");
        assertEquals(100,eliteEnemy.getHp());
    }

    @Test
    void crash() {
        System.out.println("**--- Test crash method executed ---**");
        HeroAircraft heroAircraft = HeroAircraft.getInstance();
        assertFalse(eliteEnemy.crash(heroAircraft));
    }

    @Test
    void getLocationX() {
        System.out.println("**--- Test getLocationX method executed ---**");
        assertEquals(100,eliteEnemy.getLocationX());
    }

    @Test
    void getLocationY() {
        System.out.println("**--- Test getLocationY method executed ---**");
        assertEquals(100,eliteEnemy.getLocationY());
    }

    @AfterAll
    static void afterAll() {
        System.out.println("**--- Executed once after all test methods in this class ---**");
    }

}