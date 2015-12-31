package com.sadvit.controllers;

import com.sadvit.services.ImageService;
import com.sadvit.utils.FileUtils;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.imgrec.FractionRgbData;
import org.neuroph.imgrec.ImageRecognitionHelper;
import org.neuroph.imgrec.ImageRecognitionPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
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

	@Autowired
	private ImageService imageService;

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
