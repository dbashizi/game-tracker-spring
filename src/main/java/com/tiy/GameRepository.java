package com.tiy;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * Created by localdom on 5/17/2016.
 */
public interface GameRepository extends CrudRepository<Game, Integer> {
    List<Game> findByGenre(String genre);
    List<Game> findByReleaseYear(int year);
    List<Game> findByUser(User user);

    @Query("SELECT g FROM Game g WHERE g.name LIKE ?1%")
    List<Game> findByNameStartsWith(String name);

    @Query("SELECT g FROM Game g INNER JOIN g.gamingFormats gf WHERE gf IN (:gamingFormats)")
    List<Game> findByGamingFormats(@Param("gamingFormats") Collection<GamingFormat> gamingFormats);
}
