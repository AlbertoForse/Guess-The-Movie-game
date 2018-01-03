package com.rajarshi;

import java.util.Scanner;

/**
 * The Game class which sets the logic.
 */
public class Game {
    private GameBuilder mBuilder; // We need the methods.
    private String mMovieName; // The name that was randomly generated.
    private int turn; // User turn.
    private String charSting = ""; // Store typed chars.

    /**
     * The constructor.
     *
     * @param builder is needed to get the movie name.
     */
    Game(GameBuilder builder) {
        // Tell which builder is it.
        mBuilder = builder;
        // Get the name of the movie from the builder class.
        mMovieName = mBuilder.getRandomMovieName();
        // Get the turn
        turn = getTurn();
    }

    /**
     * This method is responsible for setting the actual logic and play the game.
     */
    void play() {
        // Initialized a new scanner object.
        Scanner scanner = new Scanner(System.in);
        startMessage(); // Set a start message.

        // Keep track of the turn.
        while (turn >= 1) {
            String userInput = scanner.nextLine();
            // End the game if "?" typed and get out of the loop early.
            if (userInput.equals("?")) {
                endMessage();
                break;
            }

            // Get users input as array of chars.
            char[] guessArray = userInput.toLowerCase().toCharArray();

            // Test if the user didn't typed all the characters on the keyboard.
            if (guessArray.length > 3) {
                System.out.println("Character limit exceeded!");
                System.out.println("Enter a character:");
                continue;
            }

            for (char eachChar : guessArray) {
                // Compare the chars for validity.
                if (isValidChar(eachChar) && isSameChar(eachChar)) {
                    // Compare the chars with movie name chars.
                    if (mMovieName.contains(String.valueOf(eachChar))) {
                        // indexOf will return -1 if non found.
                        // So telling to start with position that contains the char and move on to next char until
                        // it returns -1
                        for (int i = mMovieName.indexOf(eachChar); i >= 0;
                             i = mMovieName.indexOf(eachChar, i + 1)) {
                            // If everything goes as planned then set the chars at their proper positions.
                            mBuilder.getStringBuilder().setCharAt(i, eachChar);
                        }
                    } else {
                        System.out.println(eachChar + " doesn't exist.");
                        turn--; // If non found then deduct a turn.
                        // Implement a check to break early.
                        if (turn == 0) {
                            endMessage();
                            break;
                        }
                    }

                }
            }

            // Break out of while loop early if movie name matches
            if (mMovieName.compareToIgnoreCase(mBuilder.getStringBuilder().toString()) == 0) {
                System.out.println("(✿´‿`) " + mMovieName);
                // Get out early
                break;
            } else {
                if (!mMovieName.equals(mBuilder.getStringBuilder().toString()) && turn > 0) {
                    System.out.print(mBuilder.getStringBuilder() + " | ");
                    System.out.println("Turns left: " + turn + "\nEnter the next character: ");
                }

            }


        }
        scanner.close();
    }

    /**
     * Display the basic info to the users after the game started.
     */
    private void startMessage() {
        System.out.println(mBuilder.getStringBuilder() + " # " + "Total characters: "
                + totalChars() + " | " + "Total tries: " + turn);
        System.out.println("Type '?' to Quit. | " + 3 + " character/s at a time");
        System.out.println("Can you guess the Movie name?");
        System.out.println("Enter a letter:");
    }

    /**
     * Check whether the inputted chars are actually chars and return a boolean value.
     */
    private boolean isValidChar(char eachChar) {
        if (!Character.isLetter(eachChar)) {
            System.out.println("You didn't typed any letter!\n" + "Enter a letter:");
            return false;
        }
        return true;
    }

    /**
     * Don't count already typed chars. If it's not typed by user then store it in {@link #charSting}
     * and check it next time.
     */
    private boolean isSameChar(char eachChar) {
        if (!charSting.contains(String.valueOf(eachChar))) {
            charSting += eachChar;
            return true;
        } else {
            System.out.printf("\"%s\" already typed, try another one!\n", eachChar);
            return false;
        }
    }

    /**
     * Displaying the movie name if user want to quit or turn is over.
     */
    private void endMessage() {
        System.out.println("( ⚆_⚆) " + mMovieName);
    }

    /**
     * @return Integer value of the total unique chars within the movie name.
     */
    private int totalChars() {
        return (int) mMovieName.replaceAll(" ", "").chars().distinct().count();
    }

    /**
     * @return Total turns a user can get based on chars and store it to {@link #turn}
     */
    private int getTurn() {
        int calculateTurn = totalChars();
        if (totalChars() > 10) {
            return totalChars() / 3;
        }
        return 5;
    }
}
