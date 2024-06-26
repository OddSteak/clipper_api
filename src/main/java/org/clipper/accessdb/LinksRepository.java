package org.clipper.accessdb;

import org.springframework.data.repository.CrudRepository;

public interface LinksRepository extends CrudRepository<Link, Integer> {
    Iterable<Link> findByColId(LinkCollection id);
}
