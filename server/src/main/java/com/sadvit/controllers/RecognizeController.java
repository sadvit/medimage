package com.sadvit.controllers;

import com.sadvit.beetle.BeetleController;
import com.sadvit.beetle.ByteImage;
import com.sadvit.beetle.ImageUtils;
import com.sadvit.geometry.GeometryObject;
import com.sadvit.models.CacheObject;
import com.sadvit.operations.OperationType;
import com.sadvit.operations.binary.BinaryParams;
import com.sadvit.operations.binary.BinaryType;
import com.sadvit.operations.chains.ChainElement;
import com.sadvit.processing.BinaryImageProcessor;
import com.sadvit.processing.ByteImageProcessor;
import com.sadvit.recognizer.ImageProcess;
import com.sadvit.recognizer.statistic.StatisticalRecognizer;
import com.sadvit.recognizer.statistic.distribution.HistogramDistribution;
import com.sadvit.services.ChainService;
import com.sadvit.services.ImageCache;
import com.sadvit.services.ImageService;
import com.sadvit.utils.FileUtils;
import com.sun.imageio.plugins.common.ImageUtil;
import org.apache.log4j.Logger;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.imgrec.FractionRgbData;
import org.neuroph.imgrec.ImageRecognitionHelper;
import org.neuroph.imgrec.ImageRecognitionPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by vitaly.sadovskiy.
 */
@RestController
@RequestMapping("/recognize")
public class RecognizeController {

	private Logger logger = Logger.getLogger(RecognizeController.class);

	@Autowired
	private ImageService imageService;

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

	public static BufferedImage toBufferedImage(byte[] image) {
		InputStream in = new ByteArrayInputStream(image);
		try {
			return ImageIO.read(in);
		} catch (IOException e) {
			return null;
		}
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

	@RequestMapping(value="/test/{id}", method = RequestMethod.GET)
	public double[] test(@PathVariable("id") String id) {
		CacheObject object = chainService.processChain(id, testElements());
		BufferedImage swap = toBufferedImage(imageCache.get(object.getId()));

		ByteImage byteImage = ImageUtils.bufferedToBinary(swap);

		byteImage = automatic(byteImage);
		byteImage.invert();

		//Binarizator binarizator = new Binarizator(byteImage);
		//for (ByteImage binaryImage : binarizator) {
		//GeometryObject geometryObject = BeetleController.seekGeometryObject(byteImage);
		//BufferedImage resres = ImageUtils.geometryToBuffered(geometryObject);

		BufferedImage resres = ImageUtils.byteToBuffered(byteImage);

		try {
			ImageIO.write(resres, "bmp", new File("resres.bmp"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		double[] params = ImageProcess.getParams(resres);
		logger.info("Params for object after Otsu: " + Arrays.toString(params));
		//StatisticalRecognizer sr = new StatisticalRecognizer(new HistogramDistribution(), MEMORY_PATH);
		return params; //sr.recognize(ImageProcess.getParams(bufferedImage));
	}

	@RequestMapping(method = RequestMethod.POST)
	public void teach(Map<String, String> images) {
		InputStream inputStream = FileUtils.readFile("/home/sadvit/Documents/content/sadvit/networks/NetworkLabel.nnet");
		NeuralNetwork network = NeuralNetwork.load(inputStream);
		DataSet trainingSet = ImageRecognitionHelper.createTrainingSet(extractLabels(images), extractRgbData(images));
		network.learn(trainingSet);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Map<String, Double> recognize(List<String> images) {
		InputStream inputStream = FileUtils.readFile("/home/sadvit/Documents/content/sadvit/networks/NetworkLabel.nnet");
		NeuralNetwork network = NeuralNetwork.load(inputStream);
		ImageRecognitionPlugin imageRecognition = (ImageRecognitionPlugin)network.getPlugin(ImageRecognitionPlugin.class); // get the image recognition plugin from neural network
		Map<String, Double> result = new HashMap<>();
		images.forEach(id -> result.putAll(imageRecognition.recognizeImage(imageService.getBufferedImage(id))));
		return result;
	}

	public List<String> extractLabels(Map<String, String> images) {
		return images.values().stream().collect(Collectors.toList());
	}

	public Map<String, FractionRgbData> extractRgbData(Map<String, String> images) {
		Map<String, FractionRgbData> dataMap = new HashMap<>();
		images.forEach((s, s2) -> dataMap.put(s, new FractionRgbData(imageService.getBufferedImage(s2))));
		return dataMap;
	}

}
