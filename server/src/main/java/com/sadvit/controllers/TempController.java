package com.sadvit.controllers;

import com.sadvit.services.ImageCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sadvit.utils.WebUtils.responseImage;

/**
 * Created by vitaly.sadovskiy.
 */
@RestController
@RequestMapping("/temp")
public class TempController {

    @Autowired
    private ImageCache cache;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        return responseImage(cache.get(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<String> saveTempImages(@RequestBody List<String> images) {
        return cache.saveFromCache(images);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteImage(@PathVariable String id) {
        cache.remove(id);
    }

}