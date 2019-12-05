package com.students.repositories;

import com.students.entities.User;

public class UserRepository extends AbstractRepository<User> {

    public UserRepository() {
        super(User.class);
    }

}
