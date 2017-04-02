package de.dfki.eliza.chat;

import de.dfki.eliza.chat.decorators.ChatManagerDecorator;
import de.dfki.eliza.files.exceptions.NoValidConversation;
import de.dfki.eliza.files.models.Conversation;

import java.util.LinkedList;

/**
 * Created by alvaro on 3/24/17.
 */
public class ChatManager implements ChatManagerDecorator {
    private int currentPosition = -1;
    private  LinkedList<Conversation> conversations = new LinkedList<>();

    public ChatManager(LinkedList<Conversation> conversations){
        this.conversations = conversations;
    }

    public ChatManager(ChatManager another){
        this.conversations = another.conversations;


    }

    public int getTotalConversations() {
        return this.conversations.size();
    }


    public Conversation getNextConversation() throws NoValidConversation {
        int oldPosition = currentPosition;
        increasePosition();
        isInvalidPosition(oldPosition);
        Conversation conversation = conversations.get(currentPosition);
        return conversation;
    }

    public void increasePosition() {
        currentPosition++;
    }

    void isInvalidPosition(int oldPosition) throws NoValidConversation {
        if(currentPosition >= getTotalConversations() || currentPosition < 0 ){
            currentPosition = oldPosition;
            throw new NoValidConversation("This is a non valid conversation");
        }
    }

    public Conversation goToConversation(int conversationId) throws NoValidConversation {
        int oldPosition = currentPosition;
        currentPosition = conversationId -1;
        isInvalidPosition(oldPosition);
        return conversations.get(currentPosition);
    }

    public Conversation getPreviousConversation() throws NoValidConversation {
        int oldPosition = currentPosition;
        decreasePosition();
        isInvalidPosition(oldPosition);
        return conversations.get(currentPosition);
    }

    public void decreasePosition() {
        currentPosition--;
    }

    public boolean hastNext() {
        return currentPosition < conversations.size() -1 && currentPosition >= -1  && conversations.size() > 0;
    }

    public boolean hasPrevious() {
        return  currentPosition > 0;
    }

    public int getCurrentPosition(){
        return currentPosition;
    }

    public void reset(){
        currentPosition = -1;
    }
    public void resetToPosition(int position){
        currentPosition = position;
    }


}
