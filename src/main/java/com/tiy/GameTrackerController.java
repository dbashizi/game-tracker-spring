package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by localdom on 5/17/2016.
 */
@Controller
public class GameTrackerController {

    @Autowired
    GameRepository games;

    @Autowired
    UserRepository users;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {
        User user = users.findFirstByName(userName);
        if (user == null) {
            user = new User(userName, password);
            users.save(user);
        }
        else if (!password.equals(user.password)) {
            throw new Exception("Incorrect password");
        }
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/games", method = RequestMethod.GET)
    public String games(Model model, HttpSession session) {
        return "games";
    }

    @RequestMapping(path = "/chocolate", method = RequestMethod.GET)
    public String chocolate(Model model, HttpSession session) {
        return "chocolate";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session, String genre, Integer releaseYear) {
//        setSessionAttribute(model, session);
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }

        List<Game> gameList = new ArrayList<Game>();
        if (genre != null) {
            gameList = games.findByGenre(genre);
        } else if (releaseYear != null) {
            gameList = games.findByReleaseYear(releaseYear);
        } else {
            User savedUser = (User)session.getAttribute("user");
            if (savedUser != null) {
                gameList = games.findByUser(savedUser);
            } else {
                Iterable<Game> allGames = games.findAll();
                for (Game game : allGames) {
                    gameList.add(game);
                }
            }
        }
        model.addAttribute("games", gameList);
        return "home";
    }

    public void setSessionAttribute(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String deleteGame(Model model, Integer gameID) {
        if (gameID != null) {
            games.delete(gameID);
        }

        return "redirect:/";
    }

    @RequestMapping(path = "/toggle", method = RequestMethod.GET)
    public String toggle(Model model, Integer gameID) {
        if (gameID != null) {
            Game game = games.findOne(gameID);
            game.name = " ** " + game.name;
            games.save(game);
        }

        return "redirect:/";
    }



    @RequestMapping(path = "/searchByName", method = RequestMethod.GET)
    public String queryGamesByName(Model model, HttpSession session, String search) {
        setSessionAttribute(model, session);

        System.out.println("Searching by ..." + search);
        List<Game> gameList = games.findByNameStartsWith(search);
        model.addAttribute("games", gameList);
        return "home";
    }

    @RequestMapping(path = "/add-game", method = RequestMethod.POST)
    public String addGame(HttpSession session, String gameName, String gamePlatform, String gameGenre, int gameYear) {
        User user = (User)session.getAttribute("user");
        Game game = new Game(gameName, gamePlatform, gameGenre, gameYear, user);
        games.save(game);
        return "redirect:/";
    }

    @PostConstruct
    public void init() {
        if (users.count() == 0) {
            User user = new User();
            user.name = "test";
            user.password = "password";
            users.save(user);
        }
    }}
