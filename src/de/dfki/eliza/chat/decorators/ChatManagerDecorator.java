package de.dfki.eliza.chat.decorators;

import de.dfki.eliza.files.exceptions.NoValidConversation;
import de.dfki.eliza.files.models.Conversation;

/**
 * Created by alvaro on 4/1/17.
 */
public interface ChatManagerDecorator {
    int getTotalConversations();
    Conversation getNextConversation() throws NoValidConversation;
    Conversation goToConversation(int conversationId) throws NoValidConversation;
    Conversation getPreviousConversation() throws NoValidConversation;
    boolean hastNext();
    boolean hasPrevious();
    int getCurrentPosition();
    void reset();
}
