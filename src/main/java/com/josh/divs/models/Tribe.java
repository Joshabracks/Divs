package com.josh.divs.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tribes")
public class Tribe {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	private String name;
	private String color;
	@OneToMany(mappedBy="tribe", fetch = FetchType.LAZY)
    private List<Div> members;
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="div_id")
    private Div leader;
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    public Tribe() {
    	
    }
    
    
    
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public List<Div> getMembers() {
		return members;
	}



	public void setMembers(List<Div> members) {
		this.members = members;
	}



	public Div getLeader() {
		return leader;
	}



	public void setLeader(Div leader) {
		this.leader = leader;
	}



	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
