package de.dfki.eliza.files.models;

import de.dfki.eliza.renderer.DummyRender;
import de.dfki.eliza.renderer.Renderable;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by alvaro on 3/23/17.
 */
public class MessageTest {
    private Message message;

    @Test
    public void test_write_AgentGoodLine_WrittenLine() {
        String text = "Hello world";
        Renderable render = makeDummyRender();
        message = Message.createAgentMessage("{Name}:", render, text, 2, 1);
        String expected = "{Name}: " + text + " |1|2|-1|";
        String res = message.write();
        assertEquals(expected, res);

    }

    @Test
    public void test_write_UserGoodLine_WrittenLine() {
        String text = "Hello world";
        Renderable render = makeDummyRender();
        message = Message.createUserMessage("Sie:", render, text, 2, 1, 5);
        String expected = "Sie: " + text + " |1|2|5|";
        String res = message.write();
        assertEquals(expected, res);
    }

    @Test
    public void test_write_EmptyLine_Empty() {
        String text = "";
        Renderable render = makeDummyRender();
        message = Message.createAgentMessage("{Name}:", render, text, 2, 1);
        String expected = "";
        String res = message.write();
        assertEquals(expected, res);
    }

    private Renderable makeDummyRender() {
        return new DummyRender();
    }
}