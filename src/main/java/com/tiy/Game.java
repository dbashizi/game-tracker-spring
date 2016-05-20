package com.tiy;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by localdom on 5/17/2016.
 */
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    User user;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String platform;

    @Column(nullable = false)
    String genre;

    @Column(nullable = false)
    int releaseYear;

    @ManyToMany
    private Collection<GamingFormat> gamingFormats;

    public Collection<GamingFormat> getGamingFormats() {
        return gamingFormats;
    }

    public void setGamingFormats(Collection<GamingFormat> gamingFormats) {
        this.gamingFormats = gamingFormats;
    }

    public Game() {
    }

    @Override
    public String toString() {
        String returnString = "name = " + name + "\n";
        returnString += "genre = " + genre + "\n";
        returnString += "releaseYear = " + releaseYear + "\n";
        if (user != null) {
            returnString += "user = " + user.getName() + "\n";
        }

        return returnString;
    }

    public Game(String name, String platform, String genre, int releaseYear, User user) {
        this.name = name;
        this.platform = platform;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
