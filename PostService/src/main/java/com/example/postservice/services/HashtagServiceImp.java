package com.example.postservice.services;

import com.example.postservice.models.Hashtag;
import com.example.postservice.repositories.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class HashtagServiceImp implements HashtagService {
    @Autowired
    private HashtagRepository hashtagRepository;

    public Iterable<Hashtag> List(){
        Iterable<Hashtag> hashtags = hashtagRepository.getMostPopular();
        return  hashtags;
    }

    @Override
    public Iterable<Hashtag> CreateIfNotExistsAll(Iterable<String> names) {
        Set<Hashtag> retHashtags = new HashSet<>();
        for (String name: names) {
            Hashtag hashtag = hashtagRepository.findByName(name);
            if(hashtag == null){
                Hashtag newHashtag = new Hashtag();
                newHashtag.setName(name);
                newHashtag.setHash_counter(1);
                newHashtag.setPin_counter(0);
                hashtagRepository.save(newHashtag);
                retHashtags.add (newHashtag);
            }else {
                hashtag.setHash_counter(hashtag.getHash_counter() + 1);
                retHashtags.add(hashtag);
            }
        }
        return retHashtags;
    }

    @Override
    public void FixPostDelete(Iterable<Hashtag> hashtags) {
        for (Hashtag hashtag: hashtags) {
            hashtag.setHash_counter(hashtag.getHash_counter()-1);
        }
    }

    @Override
    public Iterable<Hashtag> FixPostUpdate(Iterable<Hashtag> oldHashtags, Iterable<String> newHastagNames) {
        for (Hashtag hash: oldHashtags) {
            hash.setHash_counter(hash.getHash_counter() - 1);
        }
        Iterable<Hashtag> retHashtags = CreateIfNotExistsAll(newHastagNames);
        return retHashtags;
    }

}
