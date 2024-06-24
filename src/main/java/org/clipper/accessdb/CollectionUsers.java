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
    private User user_id;

    @Id
    private Collection col_id;

    public User getUserId() {
		return user_id;
	}

	public void setUserId(User userId) {
		this.user_id.setId(userId.getId());
	}

	public Collection getCollection() {
		return col_id;
	}

	public void setCollection(Collection collection) {
		this.col_id.setId(collection.getId());
	}
}

