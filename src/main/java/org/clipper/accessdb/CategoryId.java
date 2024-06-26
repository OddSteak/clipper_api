package org.clipper.accessdb;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CategoryId implements Serializable {
    @Id
    @Column(name = "category")
    private String category;

    @Id
    @ManyToOne
    @JoinColumn(name = "link_id", referencedColumnName = "id")
    private Link linkId;

    public CategoryId(Link linkId, String category) {
        this.linkId = linkId;
        this.category = category;
    }

    public CategoryId() {}

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

    public void setLinkId(Link link_id) {
		this.linkId = link_id;
	}

	public Link getLinkId() {
		return linkId;
	}

    public CategoryId(String cat, Link linkId) {
        this.category = cat;
        this.linkId = linkId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * (result + ((category == null) ? 0 : category.hashCode()));
        result = prime * (result + ((linkId == null) ? 0 : linkId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CategoryId other = (CategoryId) obj;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (linkId == null) {
            if (other.linkId != null)
                return false;
        } else if (!linkId.equals(other.linkId))
            return false;
        return true;
    }
}
