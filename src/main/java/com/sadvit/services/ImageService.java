package com.sadvit.services;

import com.sadvit.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * Created by sadvit on 23.11.15.
 */
@Service
public class ImageService {

	@Value("${medimage.content}")
    private String content;

    private static final String IMAGES = "images";

    protected static final String SEPARATOR = "/";

    @Autowired
    private UserService userService;

    public InputStream getImage(String id) {
        return null; // FileUtils.readFile(getFilePath(id));
    }

	public BufferedImage getBufferedImage(String id) {
		return null; // FileUtils.readBufferedImage(getFilePath(id));
	}

    public byte[] getImageAsByteArray(String id, String name) {
        return FileUtils.readFileAsByteArray(getFilePath(id, name));
    }

    public List<String> getAllImageNames() {
        return null; // FileUtils.readAllFileNames(getFolderPath());
    }

    public void deleteImage(String id) {
        //FileUtils.deleteFile(getFilePath(id));
    }

    public String saveImage(MultipartFile multipartFile) {
        try
        {
            InputStream inputStream = multipartFile.getInputStream();
            String id = UUID.randomUUID().toString();
            //FileUtils.writeFile(getFilePath(id), inputStream);
            return id;
        }
        catch (IOException e) {
            throw new MultipartException("Failed to get input stream of the multipart file");
        }
    }

    private String getFolderPath(String name) {
        return content + SEPARATOR + name + SEPARATOR + IMAGES;
    }

    private String getFilePath(String id, String name) {
        return getFolderPath(name) + SEPARATOR + id;
    }

}
