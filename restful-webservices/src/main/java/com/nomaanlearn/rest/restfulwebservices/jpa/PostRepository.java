package com.nomaanlearn.rest.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nomaanlearn.rest.restfulwebservices.user.Posts;


public interface PostRepository extends JpaRepository<Posts, Integer> {

}
