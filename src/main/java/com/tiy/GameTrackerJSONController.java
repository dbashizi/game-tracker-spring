package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by localdom on 5/18/2016.
 */
@RestController
public class GameTrackerJSONController {

    @Autowired
    GameRepository games;

    @Autowired
    UserRepository users;

    @RequestMapping(path = "/addGame.json", method = RequestMethod.POST)
    public ArrayList<Game> addGame(HttpSession session, @RequestBody Game game) throws Exception {
        User user = (User)session.getAttribute("user");

        if (user == null) {
            throw new Exception("Unable to add game without an active user in the session");
        }
        game.user = user;

        games.save(game);

        return getAllGames();
    }

    @RequestMapping(path = "/getChocolateSolution.json", method = RequestMethod.POST)
    public ChocolateSolution getChocolateSolution(HttpSession session, @RequestBody ChocolateRequest chocolateRequest) throws Exception {
        User user = (User)session.getAttribute("user");

        System.out.println("Chocolate Request::");
        System.out.println(chocolateRequest);

        ChocolateSolution solution = new CodingBatExercises().
                                        makeChocolate(chocolateRequest.smalls,
                                                      chocolateRequest.bigs,
                                                      chocolateRequest.kilos);

        return solution;
    }

    @RequestMapping(path = "/games.json", method = RequestMethod.GET)
    public ArrayList<Game> getGames() {
        return getAllGames();
    }

    @RequestMapping(path = "/users.json", method = RequestMethod.GET)
    public ArrayList<User> getUsers() {
        return getAllUsers();
    }

    ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<User>();
        Iterable<User> allUsers = users.findAll();
        for (User user : allUsers) {
            userList.add(user);
        }

        return userList;
    }

    @RequestMapping(path = "/deleteGame.json", method = RequestMethod.GET)
    public ArrayList<Game> deleteGame(int gameID) {
        System.out.println("deleting game with ID " + gameID);
        Game game = games.findOne(gameID);
        games.delete(game);

//        return "{\"response\": \"SUCCESS\"}";
        return getAllGames();
    }

    ArrayList<Game> getAllGames() {
        ArrayList<Game> gameList = new ArrayList<Game>();
        Iterable<Game> allGames = games.findAll();
        for (Game game : allGames) {
            gameList.add(game);
        }

        return gameList;
    }

    @RequestMapping(path = "/toggleGame.json", method = RequestMethod.GET)
    public ArrayList<Game> toggleGame(int gameID) {
        System.out.println("toggling game with ID " + gameID);
        Game game = games.findOne(gameID);
        game.name = "**" + game.name;
        games.save(game);

        return getAllGames();
    }

}
