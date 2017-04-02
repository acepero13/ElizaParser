package de.dfki.eliza.chat.decorators.factories;

import de.dfki.eliza.chat.ChatManager;
import de.dfki.eliza.chat.decorators.ChatManagerDecorator;
import de.dfki.eliza.chat.decorators.DummyAssessmentDecorator;
import de.dfki.eliza.chat.decorators.assessments.ConspicuousConversationDecorator;
import de.dfki.eliza.chat.decorators.assessments.EmptyConversationDecorator;
import de.dfki.eliza.chat.decorators.assessments.NonConspicuousConversationDecorator;

/**
 * Created by alvaro on 4/1/17.
 */
public class ChatManagerAbstractFactory {
    public static ChatManagerDecorator createChatManager(int itemIndex, ChatManager chatManager){
        ChatManagerDecorator decoratedChatManager;
        if (itemIndex == 0) {
            decoratedChatManager = new EmptyConversationDecorator(chatManager);
        } else if (itemIndex == 1) {
            decoratedChatManager = new ConspicuousConversationDecorator(chatManager);

        } else if (itemIndex == 2) {
            decoratedChatManager = new NonConspicuousConversationDecorator(chatManager);
        }else{
            decoratedChatManager = new DummyAssessmentDecorator();
        }
        return decoratedChatManager;
    }
}
