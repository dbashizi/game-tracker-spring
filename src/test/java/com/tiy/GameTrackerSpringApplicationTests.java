package com.tiy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GameTrackerSpringApplication.class)
@WebAppConfiguration
public class GameTrackerSpringApplicationTests {

	@Autowired
	UserRepository users;

	@Autowired
	GameRepository games;

	@Autowired
	GamingFormatRepository gamingFormatRepo;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testCreateUser() {
		User testUser = new User("tester2", "easy");
		users.save(testUser);
		System.out.println("User created in the database");

		assertNotNull(testUser);
		assertNotEquals(0, testUser.getId());

		int userId = testUser.getId();
		User retrievedUser = users.findOne(userId);
		assertNotNull(retrievedUser);
		assertEquals(testUser.getName(), retrievedUser.getName());
		assertEquals(testUser.getPassword(), retrievedUser.getPassword());
		System.out.println("User retrieved from the database");

		users.delete(testUser);
		retrievedUser = users.findOne(userId);

		assertNull(retrievedUser);
		System.out.println("User deleted from the database");
	}

	@Test
	public void testCreateGamingFormat() {
		GamingFormat cd = new GamingFormat("CD_TEST");

		gamingFormatRepo.save(cd);

		int cdId = cd.getId();
		assertNotEquals(0, cdId);

		GamingFormat retrievedFormat = gamingFormatRepo.findOne(cdId);
		assertNotNull(retrievedFormat);

		assertEquals(cd.getName(), retrievedFormat.getName());

		gamingFormatRepo.delete(cd);

		retrievedFormat = gamingFormatRepo.findOne(cdId);
		assertNull(retrievedFormat);
	}

	@Test
	public void testCreateGameWithFormat() {
		GamingFormat format1 = new GamingFormat("format1");
		GamingFormat format2= new GamingFormat("format2");
		gamingFormatRepo.save(format1);
		gamingFormatRepo.save(format2);

		User testUser = new User("formatTester", "password");
		users.save(testUser);

		Game testGame = new Game("Test Game", "Test Platform", "Test Genre", 1998, testUser);

		ArrayList<GamingFormat> gamingFormats = new ArrayList<GamingFormat>();
		gamingFormats.add(format1);
		gamingFormats.add(format2);

		testGame.setGamingFormats(gamingFormats);

		games.save(testGame);

		int gameId = testGame.getId();
		assertNotEquals(0, gameId);

		Game retrievedGame = games.findOne(gameId);
		assertNotNull(retrievedGame);
		assertNotNull(retrievedGame.getGamingFormats());

		games.delete(testGame);
		gamingFormatRepo.delete(format1);
		gamingFormatRepo.delete(format2);
		users.delete(testUser);
	}

	@Test
	public void deleteEverything() {
		games.deleteAll();
		gamingFormatRepo.deleteAll();
		users.deleteAll();
	}

	@Test
	public void testFindByGameFormat() {
		GamingFormat format1 = new GamingFormat("format1");
		GamingFormat format2= new GamingFormat("format2");
		gamingFormatRepo.save(format1);
		gamingFormatRepo.save(format2);

		User testUser = new User("formatTester", "password");
		users.save(testUser);

		Game testGame = new Game("Test Game", "Test Platform", "Test Genre", 1998, testUser);

		ArrayList<GamingFormat> gamingFormats = new ArrayList<GamingFormat>();
		gamingFormats.add(format1);
		gamingFormats.add(format2);

		testGame.setGamingFormats(gamingFormats);

		games.save(testGame);

		int gameId = testGame.getId();
		assertNotEquals(0, gameId);

		Game retrievedGame = games.findOne(gameId);
		assertNotNull(retrievedGame);
		assertNotNull(retrievedGame.getGamingFormats());

		// test searching by gaming format
		List<Game> foundGames = games.findByGamingFormats(gamingFormats);
		assertNotNull(foundGames);
		assertEquals(2, foundGames.size());

		games.delete(testGame);
		gamingFormatRepo.delete(format1);
		gamingFormatRepo.delete(format2);
		users.delete(testUser);
	}
}
