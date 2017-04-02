package de.dfki.eliza.chat.decorators.assessments;

import de.dfki.eliza.chat.ChatManager;
import de.dfki.eliza.chat.decorators.AbstractConversationDecorator;
import de.dfki.eliza.files.models.Conversation;

/**
 * Created by alvaro on 4/1/17.
 */
public class EmptyConversationDecorator extends AbstractConversationDecorator {

    public static final int EMPTY_ASSESSMENT = 0;

    //TODO: ResetPosition
    public EmptyConversationDecorator(ChatManager chatManager) {
        super(chatManager);
    }

    protected boolean isSelectedConditionFulfilled(Conversation conversation) {
        return conversation.getAssesment() == EMPTY_ASSESSMENT;
    }





}
