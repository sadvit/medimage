package com.sadvit.controllers;

import com.sadvit.beetle.ByteImage;
import com.sadvit.beetle.ImageUtils;
import com.sadvit.models.CacheObject;
import com.sadvit.operations.OperationType;
import com.sadvit.operations.binary.BinaryParams;
import com.sadvit.operations.binary.BinaryType;
import com.sadvit.operations.chains.ChainElement;
import com.sadvit.processing.BinaryImageProcessor;
import com.sadvit.processing.ByteImageProcessor;
import com.sadvit.recognizer.ImageProcess;
import com.sadvit.services.ChainService;
import com.sadvit.services.ImageCache;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * Created by sadvit
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private Logger logger = Logger.getLogger(RecognizeController.class);

    @Autowired
    private ChainService chainService;

    @Autowired
    private ImageCache imageCache;

    public ChainElement[] testElements() {
        ChainElement[] elements = new ChainElement[1];
        ChainElement element = new ChainElement();
        BinaryParams binaryParams = new BinaryParams();
        binaryParams.setType(BinaryType.OTSU);
        element.setOperationType(OperationType.BINARY);
        element.setBinaryParams(binaryParams);
        elements[0] = element;
        return elements;
    }

    public ByteImage automatic(ByteImage byteImage) {
        ByteImageProcessor byteImageProcessor = new ByteImageProcessor(byteImage);
        //byteImageProcessor.setBlurImage();
        //byteImageProcessor.setBinary(method);

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

        return binaryImageProcessor.getByteImage();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public double[] test(@PathVariable("id") String id) {

        CacheObject object = chainService.processChain(id, testElements());
        BufferedImage swap = imageCache.getBuffered(object.getId());

        ByteImage byteImage = ImageUtils.bufferedToBinary(swap);

        byteImage = automatic(byteImage);
        byteImage.invert();

        BufferedImage resres = ImageUtils.byteToBuffered(byteImage);

        double[] params = ImageProcess.getParams(resres);
        logger.info("Params for object after Otsu: " + Arrays.toString(params));
        //StatisticalRecognizer sr = new StatisticalRecognizer(new HistogramDistribution(), MEMORY_PATH);
        return params;//sr.recognize(ImageProcess.getParams(resres));
    }

}
