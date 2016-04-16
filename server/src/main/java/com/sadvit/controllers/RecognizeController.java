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

	@RequestMapping(value = "/learn/{chainId}/{networkId}", method = RequestMethod.POST)
	public void learn(@RequestBody Map<String, String> images, @PathVariable("networkId") Integer networkId, @PathVariable("chainId") Integer chainId) {
		recognizeService.learn(chainId, networkId, images);
	}

	@RequestMapping(value = "/recognize/{chainId}/{networkId}", method = RequestMethod.POST)
	public Map<String, String> recognize(@RequestBody List<String> images, @PathVariable("networkId") Integer networkId, @PathVariable("chainId") Integer chainId) {
		return recognizeService.recognize(chainId, networkId, images);
	}

}
