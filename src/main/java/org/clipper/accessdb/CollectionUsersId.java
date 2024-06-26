package org.clipper.accessdb;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CollectionUsersId implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    @Id
    @ManyToOne
    @JoinColumn(name = "col_id", referencedColumnName = "id")
    private LinkCollection colId;

    public CollectionUsersId() {}

    public CollectionUsersId(User user_id, LinkCollection col_id) {
        this.userId = user_id;
        this.colId = col_id;
    }

    public User getUserId() {
		return userId;
	}

	public void setUserId(User user_id) {
		this.userId = user_id;
	}

    public LinkCollection getColId() {
		return colId;
	}

	public void setColId(LinkCollection col_id) {
		this.colId = col_id;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((colId == null) ? 0 : colId.hashCode());
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
        CollectionUsersId other = (CollectionUsersId) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (colId == null) {
            if (other.colId != null)
                return false;
        } else if (!colId.equals(other.colId))
            return false;
        return true;
    }
}
