package de.dfki.eliza.renderer;

import de.dfki.eliza.files.models.Textable;

/**
 * Created by alvaro on 3/28/17.
 */
public class DummyRender implements Renderable {
    @Override
    public void render(int rowPosition, Textable message) {
        //Do nothing
    }
}
