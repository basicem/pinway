package com.example.collectionservice;

import com.example.collectionservice.models.Collection;
import com.example.collectionservice.models.CollectionVisibilityType;
import com.example.collectionservice.repositories.CollectionRepository;
import com.example.collectionservice.repositories.CollectionVisibilityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class CollectionDataLoader {

    private CollectionVisibilityTypeRepository collectionVisibilityTypeRepository;

    private CollectionRepository collectionRepository;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CollectionDataLoader(CollectionVisibilityTypeRepository collectionVisibilityTypeRepository,
                                      CollectionRepository collectionRepository,
                                      JdbcTemplate jdbcTemplate){
        this.collectionVisibilityTypeRepository = collectionVisibilityTypeRepository;
        this.collectionRepository = collectionRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    private  void setCollectionVisibilityType() {
        String sql = "SELECT id FROM collection_visibility_type LIMIT 1";
        List<CollectionVisibilityType> nt = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if( nt == null || nt.size() == 0){

            CollectionVisibilityType nt1 = new CollectionVisibilityType();
            nt1.setType("PRIVATE");

            CollectionVisibilityType nt2 = new CollectionVisibilityType();
            nt2.setType("PUBLIC");

            CollectionVisibilityType nt3 = new CollectionVisibilityType();
            nt3.setType("SHARED");



            collectionVisibilityTypeRepository.save(nt1);
            collectionVisibilityTypeRepository.save(nt2);
            collectionVisibilityTypeRepository.save(nt3);
        }
        System.out.println(collectionVisibilityTypeRepository.count());
    }

    private  void seedNotification() {
        String sql = "SELECT id, name FROM collection LIMIT 1";
        List<Collection> n = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if( n == null || n.size() == 0){

            Optional<CollectionVisibilityType> optionalVisibilityType = collectionVisibilityTypeRepository.findOneByType("PRIVATE");
            CollectionVisibilityType visibilityType = new CollectionVisibilityType();

            if(optionalVisibilityType.isPresent())
                visibilityType = optionalVisibilityType.get();

            Collection c1 = new Collection();
            c1.setName("Cute");
            c1.setNumOfPosts(0);
            c1.setCreatedAt(LocalDate.now());
            c1.setDeleted(false);
            c1.setUserId(Long.valueOf(2));
            c1.setCollectionVisibilityType(visibilityType);

            optionalVisibilityType = collectionVisibilityTypeRepository.findOneByType("PRIVATE");
            visibilityType = new CollectionVisibilityType();

            if(optionalVisibilityType.isPresent())
                visibilityType = optionalVisibilityType.get();

            Collection c2 = new Collection();
            c2.setName("Cyber Punk");
            c2.setNumOfPosts(0);
            c2.setCreatedAt(LocalDate.now());
            c2.setDeleted(false);
            c2.setUserId(Long.valueOf(2));
            c2.setCollectionVisibilityType(visibilityType);


            collectionRepository.save(c1);
            collectionRepository.save(c2);

        }
        System.out.println(collectionRepository.count());
    }

    @EventListener
    public  void seed(ContextRefreshedEvent event){
        setCollectionVisibilityType();
        seedNotification();

    }
}
