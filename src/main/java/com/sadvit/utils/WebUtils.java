package com.sadvit.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * Created by vitaly.sadovskiy.
 */
public class WebUtils {

	public static ResponseEntity<byte[]> imageResponse(byte[] image) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(image, headers, HttpStatus.CREATED);
	}

}
