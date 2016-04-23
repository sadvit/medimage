package com.sadvit.services;

import com.sadvit.models.CacheObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by vitaly.sadovskiy.
 */
@Service
public class ImageCache {

    @Autowired
    private ImageService imageService;

    private Map<String, byte[]> images = new HashMap<>();

    public void put(String id, byte[] image) {
        images.put(id, image);
    }

    public byte[] get(String id) {
        return images.get(id);
    }

    public BufferedImage getBuffered(String id) {
        InputStream in = new ByteArrayInputStream(get(id));
        try {
            return ImageIO.read(in);
        } catch (IOException e) {
            return null;
        }
    }

    public void remove(String id) {
        images.remove(id);
    }

    public CacheObject addToCache(byte[] image) {
        String imageId = UUID.randomUUID().toString();
        put(imageId, image);
        return new CacheObject(imageId);
    }

    public List<String> saveFromCache(List<String> images) {
        List<String> imageIds = new ArrayList<>();
        images.forEach(image -> {
            imageIds.add(imageService.saveImage(getBuffered(image)));
        });
        return imageIds;
    }


}
