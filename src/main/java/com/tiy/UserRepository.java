package com.tiy;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by localdom on 5/17/2016.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    User findFirstByName(String name);
}
