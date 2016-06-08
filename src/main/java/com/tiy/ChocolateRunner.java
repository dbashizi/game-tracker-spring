package com.tiy;

/*
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
*/

import java.net.URL;
import java.util.Scanner;

/**
 * Created by localdom on 5/26/2016.
 */
public class ChocolateRunner /* extends Application */ {

    public static void main(String[] args) {
        System.out.println("ChocoloateRunner running ...");

//        launch(args);

        playChocolate();

        System.out.println("ChocolateRunner done!");
    }

    public static void playChocolate() {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Welcome to the Chocolate Game");

        while (true) {
            System.out.println("How many big blocks of chocolate do you have?");
            int bigs = Integer.valueOf(inputScanner.nextLine());
            System.out.println("How many small blocks of chocolate do you have?");
            int smalls = Integer.valueOf(inputScanner.nextLine());
            System.out.println("How much chocolate are you trying to make?");
            int goal = Integer.valueOf(inputScanner.nextLine());

            CodingBatExercises solver = new CodingBatExercises();
            ChocolateSolution solution = solver.makeChocolate(smalls, bigs, goal);
            if (!solution.hasSolution) {
                System.out.println("No chocolate for you!");
            } else {
                System.out.println("Yay!! You have chocolate!");
                System.out.println("\tWe used " + solution.smalls + " small blocks");
                drawSmallBlocks(solution.smalls);
                System.out.println("\tWe used " + solution.bigs + " big blocks");
                drawBigBlocks(solution.bigs);

            }

            System.out.println("Would you like to play again? (y/n)");
            String playAgain = inputScanner.nextLine();
            if (playAgain.equals("n")) {
                break;
            }
        }
    }

    public static void drawX() {
        System.out.println("\t\\        /");
        System.out.println("\t \\      /");
        System.out.println("\t  \\    /");
        System.out.println("\t   \\  /");
        System.out.println("\t    \\/");
        System.out.println("\t    /\\");
        System.out.println("\t   /  \\");
        System.out.println("\t  /    \\");
        System.out.println("\t /      \\");
        System.out.println("\t/        \\");
    }

    public static void drawSmallBlocks(int numBlocks) {
        if (numBlocks == 0) {
            drawX();
        } else {
            drawBlockLine(numBlocks, "---------");
            drawBlockLine(numBlocks, "|       |");
            drawBlockLine(numBlocks, "---------");
        }
    }

    public static void drawBigBlocks(int numBlocks) {
        if (numBlocks == 0) {
            drawX();
        } else {
            drawBlockLine(numBlocks, "---------");
            drawBlockLine(numBlocks, "|       |");
            drawBlockLine(numBlocks, "|       |");
            drawBlockLine(numBlocks, "|       |");
            drawBlockLine(numBlocks, "|       |");
            drawBlockLine(numBlocks, "|       |");
            drawBlockLine(numBlocks, "---------");
        }
    }

    public static void drawBlockLine(int numBlocks, String lineContent) {
        String spacer = "\t\t\t";
        StringBuilder fullLineBuilder = new StringBuilder();
        for (int blockIndex = 0; blockIndex < numBlocks; blockIndex++) {
            fullLineBuilder.append(lineContent);
            fullLineBuilder.append(spacer);
        }
        System.out.println(fullLineBuilder.toString());
    }

    /*

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        URL testing = getClass().getResource("/chocolate.fxml");
        System.out.println(testing);
        Parent root = fxmlLoader.load(getClass().getResource("/chocolate.fxml").openStream());
        ChocolateController controller = (ChocolateController) fxmlLoader.getController();

        primaryStage.setTitle("TIY ToDo App");
        primaryStage.setScene(new Scene(root, 800, 600));

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing -> cleanup!");
            }
        });

        primaryStage.show();
    }
    */

}
