package com.example.postservice.services;

import com.example.postservice.models.Hashtag;

public interface HashtagService {
    Iterable<Hashtag> List();
    Iterable<Hashtag> CreateIfNotExistsAll(Iterable<String> names);
    void FixPostDelete(Iterable<Hashtag> hashtags);
    Iterable<Hashtag> FixPostUpdate(Iterable<Hashtag> oldHashtags, Iterable<String> newHastagNames);
}
