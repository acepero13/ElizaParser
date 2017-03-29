package de.dfki.eliza.files.parsers;

import de.dfki.eliza.files.models.Message;
import de.dfki.eliza.files.models.Textable;
import de.dfki.eliza.files.parsers.dialog.Dialog;
import de.dfki.eliza.files.parsers.dialog.DialogLine;
import de.dfki.eliza.files.parsers.dialog.ValueLine;
import de.dfki.eliza.files.parsers.factories.ConversationFactory;
import de.dfki.eliza.files.utils.nameparser.InfoNameParser;
import de.dfki.eliza.files.utils.nameparser.system.BracketsNameParser;
import de.dfki.eliza.renderer.Renderable;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by alvaro on 3/13/17.
 */
public class SystemLineParser extends Dialog {

    String systemName = "{Name}";
    private LinkedList<InfoNameParser> nameParsers = new LinkedList<>();

    public SystemLineParser(Renderable systemRender) {
        super(systemRender);
        initParsers();

    }

    public SystemLineParser() {
        super();
        initParsers();
    }

    private void initParsers() {
        BracketsNameParser bracketsNameParser = new BracketsNameParser();
        nameParsers.add(bracketsNameParser);
    }

    @Override
    public boolean parseLine(String line) {
        getSystemName(line);
        checkSystemName(line);
        IntSeparatorParser parser = new IntSeparatorParser(VALUE_TOPIC_SEPARATOR, line, COUNT_PIPE_SEPARATOR_DEPRECATED);
        valueLine = new ValueLine(parser);
        dialogLine = new DialogLine(line, systemName);
        return line.startsWith(systemName);

    }

    private void checkSystemName(String line) {
        Iterator<InfoNameParser> parserIterator = nameParsers.iterator();
        boolean parsed = false;
        InfoNameParser parser = null;
        while (!parsed && parserIterator.hasNext() ) {
            parser = parserIterator.next();
            parsed = parser.parse(line);
        }
        updateSystemName(parsed, parser);
    }

    void getSystemName(String line) {
        systemName = ConversationFactory.getInstance().getConversation().getSystemName();
    }


    private void updateSystemName(boolean parsed, InfoNameParser parser) {
        if (hasDiferentNameFromInfoLine(parsed, parser)) {
            systemName = parser.getName();
        }
    }

    private boolean hasDiferentNameFromInfoLine(boolean parsed, InfoNameParser parser) {
        return parsed && parser != null && !parser.getName().equals(systemName);
    }

    @Override
    public void postParsed() {
        String authorName = systemName + ":";
        Textable m = Message.createAgentMessage(authorName, render, dialogLine.getText(),
                valueLine.getIntAt(Dialog.VALUE_INDEX),
                valueLine.getIntAt(Dialog.TOPIC_INDEX));
        conversationFactory.getConversation().addMessage(m);
    }


}
