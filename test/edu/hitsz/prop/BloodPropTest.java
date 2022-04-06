package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BloodPropTest {

    AbstractProp bloodProp;

    @BeforeAll
    static void beforeAll() {
        System.out.println("**--- Executed once before all test methods in this class ---**");
    }

    @BeforeEach
    void setUp() {
        System.out.println("**--- Executed before each test method in this class ---**");
        bloodProp = new BloodProp(100,100,0,5);
    }

    @AfterEach
    void tearDown() {
        System.out.println("**--- Executed after each test method in this class ---**");
        bloodProp = null;
    }

    @Test
    void getPropkind() {
        System.out.println("**--- Test getPropkind method executed ---**");
        assertEquals(1,bloodProp.getPropkind());
    }

    @Test
    void getSpeedY() {
        System.out.println("**--- Test getSpeedY method executed ---**");
        assertEquals(5,bloodProp.getSpeedY());
    }

    @AfterAll
    static void afterAll() {
        System.out.println("**--- Executed once after all test methods in this class ---**");
    }
}