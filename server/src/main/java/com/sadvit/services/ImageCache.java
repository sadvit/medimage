package com.sadvit.services;

import com.sadvit.models.CacheObject;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by vitaly.sadovskiy.
 */
@Service
public class ImageCache {

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

}
