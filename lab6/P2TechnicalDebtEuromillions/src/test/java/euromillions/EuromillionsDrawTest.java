package euromillions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class EuromillionsDrawTest {

    private CuponEuromillions sampleCuppon;


    @BeforeEach
    public void setUp() throws Exception {
        sampleCuppon = new CuponEuromillions();
        sampleCuppon.addDipToCuppon(Dip.generateRandomDip());
        sampleCuppon.addDipToCuppon(Dip.generateRandomDip());
        sampleCuppon.addDipToCuppon(new Dip(new int[]{1, 2, 3, 48, 49}, new int[]{1, 9}));
    }


    @Test
    public void testFindMatches() {
        Dip expected, actual;

        // test for perfect match on the 3rd dip
        expected = sampleCuppon.getDipByIndex(2);
        EuromillionsDraw testDraw = new EuromillionsDraw(expected);
        actual = testDraw.findMatches(sampleCuppon).get(2);

        assertEquals(sampleCuppon.countDips(), testDraw.findMatches(sampleCuppon).size());
        assertEquals(expected, actual);

        // test for no matches on the 3rd dip
        testDraw = new EuromillionsDraw(new Dip(new int[]{9, 10, 11, 12, 13}, new int[]{2, 3}));
        expected = new Dip(); // should return an empty Dip, since there are no matches
        actual = testDraw.findMatches(sampleCuppon).get(2);
        assertEquals(sampleCuppon.countDips(), testDraw.findMatches(sampleCuppon).size());
        assertEquals(expected, actual);
    }

}
