package de.dfki.eliza.files.models.decorators;

import de.dfki.eliza.files.models.Conversation;
import de.dfki.eliza.files.models.Info;
import de.dfki.eliza.files.models.Message;
import de.dfki.eliza.files.models.Textable;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by alvaro on 4/2/17.
 */
public class OnyMessageDecoratorTest {
    private MessageDecorator decorator;
    @Test
    public void test_getMessage_EmptyMessageList_Empty() {
        Conversation conversation = new Conversation();
        //LinkedList<Textable>
        makeDecorator(conversation);
        LinkedList<Textable> res = decorator.getMessages();
        assertEquals(0, res.size());
    }

    @Test
    public void test_getMessages_AllItemsAreMessage_AllItems() {
        Conversation conversation = makeConversationWithTwoMessages();
        makeDecorator(conversation);
        LinkedList<Textable> res = decorator.getMessages();
        assertEquals(2, res.size());
    }

    @Test
    public void test_getMessages_TwoItemsMessageOneItemInfo_TwoItems() {
        Conversation conversation = makeConversationWithTwoMessages();
        conversation.addMessage(new Info("", null));
        makeDecorator(conversation);
        LinkedList<Textable> res = decorator.getMessages();
        assertEquals(2, res.size());
    }

    private Conversation makeConversationWithTwoMessages() {
        Conversation conversation = new Conversation();
        conversation.addMessage(new Message());
        conversation.addMessage(new Message());
        return conversation;
    }


    private void makeDecorator(Conversation conversation) {
        decorator  = new MessageDecorator(conversation);
    }


}