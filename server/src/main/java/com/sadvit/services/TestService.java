package com.sadvit.services;

import com.sadvit.analysis.beetle.ByteImage;
import com.sadvit.analysis.beetle.ImageUtils;
import com.sadvit.models.CacheObject;
import com.sadvit.operations.OperationType;
import com.sadvit.operations.binary.BinaryParams;
import com.sadvit.operations.binary.BinaryType;
import com.sadvit.operations.chains.ChainElement;
import com.sadvit.analysis.processing.BinaryImageProcessor;
import com.sadvit.analysis.processing.ByteImageProcessor;
import com.sadvit.analysis.recognizer.ImageProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

/**
 * Created by sadvit
 */
@Service
public class TestService {

    @Autowired
    private ChainService chainService;

    @Autowired
    private ImageCache imageCache;

    public ChainElement[] createTestChain() {
        ChainElement[] elements = new ChainElement[1];
        ChainElement element = new ChainElement();
        BinaryParams binaryParams = new BinaryParams();
        binaryParams.setType(BinaryType.OTSU);
        element.setOperationType(OperationType.BINARY);
        element.setBinaryParams(binaryParams);
        elements[0] = element;
        return elements;
    }

    public ByteImage getContour(ByteImage byteImage) {
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

    public double[] findParams(String id) {
        CacheObject object = chainService.processChain(id, createTestChain());
        BufferedImage swap = imageCache.getBuffered(object.getId());
        ByteImage byteImage = ImageUtils.bufferedToBinary(swap);
        byteImage = getContour(byteImage);
        BufferedImage buffered = ImageUtils.byteToBuffered(byteImage);
        return ImageProcess.getParams(buffered);
    }

}
