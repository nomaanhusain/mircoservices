package com.nomaanlearn.rest.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nomaanlearn.rest.restfulwebservices.user.User;


public interface UserRepository extends JpaRepository<User, Integer> {

}
