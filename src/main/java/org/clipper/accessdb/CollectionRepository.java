package org.clipper.accessdb;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CollectionRepository extends CrudRepository<LinkCollection, Integer> {
    List<LinkCollection> findByCreatorId(User creatorId);

    List<LinkCollection> findByName(String name);
}
