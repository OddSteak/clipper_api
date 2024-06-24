package org.clipper.accessdb;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;

enum colAccess {
    PVT,
    SHARED,
    PUBLIC
}

@Entity
@Table(name = "collection")
public class Collection {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

	@ManyToOne
    @JoinColumn(name = "creator_id",  referencedColumnName = "id")
    private User creator_id;

	@Column(name = "name")
    private String name;

	@Enumerated(EnumType.ORDINAL)
    @Column(name = "access")
    private colAccess access;

    @OneToMany(mappedBy = "col_id")
    List<CollectionUsers> access_users;

    @OneToMany(mappedBy = "col_id")
    List<Links> links;

    public List<Links> getLinks() {
		return links;
	}

	public void setLinks(List<Links> links) {
		this.links = links;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public User getCreator() {
		return creator_id;
	}

	public void setCreator(User creator) {
		this.creator_id = creator;
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

	public void addAccess(colAccess access) {
		this.access = access;
	}
}
