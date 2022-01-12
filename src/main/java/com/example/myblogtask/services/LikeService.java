package com.example.myblogtask.services;

import com.example.myblogtask.models.PostLikes;
import com.example.myblogtask.models.UserDetails;

public interface LikeService {

    void likePost(UserDetails person, Long postId, int action);
}
