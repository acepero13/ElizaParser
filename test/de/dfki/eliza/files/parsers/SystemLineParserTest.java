package de.dfki.eliza.files.parsers;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alvaro on 3/22/17.
 */
public class SystemLineParserTest {
    private SystemLineParser parser;

    @Test
    public void test_parse_InfoNameTheSameWithSystemName_ParsedText() {
        makeFakeParser();
        ((FakeSystemLineParser)parser).systemN = "{Name}";
        String expected = "Hallo welt";
        String line = "{Name}: " + expected;
        parser.parse(line);
        String res = parser.getText();
        assertEquals(expected, res);
    }

    @Test
    public void test_parse_InfoNameDifferentFromSystemName_ParsedText() {
        makeFakeParser();
        ((FakeSystemLineParser)parser).systemN = "{Name}";
        String expected  = "Hallo welt";
        String line = "{dict-entry} " + expected;
        parser.parse(line);
        String res = parser.getText();
        assertEquals(expected, res);
    }

    @Test
    public void test_parse_InfoNameWithoutBracketsAndEqualsToSystemName_ParsedText() {
        makeFakeParser();
        String systemName = "Alvaro";
        ((FakeSystemLineParser)parser).systemN = systemName;
        String expected = "Hallo welt";
        String line = systemName + " " + expected;
        parser.parse(line);
        String res = parser.getText();
        assertEquals(expected, res);
    }

    void makeFakeParser() {
        parser = new FakeSystemLineParser();
    }

    private class FakeSystemLineParser extends SystemLineParser{
        public FakeSystemLineParser(){
            super();
        }
        public String systemN;
        void getSystemName(String line){
            systemName = systemN;
        }
    }
}

