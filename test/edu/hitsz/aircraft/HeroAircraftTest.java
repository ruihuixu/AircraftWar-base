package edu.hitsz.aircraft;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {

    HeroAircraft heroAircraft;

    @BeforeAll
    static void beforeAll() {
        System.out.println("**--- Executed once before all test methods in this class ---**");
    }

    @BeforeEach
    void setUp() {
        System.out.println("**--- Executed before each test method in this class ---**");
        heroAircraft = HeroAircraft.getInstance();
    }

    @AfterEach
    void tearDown() {
        System.out.println("**--- Executed after each test method in this class ---**");
        heroAircraft = null;
    }

    @Test
    void decreaseHp() {
        System.out.println("**--- Test decreaseHp method executed ---**");
        heroAircraft.decreaseHp(40);
        assertEquals(60,heroAircraft.getHp());
    }

    @Test
    void getKind() {
        System.out.println("**--- Test getKind method executed ---**");
        assertEquals(0,heroAircraft.getKind());
    }

    @AfterAll
    static void afterAll() {
        System.out.println("**--- Executed once after all test methods in this class ---**");
    }

}