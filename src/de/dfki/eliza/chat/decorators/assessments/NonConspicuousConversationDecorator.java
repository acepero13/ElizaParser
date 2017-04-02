package de.dfki.eliza.chat.decorators.assessments;

import de.dfki.eliza.chat.ChatManager;
import de.dfki.eliza.chat.decorators.AbstractConversationDecorator;
import de.dfki.eliza.files.models.Conversation;

/**
 * Created by alvaro on 4/1/17.
 */
public class NonConspicuousConversationDecorator extends AbstractConversationDecorator {

    private static final int NON_CONSPICUOUS_ASSESSMENT = 2;


    //TODO: ResetPosition

    public NonConspicuousConversationDecorator(ChatManager chatManager) {
        super(chatManager);
    }

    protected boolean isSelectedConditionFulfilled(Conversation conversation) {
        return conversation.getAssesment() == NON_CONSPICUOUS_ASSESSMENT;
    }
}
