package com.sadvit.controllers;

import com.sadvit.services.RecognizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping(value = "/learn/{networkId}", method = RequestMethod.POST)
	public void learn(@RequestBody Map<String, String> images, @PathVariable("networkId") Integer networkId) {
		recognizeService.learn(networkId, images);
	}

	@RequestMapping(value = "/recognize/{networkId}", method = RequestMethod.POST)
	public Map<String, String> recognize(@RequestBody List<String> images, @PathVariable("networkId") Integer networkId) {
		return recognizeService.recognize(networkId, images);
	}

}
