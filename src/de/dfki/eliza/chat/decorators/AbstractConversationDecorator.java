package de.dfki.eliza.chat.decorators;

import de.dfki.eliza.chat.ChatManager;
import de.dfki.eliza.files.exceptions.NoValidConversation;
import de.dfki.eliza.files.models.Conversation;

/**
 * Created by alvaro on 4/1/17.
 */
public abstract class AbstractConversationDecorator implements ChatManagerDecorator {
    protected ChatManager chatManager;
    protected int totalConversations;

    public AbstractConversationDecorator(ChatManager chatManager) {
        initChatManager(chatManager, false);
    }

    public AbstractConversationDecorator(ChatManager chatManager, boolean keepCopy) {
        initChatManager(chatManager, keepCopy);
    }

    private void initChatManager(ChatManager chatManager, boolean keepCopy) {
        if (!keepCopy)
            this.chatManager = new ChatManager(chatManager);
        else
            this.chatManager = chatManager;
    }

    @Override
    public int getTotalConversations() {
        int oldPosition = chatManager.getCurrentPosition();
        totalConversations = 0;
        while (chatManager.hastNext()) {
            countFulfilledConversations();
        }
        chatManager.resetToPosition(oldPosition);
        return totalConversations;
    }

    private void countFulfilledConversations() {
        try {
            countConversations();
        } catch (NoValidConversation noValidConversation) {
            noValidConversation.printStackTrace();
        }
    }

    private void countConversations() throws NoValidConversation {
        Conversation conversation = chatManager.getNextConversation();
        if (isSelectedConditionFulfilled(conversation)) {
            totalConversations++;
        }
    }

    @Override
    public Conversation getNextConversation() throws NoValidConversation {
        while (chatManager.hastNext()) {
            Conversation conversation = findNextConversation();
            if (conversation != null) return conversation;
        }
        throw new NoValidConversation("There is no conversation without assessment");
    }

    private Conversation findNextConversation() throws NoValidConversation {
        Conversation conversation = chatManager.getNextConversation();
        if (isSelectedConditionFulfilled(conversation)) {
            return conversation;
        }
        return null;
    }

    protected abstract boolean isSelectedConditionFulfilled(Conversation conversation);


    @Override
    public Conversation goToConversation(int conversationId) throws NoValidConversation {
        throw new UnsupportedOperationException();
    }

    @Override
    public Conversation getPreviousConversation() throws NoValidConversation {
        while (chatManager.hasPrevious()) {
            Conversation conversation = findPreviousConversation();
            if (conversation != null) return conversation;
        }
        throw new NoValidConversation("There is no conversation without assessment");
    }

    private Conversation findPreviousConversation() throws NoValidConversation {
        Conversation conversation = chatManager.getPreviousConversation();
        if (isSelectedConditionFulfilled(conversation)) {
            return conversation;
        }
        return null;
    }


    @Override
    public boolean hastNext() {
        return chatManager.hastNext();
    }

    @Override
    public boolean hasPrevious() {
        return chatManager.hasPrevious();
    }

    @Override
    public int getCurrentPosition() {
        return chatManager.getCurrentPosition();
    }

    public void reset() {
        chatManager.reset();
    }
}
