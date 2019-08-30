package com.josh.divs.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="relationships")
public class Relationship {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	private Integer level;
	public Relationship() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
}

