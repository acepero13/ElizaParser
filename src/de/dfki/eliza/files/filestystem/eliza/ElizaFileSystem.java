package de.dfki.eliza.files.filestystem.eliza;


import de.dfki.eliza.files.exceptions.IncorrectFileExtension;
import de.dfki.eliza.files.filestystem.FileSystemReadable;
import org.apache.commons.io.FilenameUtils;

import java.io.*;

/**
 * Created by alvaro on 3/6/17.
 */
public class ElizaFileSystem implements FileSystemReadable {
    private static final String READ_EXTENSION = "txt";
    private static final String WRITE_EXTENSION = "tca";
    private final String filename;
    private File file = null;
    private FileReader fileReader = null;
    private BufferedReader bufferedReader = null;

    public ElizaFileSystem(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean fileExists() {
        file = new File(this.filename);
        return file.exists() && !file.isDirectory();
    }

    @Override
    public String getFileExtension() {
        return FilenameUtils.getExtension(this.filename);
    }

    @Override
    public BufferedReader openFile() throws IncorrectFileExtension, FileNotFoundException {
        checkFileExtension();
        checkFileExistance();
        return getBufferedReader();
    }

    protected BufferedReader getBufferedReader() throws FileNotFoundException {
        fileReader = new FileReader(file);
        bufferedReader = new BufferedReader(fileReader);
        return bufferedReader;
    }

    @Override
    public boolean checkFileExistance() throws FileNotFoundException {
        if (!fileExists()) {
            throw new FileNotFoundException();
        }
        return true;
    }

    @Override
    public boolean checkFileExtension() throws IncorrectFileExtension {
        if (!getFileExtension().equals(READ_EXTENSION) && !getFileExtension().equals(WRITE_EXTENSION)) {
            throw new IncorrectFileExtension();
        }
        return true;
    }

    @Override
    public void close() {
        try {
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getFilename() {
        return this.filename;
    }

}
