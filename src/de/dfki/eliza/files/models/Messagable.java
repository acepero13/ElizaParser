package de.dfki.eliza.files.models;

import java.util.LinkedList;

/**
 * Created by alvaro on 4/2/17.
 */
public interface Messagable {
    LinkedList<Textable> getMessages();
}
