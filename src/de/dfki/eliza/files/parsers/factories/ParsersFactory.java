package de.dfki.eliza.files.parsers.factories;

import de.dfki.eliza.files.models.Conversation;
import de.dfki.eliza.files.parsers.*;
import de.dfki.eliza.files.parsers.dialog.Dialog;
import de.dfki.eliza.renderer.Renderable;

import java.util.LinkedList;

public class ParsersFactory {
    private Dialog firstDialogParser;
    private LinkedList<Conversation> conversations = new LinkedList<>();
    public ParsersFactory() {

    }

    public  NewChatParser createFirstParser(LinkedList<Conversation> conversations){
        this.conversations = conversations;
        return createParser();
    }

    public NewChatParser createFirstParserWithRenders(LinkedList<Conversation> conversations,
                                                      Renderable infoRender, Renderable userRender, Renderable systemRender){
        this.conversations = conversations;
        firstDialogParser = new NewChatParser(getConversations());
        Dialog infoUserDialog = new InfoUserLineParser(infoRender);
        Dialog infoDialog = new InfoLineParser(infoRender);
        Dialog userLine = new UserLineParser(userRender);
        Dialog systemLine = new SystemLineParser(systemRender);
        return buildParsers(infoUserDialog, infoDialog, userLine, systemLine);


    }

    private NewChatParser buildParsers(Dialog infoUserDialog, Dialog infoDialog, Dialog userLine, Dialog systemLine) {
        Dialog defenseStratey = new DefenseStrategyParser();

        systemLine.setNextParser(defenseStratey);
        userLine.setNextParser(systemLine);
        infoDialog.setNextParser(userLine);
        infoUserDialog.setNextParser(infoDialog);
        firstDialogParser.setNextParser(infoUserDialog);
        return (NewChatParser) firstDialogParser;
    }

    private NewChatParser createParser() {
        firstDialogParser = new NewChatParser(getConversations());
        Dialog infoUserDialog = new InfoUserLineParser();
        Dialog infoDialog = new InfoLineParser();
        Dialog userLine = new UserLineParser();
        Dialog systemLine = new SystemLineParser();
        return buildParsers(infoUserDialog, infoDialog, userLine, systemLine);
    }

    public LinkedList<Conversation> getConversations() {
        return conversations;
    }


}