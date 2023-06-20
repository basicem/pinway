package com.example.postservice.repositories;

import com.example.postservice.models.Hashtag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface HashtagRepository extends CrudRepository<Hashtag, Long> {
    Hashtag findByName( String name);

    @Query("SELECT h FROM Hashtag h ORDER BY h.hash_counter DESC ")
    Iterable<Hashtag> getMostPopular();
}
