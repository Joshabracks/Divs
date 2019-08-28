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
@Table(name="things")
public class Thing {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	private String name;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//        name = "dislikes", 
//        joinColumns = @JoinColumn(name = "thing_id"), 
//        inverseJoinColumns = @JoinColumn(name = "div_id")
//    )
//    private List<Div> dislikedBy;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//        name = "likes", 
//        joinColumns = @JoinColumn(name = "thing_id"), 
//        inverseJoinColumns = @JoinColumn(name = "div_id")
//    )
//    private List<Div> likedBy;
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    public Thing() {
    	
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

//	public List<Div> getDislikedBy() {
//		return dislikedBy;
//	}
//
//	public void setDislikedBy(List<Div> dislikedBy) {
//		this.dislikedBy = dislikedBy;
//	}
//
//	public List<Div> getLikedBy() {
//		return likedBy;
//	}
//
//	public void setLikedBy(List<Div> likedBy) {
//		this.likedBy = likedBy;
//	}
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
