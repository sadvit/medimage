package com.sadvit.services;

import com.sadvit.analysis.beetle.ByteImage;
import com.sadvit.analysis.beetle.ImageUtils;
import com.sadvit.analysis.processing.BinaryImageProcessor;
import com.sadvit.analysis.processing.ByteImageProcessor;
import com.sadvit.analysis.recognizer.ImageProcess;
import com.sadvit.exceptions.IncorrectImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

/**
 * Created by sadvit
 */
@Service
public class ParamsService {

    @Autowired
    private ImageCache imageCache;

    public double[] findParams(String imageId) {
        double[] result;
        try {
            BufferedImage swap = imageCache.getBuffered(imageId);
            ByteImage byteImage = ImageUtils.bufferedToBinary(swap);
            byteImage = getContour(byteImage);
            BufferedImage buffered = ImageUtils.byteToBuffered(byteImage);
            result = ImageProcess.getParams(buffered);
        } catch (Exception e) {
            throw new IncorrectImage(imageId);
        }
        return result;
    }

    private ByteImage getContour(ByteImage byteImage) {
        ByteImageProcessor byteImageProcessor = new ByteImageProcessor(byteImage);

        ByteImage result = byteImageProcessor.getByteImage();

        BinaryImageProcessor binaryImageProcessor = new BinaryImageProcessor(result);

        binaryImageProcessor.setFillImage();
        binaryImageProcessor.setErodeImage(1);
        binaryImageProcessor.setErodeImage(1);
        binaryImageProcessor.setErodeImage(1);
        binaryImageProcessor.setErodeImage(1);
        binaryImageProcessor.setErodeImage(1);
        binaryImageProcessor.setDilateImage(1);
        binaryImageProcessor.fillBorder();
        binaryImageProcessor.setLinesImage();

        ByteImage image = binaryImageProcessor.getByteImage();
        image.invert();

        return image;
    }

}
