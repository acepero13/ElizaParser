package de.dfki.eliza.files.parsers.dialog;

import de.dfki.eliza.files.builders.ChatParser;
import de.dfki.eliza.files.parsers.factories.ConversationFactory;
import de.dfki.eliza.renderer.DummyRender;
import de.dfki.eliza.renderer.Renderable;

/**
 * Created by alvaro on 3/14/17.
 */
public abstract class Dialog implements ChatParser {
    public static final String INFO_LINE = "info:";
    public static final String USER_NAME = "Sie:";
    public static final String VALUE_TOPIC_SEPARATOR = "\\|";
    public static final int COUNT_PIPE_SEPARATOR_DEPRECATED = 2;
    public static final int COUNT_PIPE_SEPARATOR = 3;
    public static final int VALUE_INDEX = 1;
    public static final int TOPIC_INDEX = 0;
    public static final int ASSESSMENT_INDEX = 2;
    protected   Renderable render;

    protected DialogLineBehavior dialogLine;
    protected ValueLineBehavior valueLine;
    protected abstract boolean parseLine(String line);
    protected ConversationFactory conversationFactory = ConversationFactory.getInstance();
    private Dialog nextParser;

    public Dialog(){
        this.render = new DummyRender();
    }

    public Dialog(Renderable render){
        this.render = render;
    }

    public boolean parse(String line){
        if(parseLine(line)){
            dialogLine.parseText();
            valueLine.parseText();
            postParsed();
            return true;
        }else if(nextParser != null){
            return nextParser.parse(line);
        }
        return false;

    }

    public abstract void postParsed();

    public String getText(){
        return dialogLine.getText();
    }

    public String getCharacterName(){
        return dialogLine.getCharacterName();
    }

    public String getStringAt(int index) {
        return valueLine.getStringAt(index);
    }

    public int getIntAt(int index) {
        return valueLine.getIntAt(index);
    }

    public void setNextParser(Dialog nextParser) {
        this.nextParser = nextParser;
    }
}
