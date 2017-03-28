package de.dfki.eliza.files.models;

import de.dfki.eliza.files.filestystem.Writable;
import de.dfki.eliza.renderer.Renderable;

import java.util.LinkedList;

/**
 * Created by alvaro on 3/14/17.
 */
public class Conversation implements Writable {
    public static final String CHAT_INIT_MARKER = "--------------------------";
    public static final String ANNOTATION_SEPARATOR = "#";
    public static final String NEW_LINE_SEPARATOR = "\n";
    private LinkedList<Textable> messages = new LinkedList<>();
    private Annotation annotation = new Annotation();
    private String systemName  = "{Name}";
    public void addMessage(Textable m){
        messages.add(m);
    }

    public int getTotalMessages(){
        return messages.size();
    }


    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public LinkedList<Textable> getMessages(){
        return messages;
    }

    @Override
    public String write() {
        String line = CHAT_INIT_MARKER + NEW_LINE_SEPARATOR;
        line = writeMessages(line);
        line += writeAnnotations();
        return line + NEW_LINE_SEPARATOR;
    }

    String writeAnnotations() {
        return ANNOTATION_SEPARATOR + annotation.getValue()
                + ANNOTATION_SEPARATOR + annotation.getPinnedString()
                + ANNOTATION_SEPARATOR + annotation.getAssesment();
    }

    String writeMessages(String line) {
        StringBuilder lineBuilder = new StringBuilder(line);
        for (Textable message : messages) {
            Writable writableMessage = (Writable) message;
            lineBuilder.append(writableMessage.write());
            lineBuilder.append(NEW_LINE_SEPARATOR);
        }
        line = lineBuilder.toString();
        return line;
    }

    public int getDefenseStrategy() {
        return annotation.getValue();
    }

    public int getAssesment() {
        return annotation.getAssesment();
    }

    public boolean isPinned() {
        return annotation.isPinned();
    }

    public void setDefenseStrategy(int defenseStrategy) {
        annotation.setValue(defenseStrategy);
    }

    public void setOveralAssesment(int assesment) {
        annotation.setAssesment(assesment);
    }

    public void setPinned(boolean b) {
        if(b)
            annotation.setPinned();
        else
            annotation.unsetPinned();
    }

    public Message getMessageAt(int pos) {
        if(pos >= 0 && pos < messages.size()){
            return (Message) messages.get(pos);
        }
        return new Message();

    }



    public void render(int rowPosition) {
        for (Renderable message: messages   ) {
            message.render(0, (Textable) message);
        }
    }
}