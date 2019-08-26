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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="traits")
public class Trait {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	private String name;
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "traits", 
        joinColumns = @JoinColumn(name = "trait_id"), 
        inverseJoinColumns = @JoinColumn(name = "div_id")
    )
	private List<Div> traitOf;
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    public Trait() {
    	
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



	public List<Div> getTraitOf() {
		return traitOf;
	}



	public void setTraitOf(List<Div> traitOf) {
		this.traitOf = traitOf;
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
