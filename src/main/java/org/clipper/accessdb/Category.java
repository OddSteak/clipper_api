package org.clipper.accessdb;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(CategoryId.class)
public class Category {
    @Id
    private String category;

	@Id
    private Links link_id;

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Links getLink_id() {
		return link_id;
	}

	public void setLink_id(Links link_id) {
		this.link_id = link_id;
	}
}
