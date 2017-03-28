package de.dfki.eliza.files.models;

import de.dfki.eliza.files.filestystem.Writable;
import de.dfki.eliza.files.parsers.InfoUserLineParser;
import de.dfki.eliza.renderer.Renderable;

/**
 * Created by alvaro on 3/14/17.
 */
public class Info implements Textable, Writable, Renderable {
    private final Renderable render;
    private String text;
    public Info(String text, Renderable render){
        this.render = render;
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void isUser(boolean b) {
        
    }

    @Override
    public String write() {
        String line = "";
        if(!getText().isEmpty()){
            line = InfoUserLineParser.INFO_LINE + " " + getText();
        }
        return line;
    }

    @Override
    public void render(int rowPosition, Textable message) {
        this.render.render(rowPosition, message);
    }
}
