package com.josh.divs.models;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.josh.divs.tools.FirstTrait;

@Entity
@Table(name="divs")
public class Div {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@Size(min=1)
	private String name;
	@Size(min=1)
	private String color;
	@Size(min=1)
	private String outline;
	@Size(min=3)
	private String mood;
	@Size(min=3)
	private String trait;
	@Size(min=3)
	private String status;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//        name = "dislikes", 
//        joinColumns = @JoinColumn(name = "div_id"), 
//        inverseJoinColumns = @JoinColumn(name = "thing_id")
//    )
//    private List<Thing> dislikes;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//        name = "likes", 
//        joinColumns = @JoinColumn(name = "div_id"), 
//        inverseJoinColumns = @JoinColumn(name = "thing_id")
//    )
//    private List<Thing> likes;

	private Long age;
	
	private Integer radius;
	private Integer damage;
	private Integer health;
    private Integer x;
    private Integer y;
    private Integer targetX;
    private Integer targetY;
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    private String enjoy;
    private String love;
    private String dislike;
    private String hate;
    private Long targetId;

    public Div() {
    	FirstTrait firstTrait = new FirstTrait();
		String trait = firstTrait.get();
		this.trait = trait;
			
    }
    
    
    
	public String getTrait() {
		return trait;
	}



	public void setTrait(String trait) {
		this.trait = trait;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public Long getTargetId() {
		return targetId;
	}



	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}



	public Integer getX() {
		return this.x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return this.y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getTargetX() {
		return targetX;
	}

	public void setTargetX(Integer targetX) {
		this.targetX = targetX;
	}

	public Integer getTargetY() {
		return this.targetY;
	}

	public void setTargetY(Integer targetY) {
		this.targetY = targetY;
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
	
	

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

//	public List<Thing> getDislikes() {
//		return dislikes;
//	}
//
//	public void setDislikes(List<Thing> dislikes) {
//		this.dislikes = dislikes;
//	}
//
//	public List<Thing> getLikes() {
//		return likes;
//	}
//
//	public void setLikes(List<Thing> likes) {
//		this.likes = likes;
//	}

	
	
	public Long getAge() {
		return age;
	}

	public String getEnjoy() {
		return enjoy;
	}



	public void setEnjoy(String enjoy) {
		this.enjoy = enjoy;
	}



	public String getLove() {
		return love;
	}



	public void setLove(String love) {
		this.love = love;
	}



	public String getDislike() {
		return dislike;
	}



	public void setDislike(String dislike) {
		this.dislike = dislike;
	}



	public String getHate() {
		return hate;
	}



	public void setHate(String hate) {
		this.hate = hate;
	}



	public void setAge(Long age) {
		this.age = age;
	}

	public Integer getDamage() {
		return damage;
	}

	public void setDamage(Integer damage) {
		this.damage = damage;
	}

	public Integer getHealth() {
		return health;
	} 

	public void setHealth(Integer health) {
		this.health = health;
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
