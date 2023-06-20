package com.example.postservice.controllers;

import com.example.postservice.models.Hashtag;
import com.example.postservice.services.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller // This means that this class is a Controller
@RequestMapping(path="/api/hashtag") // This means URL's start with /demo (after Application path)
public class HashtagController {
    @Autowired
    private  HashtagService hashtagService;
    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<Hashtag>> getAllHashtags() {
        // This returns a JSON or XML with the users

        Iterable<Hashtag> allHashtags =  hashtagService.List();

        return  ResponseEntity.status(200).body(allHashtags);
    }
}
