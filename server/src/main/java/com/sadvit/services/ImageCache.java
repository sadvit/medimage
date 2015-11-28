package com.sadvit.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vitaly.sadovskiy.
 */
@Service
public class ImageCache {

	private Map<String, byte[]> images = new HashMap<String, byte[]>();

	public void put(String id, byte[] image) {
		images.put(id, image);
	}

	public byte[] get(String id) {
		return images.get(id);
	}

	public void remove(String id) {
		images.remove(id);
	}

}
