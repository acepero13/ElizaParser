package de.dfki.eliza.files.parsers.factories;

import de.dfki.eliza.files.models.Conversation;

/**
 * Created by alvaro on 3/14/17.
 */
public class ConversationFactory {

    private Conversation conversation;

    private ConversationFactory(){
        conversation = new Conversation();
    }

    private static class InstanceHolder {
        private static final ConversationFactory instance = new ConversationFactory();
    }

    public static ConversationFactory getInstance(){
        return InstanceHolder.instance;
    }

    public Conversation getConversation(){
        return  conversation;
    }

    public Conversation createConversation() {
        this.conversation = new Conversation();
        return this.conversation;
    }
}
