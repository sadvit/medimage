package com.sadvit.services;

import com.sadvit.operations.HandlerType;
import com.sadvit.operations.ProcessHandler;
import com.sadvit.operations.HandlerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Map;

import static com.sadvit.utils.FileUtils.toByteArray;

/**
 * Created by vitaly.sadovskiy.
 *
 *
 */
@Service
public class ProcessService {

	@Autowired
	private ImageService imageService;

	public byte[] process(String id, HandlerType type, Map params) {
        BufferedImage image = imageService.getBufferedImage(id);
        ProcessHandler handler = HandlerFactory.getHandler(type);
        BufferedImage result = handler.handle(image, params);
        return toByteArray(result);
	}

}
