package org.clipper.accessdb;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
@IdClass(CategoryId.class)
public class Category {
    @Id
    private String category;

	@Id
    private Link linkId;

    public Category(Link linkId, String category) {
        this.linkId = linkId;
        this.category = category;
    }

    public Category() {}

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Link getLinkId() {
		return linkId;
	}

	public void setLinkId(Link link_id) {
		this.linkId = link_id;
	}

    public String toString() {
        return String.format("[%s - %s]", category, linkId.getId());
    }
}
