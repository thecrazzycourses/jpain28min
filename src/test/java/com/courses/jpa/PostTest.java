package com.courses.jpa;

import com.courses.jpa.entity.Post;
import com.courses.jpa.entity.PostComment;
import com.courses.jpa.repositories.PostCommentRepository;
import com.courses.jpa.repositories.PostRepository;
import com.courses.jpa.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class PostTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostCommentRepository postCommentRepository;

    @Autowired
    PostService postService;

    @Test
    void addCommend() {
        // Add Comment to a Post
        Post post = postRepository.findById(1001L).get();
        PostComment comment = new PostComment("My First Review");
        comment.setPost(post);
        postCommentRepository.save(comment);
    }

    @Test
    void removeCommend() {
        // Remove Comment for a Post
        PostComment postComment = postCommentRepository.findById(2001L).get();
        postComment.setPost(null);
        postCommentRepository.save(postComment);
    }

    @Test
    void oneToManyBirectional() {
        postService.oneToManyBi();
    }

    @Test
    void oneToManyUni() {
        postService.oneToManyUni();
    }

}
