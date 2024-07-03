package org.clipper.accessdb;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "user")
public class User {
    @Id
    private String id;

    private String pass;

    public User(String id, String pass) {
        this.id = id;
        this.pass = pass;
    }

    public User() {}

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

