package com.example.collectionservice.services;
import com.example.collectionservice.dto.*;
import com.example.collectionservice.models.Collection;
import com.example.collectionservice.models.CollectionVisibilityType;

import java.util.List;
import java.util.Optional;

public interface CollectionService {
    Collection Create(Collection collection);

    Iterable<Collection> List();

    Iterable<Collection> FindAllByIds(Iterable<Integer> ids);

    Collection Details(Integer id);

    Boolean Delete(Integer id);

    Collection Update(Integer id, Collection collection);

    Collection ChangeVisibilityType(Integer id, CollectionVisibilityTypeOnlyTypeDTO collectionVisibilityTypeOnlyTypeDTO);

    Iterable<CollectionVisibilityType> ListVisibilityTypes();

}