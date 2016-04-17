package com.sadvit.controllers;

import com.sadvit.services.ImageCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sadvit.utils.WebUtils.imageResponse;

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
        return imageResponse(cache.get(id));
    }


    @RequestMapping(method = RequestMethod.POST)
    public List<String> saveFromCache(@RequestBody List<String> images) {
        return cache.saveFromCache(images);
    }

}