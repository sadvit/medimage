package com.sadvit.controllers;

import com.sadvit.services.RecognizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by vitaly.sadovskiy.
 */
@RestController
@RequestMapping("/networks")
public class RecognizeController {

	@Autowired
	private RecognizeService recognizeService;

	@RequestMapping(value = "/learn", method = RequestMethod.POST)
	public void learn(@RequestBody Map<String, String> images) {
		recognizeService.learn(images);
	}

	@RequestMapping(value = "/recognize", method = RequestMethod.POST)
	public Map<String, String> recognize(@RequestBody List<String> images) {
		return recognizeService.recognize(images);
	}

}
