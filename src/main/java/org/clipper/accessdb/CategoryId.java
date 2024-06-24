package org.clipper.accessdb;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CategoryId implements Serializable {
    private String category;

    @ManyToOne
    @JoinColumn(name = "link_id", referencedColumnName = "id")
    private Links link_id;

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

    public void setLink_id(Links link_id) {
		this.link_id = link_id;
	}

	public Links getLink_id() {
		return link_id;
	}

    public CategoryId(String cat, Links linkId) {
        this.category = cat;
        this.link_id = linkId;
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryId catId1 = (CategoryId) o;
        return Objects.equals(category, catId1.category) &&
               Objects.equals(link_id, catId1.link_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, link_id);
    }
}
