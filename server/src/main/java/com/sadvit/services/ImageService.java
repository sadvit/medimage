package com.sadvit.services;

import com.sadvit.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * Created by sadvit on 23.11.15.
 */
@Service
public class ImageService {

    @Autowired
    private UserService userService;

    public InputStream getImage(String id) {
        return FileUtils.readFile(getFilePath(id));
    }

    public BufferedImage getBufferedImage(String id) {
        return FileUtils.readBufferedImage(getFilePath(id));
    }

    public byte[] getImageAsByteArray(String id) {
        return FileUtils.readFileAsByteArray(getFilePath(id));
    }

    public List<String> getAllImageNames() {
        return FileUtils.readAllFileNames(getFolderPath());
    }

    public void deleteImage(String id) {
        FileUtils.deleteFile(getFilePath(id));
    }

    public String saveImage(MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            String id = UUID.randomUUID().toString();
            FileUtils.writeFile(getFilePath(id), inputStream);
            return id;
        } catch (IOException e) {
            throw new MultipartException("Failed to get input stream of the multipart file");
        }
    }

    public String saveImage(BufferedImage bufferedImage) {
        String id = UUID.randomUUID().toString();
        FileUtils.writeBufferedImage(getFilePath(id), bufferedImage);
        return id;
    }

    private String getFolderPath() {
        return userService.getUserFolderPath();
    }

    private String getFilePath(String id) {
        return userService.getUserFilePath(id);
    }

}
