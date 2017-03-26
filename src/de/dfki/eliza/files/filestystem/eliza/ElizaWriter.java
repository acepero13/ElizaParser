package de.dfki.eliza.files.filestystem.eliza;

import de.dfki.eliza.files.filestystem.FileSystemWritable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by alvaro on 3/26/17.
 */
public class ElizaWriter implements FileSystemWritable{
    private final File file;
    private FileWriter writer;

    public ElizaWriter(String filename){
        this.file = new File(filename);
    }

    @Override
    public void openOverwriting() throws IOException {
        writer = new FileWriter(file, false);

    }

    @Override
    public void openAppend() throws IOException {
        writer = new FileWriter(file, true);
    }


    @Override
    public void write(String line) throws IOException {
        writer.write(line);
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }


}
