package de.dfki.eliza.files.filestystem;

import java.io.IOException;

/**
 * Created by alvaro on 3/26/17.
 */
public interface FileSystemWritable {
    void openOverwriting() throws IOException;
    void openAppend() throws IOException;
    void write(String text) throws IOException;
    void close() throws IOException;
}
