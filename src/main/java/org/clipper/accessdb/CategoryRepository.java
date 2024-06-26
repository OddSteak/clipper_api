package org.clipper.accessdb;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, CategoryId> {
    public List<Category> findByLinkId(Link link_id);

    public List<Link> findByCategory(String category);
}
