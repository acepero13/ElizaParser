package de.dfki.eliza.files.models;

import de.dfki.eliza.files.parsers.dialog.Dialog;
import de.dfki.eliza.renderer.DummyRender;
import de.dfki.eliza.renderer.Renderable;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by alvaro on 3/23/17.
 */
public class ConversationTest {
    private Conversation conversation;
    private Renderable render = new DummyRender();

    @Test
    public void test_write_InfoMessageAndUserMessageAndSystemMeesage_WrittenLine() {
        makeConversation();
        Textable info = new Info("Info text", render);
        Textable user = Message.createUserMessage(Dialog.USER_NAME, render, "User Text", 1, 2, 3);
        Textable system = Message.createAgentMessage("{Name}:", render, "System text", 1,2);
        conversation.addMessage(info);
        conversation.addMessage(user);
        conversation.addMessage(system);
        Annotation annotation = new Annotation();
        annotation.setValue(1);
        annotation.setAssesment(3);
        annotation.setPinned();
        conversation.setAnnotation(annotation);
        String expected = "--------------------------\n" +
                "info: Info text\n" +
                "Sie: User Text |2|1|3|\n" +
                "{Name}: System text |2|1|-1|\n" +
                "#1#1#3\n";
        String res = conversation.write();
        assertEquals(expected, res);

    }

    @Test
    public void test_write_InfoMessageAndUserMessageAndSystemMeesageUnpinned_WrittenLine() {
        makeConversation();
        Textable info = new Info("Info text", render);
        Textable user = Message.createUserMessage(Dialog.USER_NAME, render, "User Text", 1, 2, 3);
        Textable system = Message.createAgentMessage("{Name}:", render, "System text", 1,2);
        conversation.addMessage(info);
        conversation.addMessage(user);
        conversation.addMessage(system);
        Annotation annotation = new Annotation();
        annotation.setValue(1);
        annotation.setAssesment(3);
        annotation.unsetPinned();
        conversation.setAnnotation(annotation);
        String expected = "--------------------------\n" +
                "info: Info text\n" +
                "Sie: User Text |2|1|3|\n" +
                "{Name}: System text |2|1|-1|\n" +
                "#1#0#3\n";
        String res = conversation.write();
        assertEquals(expected, res);

    }

    @Test
    public void test_render_InfoRenderAndUserRender_TextRendered() {
        makeConversation();
        String text = "Info text";
        InfoRender infoRender = new InfoRender(text);
        Textable info = new Info(text, infoRender);
        String systemText = "System text";
        SystemRender systemRender = new SystemRender(systemText);
        Textable system = Message.createAgentMessage("{Name}:", systemRender, systemText, 1,2);
        conversation.addMessage(info);
        conversation.addMessage(system);
        conversation.render();
        assertTrue(infoRender.text.contains("@@"));
        assertTrue(systemRender.text.contains("##"));
    }

    void makeConversation() {
        conversation = new Conversation();
    }

    private class InfoRender implements Renderable{
        public String text;
        public InfoRender(String text){
            this.text = text;
        }
        @Override
        public void render(int rowPosition, Textable message) {
            this.text = "@@Info" + this.text + "@@";
        }
    }

    private class SystemRender implements Renderable{
        public String text;
        public SystemRender(String text){
            this.text = text;
        }
        @Override
        public void render(int rowPosition, Textable message) {
            this.text = "##Info" + this.text + "##";
        }
    }
}