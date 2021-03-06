package de.dfki.eliza.files.parsers;

import de.dfki.eliza.files.models.Info;
import de.dfki.eliza.files.models.Textable;
import de.dfki.eliza.files.parsers.dialog.Dialog;
import de.dfki.eliza.files.parsers.dialog.DialogLine;
import de.dfki.eliza.files.parsers.dialog.NoValueLine;
import de.dfki.eliza.renderer.Renderable;

/**
 * Created by alvaro on 3/13/17.
 */
public class InfoLineParser extends Dialog {
    public InfoLineParser(Renderable infoRender) {
        super(infoRender);
    }

    public InfoLineParser() {
        super();
    }

    @Override
    public boolean parseLine(String line) {
        valueLine = new NoValueLine();
        dialogLine = new DialogLine(line, Dialog.INFO_LINE);
        return line.startsWith(Dialog.INFO_LINE);
    }

    @Override
    public void postParsed() {
        Textable info = new Info(dialogLine.getText(), render);
        conversationFactory.getConversation().addMessage(info);
    }


}
