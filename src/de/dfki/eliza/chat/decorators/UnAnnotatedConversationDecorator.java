package de.dfki.eliza.chat.decorators;

import de.dfki.eliza.chat.ChatManager;
import de.dfki.eliza.files.models.Conversation;
import de.dfki.eliza.files.models.Message;
import de.dfki.eliza.files.models.Textable;
import de.dfki.eliza.files.models.decorators.MessageDecorator;

import java.util.Iterator;

/**
 * Created by alvaro on 4/1/17.
 */
public class UnAnnotatedConversationDecorator extends AbstractConversationDecorator {
    public UnAnnotatedConversationDecorator(ChatManager chatManager) {

        super(chatManager, true);
    }

    @Override
    protected boolean isSelectedConditionFulfilled(Conversation conversation) {
        MessageDecorator conversationWithMessagesOnly = new MessageDecorator(conversation);
        boolean isAnnotated = true;
        Iterator<Textable> messageIterator = conversationWithMessagesOnly.getMessages().iterator();
        while (isAnnotated && messageIterator.hasNext()){
            isAnnotated = isMessageAnnotated(isAnnotated, messageIterator);
        }
        return !isAnnotated;
    }

    private boolean isMessageAnnotated(boolean isAnnotated, Iterator<Textable> messageIterator) {
        Message textMessage = (Message) messageIterator.next();
        if(textMessage.getTopic() < 0 || textMessage.getValue() < 0){
            isAnnotated = false;
        }
        return isAnnotated;
    }
}
