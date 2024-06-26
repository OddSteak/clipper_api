package org.clipper.accessdb;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "links")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

	@ManyToOne
    @JoinColumn(name = "col_id", referencedColumnName = "id" )
    private LinkCollection colId;

	@Column(name = "name")
    private String name;

	@Column(name = "url")
    private String url;

	@Column(name = "date")
    private String date;

	@Column(name = "type")
    private String type;

    Link() {}

    Link(LinkCollection colId, String name, String url, String date, String type) {
        this.colId = colId;
        this.name = name;
        this.url = url;
        this.date = date;
        this.type = type;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public LinkCollection getColId() {
		return colId;
	}

	public void setColId(LinkCollection col_id) {
		this.colId = col_id;
	}

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
