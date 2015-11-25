package com.sadvit.services;

import com.sadvit.operations.binary.BinaryProcessHandler;
import com.sadvit.operations.blur.BlurProcessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

import static com.sadvit.utils.FileUtils.toByteArray;

/**
 * Created by vitaly.sadovskiy.
 *
 *
 * Этот класс по задумке должен передавать параметры специальному обьекту обработчику,
 * который будет возвращать результат обработки изображения, и выкидывать ответ в контроллер.
 */
@Service
public class ProcessService {

	@Autowired
	private ImageService imageService;

	public byte[] processBlur(String id) {
        BufferedImage image = imageService.getBufferedImage(id);
        BlurProcessHandler handler = new BlurProcessHandler();
        BufferedImage result = handler.handle(image, null);
        return toByteArray(result);
	}

	public byte[] processBinary(String id) {
		BufferedImage image = imageService.getBufferedImage(id);
        BinaryProcessHandler handler = new BinaryProcessHandler();
        BufferedImage result = handler.handle(image, null);
        return toByteArray(result);
    }

}
