package com.example.myblogtask.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.myblogtask.models.Comment;
import com.example.myblogtask.models.Post;
import com.example.myblogtask.models.UserDetails;
import com.example.myblogtask.services.serviceImpl.CommentServiceImpl;
import com.example.myblogtask.services.serviceImpl.PostServiceImpl;
import com.example.myblogtask.services.serviceImpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CommentController.class})
@ExtendWith(SpringExtension.class)
class CommentControllerTest {
    @Autowired
    private CommentController commentController;

    @MockBean
    private CommentServiceImpl commentServiceImpl;

    @MockBean
    private PostServiceImpl postServiceImpl;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @Test
    void testCreateComment() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setActive("Active");
        userDetails.setEmail("jane.doe@example.org");
        userDetails.setFavoritePosts(new ArrayList<>());
        userDetails.setFriends(new ArrayList<>());
        userDetails.setGender("Gender");
        userDetails.setId(123L);
        userDetails.setName("Name");
        userDetails.setPassword("iloveyou");
        when(this.userServiceImpl.getUserById((Long) any())).thenReturn(userDetails);
        when(this.commentServiceImpl.createComment((Long) any(), (Long) any(), (Comment) any())).thenReturn(true);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setActive("Active");
        userDetails1.setEmail("jane.doe@example.org");
        userDetails1.setFavoritePosts(new ArrayList<>());
        userDetails1.setFriends(new ArrayList<>());
        userDetails1.setGender("Gender");
        userDetails1.setId(123L);
        userDetails1.setName("Name");
        userDetails1.setPassword("iloveyou");

        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setComments(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setDateCreated(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setDateModified(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post.setLikesCount(3);
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUser(userDetails1);

        UserDetails userDetails2 = new UserDetails();
        userDetails2.setActive("Active");
        userDetails2.setEmail("jane.doe@example.org");
        userDetails2.setFavoritePosts(new ArrayList<>());
        userDetails2.setFriends(new ArrayList<>());
        userDetails2.setGender("Gender");
        userDetails2.setId(123L);
        userDetails2.setName("Name");
        userDetails2.setPassword("iloveyou");

        Comment comment = new Comment();
        comment.setComment("Comment");
        comment.setCommentId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comment.setDateCreated(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comment.setDateModified(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        comment.setPost(post);
        comment.setUsers(userDetails2);
        String content = (new ObjectMapper()).writeValueAsString(comment);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/blogComment/createComment/{postId}/{userId}", 123L, 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Comment Comment saved!!!"));
    }

    @Test
    void testCreateComment2() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setActive("Active");
        userDetails.setEmail("jane.doe@example.org");
        userDetails.setFavoritePosts(new ArrayList<>());
        userDetails.setFriends(new ArrayList<>());
        userDetails.setGender("Gender");
        userDetails.setId(123L);
        userDetails.setName("Name");
        userDetails.setPassword("iloveyou");
        when(this.userServiceImpl.getUserById((Long) any())).thenReturn(userDetails);
        when(this.commentServiceImpl.createComment((Long) any(), (Long) any(), (Comment) any())).thenReturn(false);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setActive("Active");
        userDetails1.setEmail("jane.doe@example.org");
        userDetails1.setFavoritePosts(new ArrayList<>());
        userDetails1.setFriends(new ArrayList<>());
        userDetails1.setGender("Gender");
        userDetails1.setId(123L);
        userDetails1.setName("Name");
        userDetails1.setPassword("iloveyou");

        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setComments(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setDateCreated(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setDateModified(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post.setLikesCount(3);
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUser(userDetails1);

        UserDetails userDetails2 = new UserDetails();
        userDetails2.setActive("Active");
        userDetails2.setEmail("jane.doe@example.org");
        userDetails2.setFavoritePosts(new ArrayList<>());
        userDetails2.setFriends(new ArrayList<>());
        userDetails2.setGender("Gender");
        userDetails2.setId(123L);
        userDetails2.setName("Name");
        userDetails2.setPassword("iloveyou");

        Comment comment = new Comment();
        comment.setComment("Comment");
        comment.setCommentId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comment.setDateCreated(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comment.setDateModified(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        comment.setPost(post);
        comment.setUsers(userDetails2);
        String content = (new ObjectMapper()).writeValueAsString(comment);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/blogComment/createComment/{postId}/{userId}", 123L, 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Comment not saved"));
    }

    @Test
    void testEditComment() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setActive("Active");
        userDetails.setEmail("jane.doe@example.org");
        userDetails.setFavoritePosts(new ArrayList<>());
        userDetails.setFriends(new ArrayList<>());
        userDetails.setGender("Gender");
        userDetails.setId(123L);
        userDetails.setName("Name");
        userDetails.setPassword("iloveyou");
        when(this.userServiceImpl.getUserById((Long) any())).thenReturn(userDetails);
        when(this.commentServiceImpl.editComment((UserDetails) any(), (Long) any(), (Long) any(), (String) any()))
                .thenReturn(true);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setActive("Active");
        userDetails1.setEmail("jane.doe@example.org");
        userDetails1.setFavoritePosts(new ArrayList<>());
        userDetails1.setFriends(new ArrayList<>());
        userDetails1.setGender("Gender");
        userDetails1.setId(123L);
        userDetails1.setName("Name");
        userDetails1.setPassword("iloveyou");

        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setComments(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setDateCreated(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setDateModified(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post.setLikesCount(3);
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUser(userDetails1);

        UserDetails userDetails2 = new UserDetails();
        userDetails2.setActive("Active");
        userDetails2.setEmail("jane.doe@example.org");
        userDetails2.setFavoritePosts(new ArrayList<>());
        userDetails2.setFriends(new ArrayList<>());
        userDetails2.setGender("Gender");
        userDetails2.setId(123L);
        userDetails2.setName("Name");
        userDetails2.setPassword("iloveyou");

        Comment comment = new Comment();
        comment.setComment("Comment");
        comment.setCommentId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comment.setDateCreated(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comment.setDateModified(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        comment.setPost(post);
        comment.setUsers(userDetails2);
        String content = (new ObjectMapper()).writeValueAsString(comment);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/blogComment/editComment/{commentId}/post/{postId}/user/{userId}", 123L, 123L, 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Comment with id 123 updated"));
    }

    @Test
    void testEditComment2() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setActive("Active");
        userDetails.setEmail("jane.doe@example.org");
        userDetails.setFavoritePosts(new ArrayList<>());
        userDetails.setFriends(new ArrayList<>());
        userDetails.setGender("Gender");
        userDetails.setId(123L);
        userDetails.setName("Name");
        userDetails.setPassword("iloveyou");
        when(this.userServiceImpl.getUserById((Long) any())).thenReturn(userDetails);
        when(this.commentServiceImpl.editComment((UserDetails) any(), (Long) any(), (Long) any(), (String) any()))
                .thenReturn(false);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setActive("Active");
        userDetails1.setEmail("jane.doe@example.org");
        userDetails1.setFavoritePosts(new ArrayList<>());
        userDetails1.setFriends(new ArrayList<>());
        userDetails1.setGender("Gender");
        userDetails1.setId(123L);
        userDetails1.setName("Name");
        userDetails1.setPassword("iloveyou");

        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setComments(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setDateCreated(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setDateModified(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post.setLikesCount(3);
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUser(userDetails1);

        UserDetails userDetails2 = new UserDetails();
        userDetails2.setActive("Active");
        userDetails2.setEmail("jane.doe@example.org");
        userDetails2.setFavoritePosts(new ArrayList<>());
        userDetails2.setFriends(new ArrayList<>());
        userDetails2.setGender("Gender");
        userDetails2.setId(123L);
        userDetails2.setName("Name");
        userDetails2.setPassword("iloveyou");

        Comment comment = new Comment();
        comment.setComment("Comment");
        comment.setCommentId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comment.setDateCreated(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comment.setDateModified(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        comment.setPost(post);
        comment.setUsers(userDetails2);
        String content = (new ObjectMapper()).writeValueAsString(comment);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/blogComment/editComment/{commentId}/post/{postId}/user/{userId}", 123L, 123L, 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("failed to update comment"));
    }

    @Test
    void testGetCommentById() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setActive("Active");
        userDetails.setEmail("jane.doe@example.org");
        userDetails.setFavoritePosts(new ArrayList<>());
        userDetails.setFriends(new ArrayList<>());
        userDetails.setGender("Gender");
        userDetails.setId(123L);
        userDetails.setName("Name");
        userDetails.setPassword("iloveyou");

        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setComments(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setDateCreated(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setDateModified(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post.setLikesCount(3);
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUser(userDetails);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setActive("Active");
        userDetails1.setEmail("jane.doe@example.org");
        userDetails1.setFavoritePosts(new ArrayList<>());
        userDetails1.setFriends(new ArrayList<>());
        userDetails1.setGender("Gender");
        userDetails1.setId(123L);
        userDetails1.setName("Name");
        userDetails1.setPassword("iloveyou");

        Comment comment = new Comment();
        comment.setComment("Comment");
        comment.setCommentId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comment.setDateCreated(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        comment.setDateModified(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        comment.setPost(post);
        comment.setUsers(userDetails1);
        Optional<Comment> ofResult = Optional.of(comment);
        when(this.commentServiceImpl.getCommentById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/blogComment/getComment/{commentId}",
                123L);
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"dateCreated\":0,\"dateModified\":0,\"commentId\":123,\"comment\":\"Comment\",\"users\":{\"id\":123,\"name\":\"Name"
                                        + "\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"active\":\"Active\",\"gender\":\"Gender\",\"friends\""
                                        + ":[],\"favoritePosts\":[]}}"));
    }

    @Test
    void testDeleteComment() throws Exception {
        doNothing().when(this.commentServiceImpl).deleteCommentById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/blogComment/delete/{commentId}",
                123L);
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Comment with id 123 deleted!!"));
    }

    @Test
    void testDeleteComment2() throws Exception {
        doNothing().when(this.commentServiceImpl).deleteCommentById((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/blogComment/delete/{commentId}", 123L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Comment with id 123 deleted!!"));
    }

    @Test
    void testGetComments() throws Exception {
        when(this.commentServiceImpl.getAllComments()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/blogComment/getComment");
        MockMvcBuilders.standaloneSetup(this.commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

