package src.main.java.tests;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.command.ComputeCom;

public class DiceTest {
    @Test
    public void simpleDice() {
        String str;
        Integer test;
        for (int i = 0; i < 100; i++) {
            str = ComputeCom.compute("1d100").result;
            test = Integer.parseInt(str);
            Assert.assertTrue("Error on " + test + ", " + i + ".",
                    1 <= test && 100 >= test);
        }
    }

    @Test
    public void noDice() {
        Assert.assertEquals(ComputeCom.compute(null), "0");
        Assert.assertEquals(ComputeCom.compute(""), "0");
    }

    @Test
    public void computeOnly() {
        Assert.assertEquals(ComputeCom.compute("5+ 5"), "10");
        Assert.assertEquals(ComputeCom.compute("5*25 + 14 -8 /7"), "138");
    }

    @Test
    public void computeAndRoll() {
        for (int i = 0; i < 10; i++) {
            System.out.print(ComputeCom.compute("5 + 1d10") + "\n");
        }
    }
}
