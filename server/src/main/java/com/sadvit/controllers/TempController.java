package com.sadvit.controllers;

import com.sadvit.services.ImageCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}