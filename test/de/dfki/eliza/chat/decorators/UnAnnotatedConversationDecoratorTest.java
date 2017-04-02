package de.dfki.eliza.chat.decorators;

import de.dfki.eliza.chat.ChatManager;
import de.dfki.eliza.files.exceptions.NoValidConversation;
import de.dfki.eliza.files.models.Conversation;
import de.dfki.eliza.files.models.Info;
import de.dfki.eliza.files.models.Message;
import de.dfki.eliza.files.models.Textable;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by alvaro on 4/1/17.
 */
public class UnAnnotatedConversationDecoratorTest {
    private UnAnnotatedConversationDecorator unAnnotatedConversation;

    @Test
    public void test_getTotalConversations_EmptyList_Zero() {
        ChatManager manager = makeEmptyManager();
        makeDecorator(manager);
        int res = manager.getTotalConversations();
        assertEquals(0, res);
    }

    @Test
    public void test_getTotalConversations_ListWithAllItemsHaveValueAndTopicHenceAnnotated_Zero() {
        ChatManager manager = new ChatManager(makeAllAnnotatedList());
        makeDecorator(manager);
        int res = unAnnotatedConversation.getTotalConversations();
        assertEquals(0, res);
    }

    @Test
    public void test_getTotalConversations_ListWithTwoNoItemsAnnotated_Two() {
        ChatManager manager = new ChatManager(makeAllUnannotatedList());
        makeDecorator(manager);
        int res = unAnnotatedConversation.getTotalConversations();
        assertEquals(2, res);

    }

    @Test(expected = NoValidConversation.class)
    public void test_getNextConversation_ListWithAllItemsAnnotated_Exception() throws NoValidConversation {
        ChatManager manager = new ChatManager(makeAllAnnotatedList());
        makeDecorator(manager);
        unAnnotatedConversation.getNextConversation();
    }

    @Test
    public void test_getNextConversation_ListWithAllItemsUnAnnotated_getFirst() throws NoValidConversation {
        LinkedList<Conversation> conversations = makeAllUnannotatedList();
        ChatManager manager = new ChatManager(conversations);
        makeDecorator(manager);
        Conversation res = unAnnotatedConversation.getNextConversation();
        assertEquals(conversations.getFirst(), res);
    }

    @Test
    public void test_getPrevious_ListWithAllItemsUnAnnotatedMotToLast_getFirst() throws NoValidConversation {
        LinkedList<Conversation> conversations = makeAllUnannotatedList();
        ChatManager manager = new ChatManager(conversations);
        makeDecorator(manager);
        unAnnotatedConversation.getNextConversation();
        unAnnotatedConversation.getNextConversation();
        Conversation res = unAnnotatedConversation.getPreviousConversation();
        assertEquals(conversations.getFirst(), res);
    }

    @Test(expected = NoValidConversation.class)
    public void test_getNextConversation_ListWithAllItemsAnnotatedAndAnInfoText_Exception() throws NoValidConversation {
        LinkedList<Conversation> conversations = makeAllAnnotatedList();
        Conversation c3 = new Conversation();
        Textable info = new Info("Text", null);
        c3.addMessage(info);
        conversations.add(c3);
        ChatManager manager = new ChatManager(conversations);
        makeDecorator(manager);
        unAnnotatedConversation.getNextConversation();

    }
    //Todo: Anyadir info as textable

    private LinkedList<Conversation> makeAllUnannotatedList() {
        LinkedList<Conversation> conversations = new LinkedList<>();
        Conversation c1 = new Conversation();
        Conversation c2 = new Conversation();
        Message m1 = new Message();
        Message m2 = new Message();
        c1.addMessage(m1);
        c2.addMessage(m2);
        conversations.add(c1);
        conversations.add(c2);
        return conversations;
    }

    private LinkedList<Conversation> makeAllAnnotatedList() {
        LinkedList<Conversation> conversations = new LinkedList<>();
        Conversation c1 = new Conversation();
        Conversation c2 = new Conversation();
        Message m1 = new Message();
        Message m2 = new Message();
        m1.setValue(1);
        m1.setTopic(1);
        m2.setTopic(2);
        m2.setValue(2);
        c1.addMessage(m1);
        c2.addMessage(m2);
        conversations.add(c1);
        conversations.add(c2);
        return conversations;


    }

    private ChatManager makeEmptyManager() {
        return new ChatManager(new LinkedList<>());
    }

    private void makeDecorator(ChatManager manager) {
        unAnnotatedConversation = new UnAnnotatedConversationDecorator(manager);
    }

}