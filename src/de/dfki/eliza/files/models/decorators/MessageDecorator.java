package de.dfki.eliza.files.models.decorators;

import de.dfki.eliza.files.models.Conversation;
import de.dfki.eliza.files.models.Messagable;
import de.dfki.eliza.files.models.Message;
import de.dfki.eliza.files.models.Textable;

import java.util.LinkedList;

/**
 * Created by alvaro on 4/2/17.
 */
public class MessageDecorator implements Messagable {
    private Messagable conversation;
    private LinkedList<Textable> messages;

    public MessageDecorator(Conversation conversation) {
        this.conversation = conversation;
    }

    @Override
    public LinkedList<Textable> getMessages() {
        messages = new LinkedList<>();
        searchForMessages();
        return messages;
    }

    private void searchForMessages() {
        for (Textable text: conversation.getMessages() ) {
            addMessageIfPossible(text);
        }
    }

    private void addMessageIfPossible(Textable text) {
        if(text instanceof Message){
            messages.add(text);
        }
    }
}
