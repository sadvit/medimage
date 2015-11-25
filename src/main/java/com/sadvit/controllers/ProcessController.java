package com.sadvit.controllers;

import boofcv.alg.filter.binary.BinaryImageOps;
import boofcv.alg.filter.binary.Contour;
import boofcv.alg.filter.binary.GThresholdImageOps;
import boofcv.alg.filter.binary.ThresholdImageOps;
import boofcv.core.image.ConvertBufferedImage;
import boofcv.gui.ListDisplayPanel;
import boofcv.gui.binary.VisualizeBinaryData;
import boofcv.gui.image.ShowImages;
import boofcv.struct.ConnectRule;
import boofcv.struct.image.ImageFloat32;
import boofcv.struct.image.ImageSInt32;
import boofcv.struct.image.ImageUInt8;
import com.sadvit.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by vitaly.sadovskiy.
 */
@RestController
public class ProcessController {

	@Autowired
	private ImageService imageService;

	// /images/{id}/process/blur
	@RequestMapping(method = RequestMethod.GET, value = "/images/{id}/process/blur")
	public ResponseEntity<byte[]> processBlur(@PathVariable String id) {
		BufferedImage image = imageService.getBufferedImage(id);

		ImageFloat32 input = ConvertBufferedImage.convertFromSingle(image, null, ImageFloat32.class);
		ImageUInt8 binary = new ImageUInt8(input.width, input.height);

		ThresholdImageOps.threshold(input, binary, 100, true);

		BufferedImage visualBinary = VisualizeBinaryData.renderBinary(binary, null);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try	{
			ImageIO.write(visualBinary, "jpg", baos);
			return imageResponse(baos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private ResponseEntity<byte[]> imageResponse(byte[] image) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(image, headers, HttpStatus.CREATED);
	}

}
