package org.clipper.accessdb;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

enum colAccess {
    PVT,
    SHARED,
    PUBLIC
}

@Entity
@Table(name = "collection")
public class LinkCollection {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

	@ManyToOne
    @JoinColumn(name = "creator_id",  referencedColumnName = "id")
    private User creatorId;

	@Column(name = "name")
    private String name;

	@Enumerated(EnumType.ORDINAL)
    @Column(name = "access")
    private colAccess access;

    LinkCollection() {}

    public LinkCollection(User creatorId, String name, colAccess access) {
        this.creatorId = creatorId;
        this.name = name;
        this.access = access;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public User getCreator() {
		return creatorId;
	}

	public void setCreator(User creator) {
		this.creatorId = creator;
	}


    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public colAccess getAccess() {
		return access;
	}

	public void setAccess(colAccess access) {
		this.access = access;
	}
}
