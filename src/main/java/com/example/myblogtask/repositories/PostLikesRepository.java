package com.example.myblogtask.repositories;

import com.example.myblogtask.models.Post;
import com.example.myblogtask.models.PostLikes;
import com.example.myblogtask.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {
    void deletePostLikesByPostAndUser(Post post, UserDetails user);

}
