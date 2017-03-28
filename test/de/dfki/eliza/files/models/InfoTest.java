package de.dfki.eliza.files.models;

import de.dfki.eliza.renderer.DummyRender;
import de.dfki.eliza.renderer.Renderable;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alvaro on 3/23/17.
 */
public class InfoTest {
    private Info info;


    @Test
    public void test_write_NormalTexLine_LineWithPrefixInfo() {
        String text = "Hello world, how are you doing?";
        makeInfo(text);
        String res = info.write();
        String expected = "info: "+ text;
        assertEquals(expected, res);
    }

    @Test
    public void test_write_EmptyText_Empty() {
        String text = "";
        makeInfo(text);
        String res = info.write();
        assertEquals("", res);
    }

    void makeInfo(String text) {
        Renderable render = new DummyRender();
        info = new Info(text, render);
    }

}