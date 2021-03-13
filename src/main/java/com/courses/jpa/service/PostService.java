package com.courses.jpa.service;

import com.courses.jpa.entity.Post;
import com.courses.jpa.entity.PostComment;
import com.courses.jpa.repositories.PostCommentRepository;
import com.courses.jpa.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostCommentRepository postCommentRepository;

    @Autowired
    EntityManager entityManager;

    public void oneToManyBi() {
        Post post = new Post("First post");
        entityManager.persist(post);

        PostComment comment1 = new PostComment("My first review");
        post.addComment(comment1);
        PostComment comment2 = new PostComment("My second review");
        post.addComment(comment2);
        entityManager.persist(post);

        post.removeComment(comment1);
    }

    public void oneToManyUni() {
        Post post = new Post("First post");
        post.getComments().add(new PostComment("My first review"));
        post.getComments().add(new PostComment("My second review"));
        post.getComments().add(new PostComment("My third review"));
        entityManager.persist(post);
    }
}
