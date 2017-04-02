package de.dfki.eliza.chat.decorators;

import de.dfki.eliza.chat.ChatManager;
import de.dfki.eliza.chat.decorators.assessments.EmptyConversationDecorator;
import de.dfki.eliza.files.exceptions.NoValidConversation;
import de.dfki.eliza.files.models.Conversation;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by alvaro on 4/1/17.
 */
public class EmptyConversationDecoratorTest {
    private EmptyConversationDecorator emptyConversationDecorator;

    @Test
    public void test_getTotalConversations_EmptyList_Zero() {
        ChatManager manager = new ChatManager(new LinkedList<>());
        makeEmptyDecorator(manager);
        int res = emptyConversationDecorator.getTotalConversations();
        assertEquals(0, res);
    }

    @Test
    public void test_getTotalConversations_AllEmptyItemsInChatManager_ChatManagerTotal() {
        LinkedList<Conversation> conversations = makeEmptyAssessmentList();
        ChatManager manager = new ChatManager(conversations);
        makeEmptyDecorator(manager);
        int res = emptyConversationDecorator.getTotalConversations();
        assertEquals(conversations.size(), res);
    }

    private LinkedList<Conversation> makeEmptyAssessmentList() {
        LinkedList<Conversation> conversations = makeNoAssessmentEmptyList(0, 0);
        return conversations;
    }

    @Test
    public void test_getTotalConversations_NoEmptyImtesInChatMAnager_Zero() {
        LinkedList<Conversation> conversations = makeNoAssessmentEmptyList(1, 2);
        ChatManager manager = new ChatManager(conversations);
        makeEmptyDecorator(manager);
        int res = emptyConversationDecorator.getTotalConversations();
        assertEquals(0, res);
    }

    private LinkedList<Conversation> makeNoAssessmentEmptyList(int assesment, int assesment2) {
        LinkedList<Conversation> conversations = new LinkedList<>();
        Conversation c1 = new Conversation();
        c1.setOveralAssesment(assesment);
        Conversation c2 = new Conversation();
        c2.setOveralAssesment(assesment2);
        conversations.add(c1);
        conversations.add(c2);
        return conversations;
    }

    @Test(expected = NoValidConversation.class)
    public void test_getNextConversation_EmptyList_Exception() throws NoValidConversation {
        ChatManager manager = new ChatManager(new LinkedList<>());
        makeEmptyDecorator(manager);
        emptyConversationDecorator.getNextConversation();
    }

    @Test(expected = NoValidConversation.class)
    public void test_getNextConversation_NoEmptyItems_Exception() throws NoValidConversation {
        LinkedList<Conversation> conversations = makeNoAssessmentEmptyList(1, 2);
        ChatManager manager  = new ChatManager(conversations);
        makeEmptyDecorator(manager);
        emptyConversationDecorator.getNextConversation();
    }

    @Test
    public void test_getNextConversation_AllEmptyItems_FirstItemInConversation() throws NoValidConversation {
        LinkedList<Conversation> conversations = makeEmptyAssessmentList();
        ChatManager manager  = new ChatManager(conversations);
        makeEmptyDecorator(manager);
        Conversation res = emptyConversationDecorator.getNextConversation();
        assertEquals(conversations.getFirst(), res);

    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_goToConversation_Any_Exception() throws NoValidConversation {
        ChatManager manager = new ChatManager(new LinkedList<>());
        makeEmptyDecorator(manager);
        emptyConversationDecorator.goToConversation(100);
    }

    private void makeEmptyDecorator(ChatManager manager) {
        emptyConversationDecorator = new EmptyConversationDecorator(manager);
    }

    @Test(expected = NoValidConversation.class)
    public void test_grePrevious_EmptyList_Exception() throws NoValidConversation {
        ChatManager manager  = new ChatManager(new LinkedList<>());
        makeEmptyDecorator(manager);
        emptyConversationDecorator.getPreviousConversation();
    }

    @Test
    public void test_getPreviousConversation_AllEmptyItemsIndexInSecondPosition_getFirstConversation() throws NoValidConversation {
        LinkedList<Conversation> conversations = makeEmptyAssessmentList();
        ChatManager manager = new ChatManager(conversations);
        makeEmptyDecorator(manager);
        emptyConversationDecorator.getNextConversation();
        emptyConversationDecorator.getNextConversation();
        Conversation res = emptyConversationDecorator.getPreviousConversation();
        assertEquals(conversations.getFirst(), res);
    }

    @Test(expected = NoValidConversation.class)
    public void test_getPreviousConversation_NoEmptyItems_Exception() throws NoValidConversation {
        LinkedList<Conversation> conversations = makeNoAssessmentEmptyList(1,2);
        Conversation empty1 = new Conversation();
        Conversation empty2 = new Conversation();
        empty1.setOveralAssesment(0);
        empty2.setOveralAssesment(0);
        conversations.add(empty1);
        conversations.add(empty2);
        ChatManager manager = new ChatManager(conversations);
        makeEmptyDecorator(manager);
        emptyConversationDecorator.getNextConversation();
        emptyConversationDecorator.getPreviousConversation();
    }
}