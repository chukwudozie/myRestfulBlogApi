package com.example.myblogtask.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;



@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String active = "ACTIVE";

    @Column(nullable = false)
    private String gender;

    @OneToMany
//    @JsonBackReference
    private List<UserDetails> friends;  // List of friends that can see ur post

    @OneToMany
//    @JsonManagedReference
    private List<Post> favoritePosts;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getActive() {return active;}

    public void setActive(String active) {this.active = active;}

    public String getGender() {return gender;}

    public void setGender(String gender) {this.gender = gender;}

    public List<UserDetails> getFriends() {return friends;}

    public void setFriends(List<UserDetails> friends) {this.friends = friends;}

    public List<Post> getFavoritePosts() {
        return favoritePosts;
    }

    public void setFavoritePosts(List<Post> favoritePosts) {
        this.favoritePosts = favoritePosts;
    }

    //    @JsonManagedReference
//    public List<Post> getPost() {return post;}
//
//    public void setPost(List<Post> post) {this.post = post;}

}
