package org.clipper.accessdb;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CollectionUsersId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "col_id", referencedColumnName = "id")
    private Collection col_id;

    public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

    public Collection getCol_id() {
		return col_id;
	}

	public void setCol_id(Collection col_id) {
		this.col_id = col_id;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionUsersId colId1 = (CollectionUsersId) o;
        return Objects.equals(colId1.user_id, user_id) &&
               Objects.equals(colId1.col_id, col_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, col_id);
    }
}
