package de.dfki.eliza.chat.decorators;

import de.dfki.eliza.chat.ChatManager;
import de.dfki.eliza.files.exceptions.NoValidConversation;
import de.dfki.eliza.files.models.Conversation;

/**
 * Created by alvaro on 4/1/17.
 */
public class DummyAssessmentDecorator implements ChatManagerDecorator {
    @Override
    public int getTotalConversations() {
        return 0;
    }

    @Override
    public Conversation getNextConversation() throws NoValidConversation {
        throw new NoValidConversation("No Valid Conversation");
    }

    @Override
    public Conversation goToConversation(int conversationId) throws NoValidConversation {
        throw new NoValidConversation("No Valid Conversation");
    }

    @Override
    public Conversation getPreviousConversation() throws NoValidConversation {
        throw new NoValidConversation("No Valid Conversation");
    }

    @Override
    public boolean hastNext() {
        return false;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }

    @Override
    public void reset() {

    }
}
