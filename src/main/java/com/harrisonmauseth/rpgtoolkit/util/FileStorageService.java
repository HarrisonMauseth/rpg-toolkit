package com.harrisonmauseth.rpgtoolkit.util;

import com.harrisonmauseth.rpgtoolkit.exception.FileStorageException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * FileStorageService provides static utility methods for handling File I/O.
 */
public class FileStorageService {

    public static void log(String content, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename, true))) {
            writer.println(content);
        } catch (FileNotFoundException e) {
            throw new FileStorageException("Cannot locate file: " + filename);
        }
    }

    public static List<String> readContentsOfFile(String filename) {
        List<String> fileContents = new ArrayList<>();
        try (Scanner inboundFile = new Scanner(new File(filename))) {

        } catch (FileNotFoundException e) {
            throw new FileStorageException("Cannot locate file: " + filename);
        }

        return fileContents;
    }

}
