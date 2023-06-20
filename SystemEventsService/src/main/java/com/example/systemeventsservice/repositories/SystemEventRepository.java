package com.example.systemeventsservice.repositories;

import com.example.systemeventsservice.models.SystemEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface SystemEventRepository extends CrudRepository<SystemEvent, Long> {
}
