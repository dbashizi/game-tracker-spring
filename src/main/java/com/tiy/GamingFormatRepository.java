package com.tiy;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by localdom on 5/17/2016.
 */
public interface GamingFormatRepository extends CrudRepository<GamingFormat, Integer> {

    GamingFormat findByName(String name);
}
