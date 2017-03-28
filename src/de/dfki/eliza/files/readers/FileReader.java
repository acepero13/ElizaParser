package de.dfki.eliza.files.readers;


import de.dfki.eliza.files.exceptions.IncorrectFileExtension;
import de.dfki.eliza.files.filestystem.FileSystemReadable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Created by alvaro on 3/6/17.
 * Read files txt from the chat de.dfki.eliza
 */
public abstract class FileReader implements Readable {
    protected String filename = null;
    protected FileSystemReadable fileSystem = null;
    private BufferedReader bufferedReader = null;

    @Override
    public boolean open(String filname) {
        this.filename = filname;
        return open();
    }

    @Override
    public boolean open() {
        try {
            bufferedReader = fileSystem.openFile();
        } catch (IncorrectFileExtension | FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void read() {
        try {
            readLineByLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readLineByLine() throws IOException {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            parse(line);
        }
    }

    @Override
    public abstract void parse(String line);
}
