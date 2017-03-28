package de.dfki.eliza.files.models;

import de.dfki.eliza.files.filestystem.Writable;
import de.dfki.eliza.renderer.Renderable;

/**
 * Created by alvaro on 3/14/17.
 */
public class Message implements Textable, Writable, Renderable{
    public static final String PIPE_SEPARATOR = "|";
    private Renderable render;
    private String text;
    private int value = -1;
    private int topic = -1;
    private boolean isUserMessage = false;
    private int assessment = -1;
    private String authorName = "";

    public Message() {

    }

    private Message(Renderable render,  String authorName, String text, int value, int topic){
        this.text = text;
        this.value = value;
        this.topic = topic;
        this.authorName = authorName;
        this.render = render;
    }

    public static Message createAgentMessage(String authorName, Renderable render, String text, int value, int topic){
        return new Message(render, authorName, text, value, topic);
    }

    public static Message createUserMessage(String authorName, Renderable render, String text, int value, int topic, int assessment){
        Message m = new Message(render, authorName, text, value, topic);
        m.setAssessment(assessment);
        return m;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getTopic() {
        return topic;
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }

    @Override
    public void isUser(boolean b) {
        this.isUserMessage = b;
    }

    public boolean isIsUserMessage() {
        return isUserMessage;
    }

    @Override
    public String write() {
        if(getText().isEmpty())
            return "";
        String author = writeAuthorName();
        String textLine = writeTextWithValues();
        return author + textLine;
    }

    String writeTextWithValues() {
        String line = getText() + " " + PIPE_SEPARATOR
                + getTopic() + PIPE_SEPARATOR
                + getValue() + PIPE_SEPARATOR
                + getAssessment() + PIPE_SEPARATOR ;
        return line.trim();
    }

    private String writeAuthorName() {
        return getAuthorName() + " ";
    }

    public int getAssessment() {
        return assessment;
    }

    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }

    @Override
    public void render(int rowPosition, Textable message) {
        this.render.render(rowPosition, message);
    }
}
