package com.sadvit.services;

import boofcv.alg.filter.binary.ThresholdImageOps;
import boofcv.core.image.ConvertBufferedImage;
import boofcv.gui.binary.VisualizeBinaryData;
import boofcv.struct.image.ImageFloat32;
import boofcv.struct.image.ImageUInt8;
import com.sadvit.utils.FileUtils;
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
		return null;
	}

	public byte[] processBinary(String id) {
		BufferedImage image = imageService.getBufferedImage(id);
		ImageFloat32 input = ConvertBufferedImage.convertFromSingle(image, null, ImageFloat32.class);
		ImageUInt8 binary = new ImageUInt8(input.width, input.height);
		ThresholdImageOps.threshold(input, binary, 100, true);
		BufferedImage visualBinary = VisualizeBinaryData.renderBinary(binary, null);
		return toByteArray(visualBinary);
	}

}
