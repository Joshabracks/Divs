package com.josh.divs.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "dislikes", 
        joinColumns = @JoinColumn(name = "div_id"), 
        inverseJoinColumns = @JoinColumn(name = "thing_id")
    )
    private List<Thing> dislikes;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "likes", 
        joinColumns = @JoinColumn(name = "div_id"), 
        inverseJoinColumns = @JoinColumn(name = "thing_id")
    )
    private List<Thing> likes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tribe_id")
    private Tribe tribe;
    @OneToOne(mappedBy="leader", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Tribe leaderOf;
	private Integer age;
	@Max(25)
	private Integer radius;
	private Integer damage;
	private Integer health;
    private Integer x;
    private Integer y;
    private Integer targetX;
    private Integer targetY;
//	private List<Div> relationships;
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "traits",
        joinColumns = @JoinColumn(name = "div_id"), 
        inverseJoinColumns = @JoinColumn(name = "trait_id")
    )
	private List<Trait> traits;
	//FRIENDS
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "friendships", 
    joinColumns = @JoinColumn(name = "div_id"), 
    inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<Div> friends;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "friendships", 
    joinColumns = @JoinColumn(name = "friend_id"), 
    inverseJoinColumns = @JoinColumn(name = "div_id")
    )
    private List<Div> divFriends;
    //FRIENDS
    //ENEMIES
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "enemies", 
    joinColumns = @JoinColumn(name = "div_id"), 
    inverseJoinColumns = @JoinColumn(name = "enemy_id")
    )
    private List<Div> enemies;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "enemies", 
    joinColumns = @JoinColumn(name = "enemy_id"), 
    inverseJoinColumns = @JoinColumn(name = "div_id")
    )
    private List<Div> divEnemies;
    //ENEMIES
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    public Div() {
    	
    }
    
	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
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
		return targetY;
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

	public List<Thing> getDislikes() {
		return dislikes;
	}

	public void setDislikes(List<Thing> dislikes) {
		this.dislikes = dislikes;
	}

	public List<Thing> getLikes() {
		return likes;
	}

	public void setLikes(List<Thing> likes) {
		this.likes = likes;
	}

	public Tribe getTribe() {
		return tribe;
	}

	public void setTribe(Tribe tribe) {
		this.tribe = tribe;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
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

//	public List<Div> getRelationships() {
//		return relationships;
//	}
//
//	public void setRelationships(List<Div> relationships) {
//		this.relationships = relationships;
//	}
	//FRIENDS
	public List<Div> getFriends() {
        return friends;
    }
    public void setFriends(List<Div> friends) {
        this.friends = friends;
    }
    public List<Div> getDivFriends() {
        return divFriends;
    }
    public void setUserFriends(List<Div> userFriends) {
        this.divFriends = userFriends;
    }     
    //FRIENDS
	//ENEMIES
	public List<Div> getEnemies() {
        return friends;
    }
    public void setEnemies(List<Div> enemies) {
        this.enemies = enemies;
    }
    public List<Div> getDivEnemies() {
        return divEnemies;
    }
    public void setUserEnemies(List<Div> userEnemies) {
        this.divEnemies = userEnemies;
    }     
    //ENEMIES
	public List<Trait> getTraits() {
		return traits;
	}

	public void setTraits(List<Trait> traits) {
		this.traits = traits;
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
