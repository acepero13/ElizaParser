package de.dfki.eliza.chat.decorators.assessments;

import de.dfki.eliza.chat.ChatManager;
import de.dfki.eliza.chat.decorators.AbstractConversationDecorator;
import de.dfki.eliza.files.models.Conversation;

/**
 * Created by alvaro on 4/1/17.
 */
public class ConspicuousConversationDecorator extends AbstractConversationDecorator {
    private static final int CONSPICUOUS_ASSESSMENT = 1;


    //TODO: ResetPosition

    public ConspicuousConversationDecorator(ChatManager chatManager) {
        super(chatManager);
    }

    protected boolean isSelectedConditionFulfilled(Conversation conversation) {
        return conversation.getAssesment() == CONSPICUOUS_ASSESSMENT;
    }
}
