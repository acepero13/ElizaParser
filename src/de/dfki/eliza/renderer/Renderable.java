package de.dfki.eliza.renderer;

import de.dfki.eliza.files.models.Textable;

/**
 * Created by alvaro on 3/26/17.
 */
public interface Renderable {
    void render(int rowPosition, Textable message);
}
