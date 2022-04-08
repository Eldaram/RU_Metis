package src.main.java.tests;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.tools.Parsing;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ParseTest {
    @Test
    public void testEmpty() {
        String str = "";
        Assert.assertEquals(0, Parsing.parse(str).size());
    }

    @Test
    public void testNormal() {
        String str = "/Ceci est un test";
        List<String> response = new ArrayList<>();
        response.add("/Ceci");
        response.add("est");
        response.add("un");
        response.add("test");
        List<String> result = Parsing.parse(str);
        Assert.assertEquals(response.size(), result.size());
        for (int i = 0; i < response.size(); i++) {
            Assert.assertEquals(response.get(i), result.get(i));
        }
    }

    @Test
    public void testDoubleSpace() {
        String str = "/Ceci est    un test";
        List<String> response = new ArrayList<>();
        response.add("/Ceci");
        response.add("est");
        response.add("un");
        response.add("test");
        List<String> result = Parsing.parse(str);
        Assert.assertEquals(response.size(), result.size());
        for (int i = 0; i < response.size(); i++) {
            Assert.assertEquals(response.get(i), result.get(i));
        }
    }

    @Test
    public void testQuotes() {
        String str = "/Ceci est un \"test unique \"";
        List<String> response = new ArrayList<>();
        response.add("/Ceci");
        response.add("est");
        response.add("un");
        response.add("test unique ");
        List<String> result = Parsing.parse(str);
        Assert.assertEquals(response.size(), result.size());
        for (int i = 0; i < response.size(); i++) {
            Assert.assertEquals(response.get(i), result.get(i));
        }
    }

    @Test
    public void testQuotesEsape() {
        String str = "/Ceci est un \"test unique\\\" \"";
        List<String> response = new ArrayList<>();
        response.add("/Ceci");
        response.add("est");
        response.add("un");
        response.add("test unique\" ");
        List<String> result = Parsing.parse(str);
        Assert.assertEquals(response.size(), result.size());
        for (int i = 0; i < response.size(); i++) {
            Assert.assertEquals(response.get(i), result.get(i));
        }
    }

    @Test
    public void testEscapeChar() {
        String str = "/Ceci es\\\\t un test";
        List<String> response = new ArrayList<>();
        response.add("/Ceci");
        response.add("es\\t");
        response.add("un");
        response.add("test");
        List<String> result = Parsing.parse(str);
        Assert.assertEquals(response.size(), result.size());
        for (int i = 0; i < response.size(); i++) {
            Assert.assertEquals(response.get(i), result.get(i));
        }
    }
}
