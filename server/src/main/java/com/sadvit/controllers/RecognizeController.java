package com.sadvit.controllers;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.imgrec.ImageRecognitionPlugin;
import org.neuroph.nnet.Perceptron;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by vitaly.sadovskiy.
 */
@RestController
@RequestMapping("/recognize")
public class RecognizeController {

	@RequestMapping(method = RequestMethod.GET)
	public void test() {
		// create new perceptron network
		NeuralNetwork neuralNetwork = new Perceptron(2, 1);
		// create training set
		DataSet trainingSet =
				new DataSet(2, 1);
		// add training data to training set (logical OR function)
		trainingSet. addRow (new DataSetRow(new double[]{0, 0},
				new double[]{0}));
		trainingSet. addRow (new DataSetRow (new double[]{0, 1},
				new double[]{1}));
		trainingSet. addRow (new DataSetRow (new double[]{1, 0},
				new double[]{1}));
		trainingSet. addRow (new DataSetRow (new double[]{1, 1},
				new double[]{1}));
		// learn the training set
		neuralNetwork.learn(trainingSet);
		// save the trained network into file
		neuralNetwork.save("or_perceptron.nnet");
	}

	public void test2(BufferedImage image) {
		Image instance = image.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
	}

}
