package com.sadvit.utils;

import com.sadvit.exceptions.FileDeleteException;
import com.sadvit.exceptions.FileReadException;
import com.sadvit.exceptions.FileWriteException;
import com.sadvit.exceptions.FilesReadException;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by vitaly.sadovskiy.
 */
public class FileUtils {

    public static synchronized List<String> readAllFileNames(String path) {
		File file = new File(path);
		return Arrays.asList(file.list());
	}

    public static synchronized void deleteFile(String path) {
		File file = new File(path);
		if (!file.exists() || !file.delete()) {
			throw new FileDeleteException(path);
		}
	}

    public static synchronized InputStream readFile(String path) {
        try {
            return new FileInputStream(path);
        } catch (IOException e) {
            throw new FileReadException(path);
        }
    }

	public static synchronized BufferedImage readBufferedImage(String path) {
		try	{
			return ImageIO.read(new File(path));
		}
		catch (IOException e) {
			throw new FileReadException(path);
		}
	}

    public static synchronized byte[] readFileAsByteArray(String path) {
        return toByteArray(readFile(path));
    }

    public static synchronized void writeFile(String path, InputStream inputStream) {
        try {
            File file = new File(path);
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

    public static synchronized void writeBufferedImage(String path, BufferedImage bufferedImage) {
        try {
            File file = new File(path);
            if (file.exists()) {
                FileOutputStream outputStream = new FileOutputStream(file);
                ImageIO.write(bufferedImage, "bmp", outputStream);
            } else {
                if (file.createNewFile()) {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    ImageIO.write(bufferedImage, "bmp", outputStream);
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
            throw new RuntimeException("Error converting image");
        }
    }

	public static byte[] toByteArray(BufferedImage bufferedImage) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try	{
			ImageIO.write(bufferedImage, "bmp", baos);
			return baos.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException("Error converting image");
		}
	}

}