package com.example.myblogtask.services.serviceImpl;

import com.example.myblogtask.models.Post;
import com.example.myblogtask.models.PostLikes;
import com.example.myblogtask.models.UserDetails;
import com.example.myblogtask.repositories.CommentRepository;
import com.example.myblogtask.repositories.PostLikesRepository;
import com.example.myblogtask.repositories.PostRepository;
import com.example.myblogtask.repositories.UserRepository;
import com.example.myblogtask.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;

public class LikeServiceImpl implements LikeService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PostLikesRepository likesRepository;

    @Autowired
    public LikeServiceImpl(PostRepository postRepository, CommentRepository commentRepository,
             UserRepository userRepository,PostLikesRepository likesRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.likesRepository =likesRepository;
    }

    @Override
    public void likePost(UserDetails user, Long postId, int action) {
        Post post = postRepository.getById(postId);
        PostLikes newLike = new PostLikes();
        newLike.setPost(post);
        newLike.setUser(user);

        if(action == 1){
            likesRepository.save(newLike);
            int likesCount = post.getLikesCount();
            likesCount++;
        }
        else{
            likesRepository.deletePostLikesByPostAndUser(post,user);
        }


    }
}
