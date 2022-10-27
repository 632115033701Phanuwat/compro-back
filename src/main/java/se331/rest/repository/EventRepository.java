package se331.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.rest.entity.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findAll();
    Page<Event> findByName(String name, Pageable pageRequest);
    Page<Event> findByNameContaining(String name, Pageable pageRequest);
    Page<Event> findByNameContainingOrDescriptionContaining(String name, String description, Pageable pageRequest);
    Page<Event> findByNameContainingOrDescriptionContainingOrOrganizer_NameContaining(String name, String description, String organizerName, Pageable pageRequest);
    Page<Event> findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContainingOrOrganizer_NameIgnoreCaseContaining(String name, String description, String organizerName, Pageable pageRequest);
}
