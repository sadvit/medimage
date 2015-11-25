package com.sadvit.utils;

import com.sadvit.exceptions.FileDeleteException;
import com.sadvit.exceptions.FileReadException;
import com.sadvit.exceptions.FileWriteException;
import com.sadvit.exceptions.FilesReadException;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by vitaly.sadovskiy.
 */
public class FileUtils {

    public static synchronized List<String> readAllFileNames(String path) {
        try {
            File file = new ClassPathResource(path).getFile();
            if (file.isDirectory() && file.list().length == 0) {
                return Collections.emptyList();
            } else {
                return Arrays.asList(file.list());
            }
        } catch (IOException e) {
            throw new FilesReadException(path);
        }
    }

    public static synchronized void deleteFile(String path) {
        try {
            File file = new ClassPathResource(path).getFile();
            if (!file.exists() || !file.delete()) {
                throw new FileDeleteException(path);
            }
        } catch (IOException e) {
            throw new FileDeleteException(path);
        }
    }

    public static synchronized InputStream readFile(String path) {
        try {
            return new ClassPathResource(path).getInputStream();
        } catch (IOException e) {
            throw new FileReadException(path);
        }
    }

    public static synchronized byte[] readFileAsByteArray(String path) {
        return toByteArray(readFile(path));
    }

    public static synchronized void writeFile(String path, InputStream inputStream) {
        try {
            File file = new ClassPathResource(path).getFile();
            if (file.exists()) {
                FileOutputStream outputStream = new FileOutputStream(file);
                IOUtils.copy(inputStream, outputStream);
            } else {
                if (file.createNewFile()) {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    IOUtils.copy(inputStream, outputStream);
                } else {
                    throw new FileWriteException(path);
                }
            }
        } catch (IOException e) {
            throw new FileWriteException(path);
        }
    }

    public static byte[] toByteArray(InputStream inputStream) {
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}