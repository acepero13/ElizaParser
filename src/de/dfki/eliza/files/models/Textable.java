package de.dfki.eliza.files.models;

import de.dfki.eliza.renderer.Renderable;

/**
 * Created by alvaro on 3/14/17.
 */
public interface Textable extends Renderable {
    String getText();
    void isUser(boolean b);

    @Override
    void render(int rowPosition, Textable message);
}
