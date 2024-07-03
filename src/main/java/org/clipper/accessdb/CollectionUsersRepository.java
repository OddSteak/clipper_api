package org.clipper.accessdb;

import org.springframework.data.repository.CrudRepository;

public interface CollectionUsersRepository extends CrudRepository<CollectionUsers, CollectionUsersId> {
    Iterable<LinkCollection> findByUserId(User userId);
}
