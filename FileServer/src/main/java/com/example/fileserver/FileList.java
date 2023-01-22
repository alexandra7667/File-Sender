/**
 * This class stores file path and file name of all files added by the server user.
 *
 * @author Alexandra Härnström
 * @version 1
 */

package com.example.fileserver;

import java.io.File;
import java.util.ArrayList;

public class FileList {
    private ArrayList<File> files;

    public FileList() {
        files = new ArrayList<>();
    }

    /**
     * This method adds a new File to the list 'files'
     * @param file - The file to be added
     */
    public void addFile(File file) {
        files.add(file);
    }

    /**
     * This method returns all files the user has added
     * @return - All added files
     */
    public ArrayList<File> getFiles() {
        return files;
    }
}
