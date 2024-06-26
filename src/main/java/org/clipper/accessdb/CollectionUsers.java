package org.clipper.accessdb;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "collection-users")
@IdClass(CollectionUsersId.class)
public class CollectionUsers {
    @Id
    private User userId;

    @Id
    private LinkCollection colId;

    public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId.setId(userId.getId());
	}

	public LinkCollection getCollection() {
		return colId;
	}

	public void setCollection(LinkCollection collection) {
		this.colId.setId(collection.getId());
	}
}

