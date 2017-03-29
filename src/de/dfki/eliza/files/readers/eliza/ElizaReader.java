package de.dfki.eliza.files.readers.eliza;

import de.dfki.eliza.files.filestystem.FileSystemReadable;
import de.dfki.eliza.files.filestystem.Writable;
import de.dfki.eliza.files.models.Conversation;
import de.dfki.eliza.files.parsers.*;
import de.dfki.eliza.files.parsers.dialog.Dialog;
import de.dfki.eliza.files.parsers.factories.ParsersFactory;
import de.dfki.eliza.files.readers.FileReader;
import de.dfki.eliza.renderer.Renderable;

import java.util.LinkedList;

/**
 * Created by alvaro on 3/13/17.
 */
public class ElizaReader extends FileReader implements Writable {

    LinkedList<Conversation> conversations = new LinkedList<>();
    private Dialog firstDialogParser;

    public ElizaReader(FileSystemReadable fs) {
        ParsersFactory factory = init(fs);
        firstDialogParser = factory.createFirstParser(conversations);
    }

    public ElizaReader(FileSystemReadable fs, Renderable infoRender, Renderable userRender, Renderable systemRender) {
        ParsersFactory factory = init(fs);
        firstDialogParser = factory.createFirstParserWithRenders(conversations, infoRender, userRender, systemRender);
    }

    private ParsersFactory init(FileSystemReadable fs) {
        fileSystem = fs;
        filename = fs.getFilename();
        return new ParsersFactory();
    }


    @Override
    public void parse(String line) {
        firstDialogParser.parse(line);
    }

    public int getTotalConversations(){
        return conversations.size();
    }

    public LinkedList<Conversation> getConversations(){
        return conversations;
    }

    @Override
    public String write() {
        StringBuilder line = new StringBuilder();
        for (Writable conversation: conversations ) {
            line.append(conversation.write());
        }
        return line.toString();
    }
}
