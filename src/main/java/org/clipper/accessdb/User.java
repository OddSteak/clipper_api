package org.clipper.accessdb;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "user")
public class User {
    @Id
    private String id;

    @OneToMany(mappedBy = "creator_id")
    List<Collection> collections;

    @OneToMany(mappedBy = "user_id")
    List<CollectionUsers> accessCollections;

    private String pass;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}

