package com.example.myblogtask.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Entity
@Table(name = "posts")
public class Post extends TimeStamps{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private UserDetails users;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UserDetails user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public Long getPostId() {return postId;}

    public void setPostId(Long postId) {this.postId = postId;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getBody() {return body;}

    public void setBody(String body) {this.body = body;}

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    //    @JsonBackReference
//    public UserDetails getUsers() {return users;}
//
//    public void setUsers(UserDetails users) {this.users = users;}

    public List<Comment> getComments() {return comments;}

    public void setComments(List<Comment> comments) {this.comments = comments;}
}
