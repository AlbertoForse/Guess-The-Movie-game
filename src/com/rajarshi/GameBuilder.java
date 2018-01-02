package com.rajarshi;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * This class gets the Movie Names.
 * Helps to replace each characters to asterisks and vise versa.
 */
class GameBuilder {
    // A member variable that can be accessed by others within this class.
    private ArrayList<Movies> mMoviesList = new ArrayList<>();
    // Name of a randomly generated movie.
    private String mMovieName;
    // A variable of StringBuilder to work upon.
    private StringBuilder mStringBuilder;

    /**
     * Constructor which contains methods which are called as soon as it is initialized.
     *
     * @param txtMovieNames is the file which contains the movie names
     */
    GameBuilder(String txtMovieNames) {
        // Add the movie names to the list
        addMovieNames(txtMovieNames);
        // Get a random movie name
        randomName(mMoviesList);
        // Set the characters of the movie name to dashes.
        setDashed();
    }

    /**
     * Create a List of {@link Movies} names found within the text file.
     * Add the objects to the ArrayList of {@link #mMoviesList}
     *
     * @param txtFile is used to get the Movie names from the text file.
     */
    private void addMovieNames(String txtFile) {
        try {
            Scanner scanner = new Scanner(new FileReader(txtFile));
            while (scanner.hasNextLine()) {
                mMoviesList.add(new Movies(scanner.nextLine()));
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * Get a random movie name and store it to {@link #mMovieName}
     *
     * @param fromList is the ArrayList of {@link #mMoviesList}
     */
    private void randomName(List<Movies> fromList) {
        Collections.shuffle(fromList);
        mMovieName = fromList.get(0).getMovieName().trim();
    }

    /**
     * {@link StringBuilder} is mutable, it doesn't creates a new object.
     * Can be used to easily replace the strings.
     * Replace all the characters of the movie name with dashes/asterisks.
     */
    private void setDashed() {
        // Not so strong with regex. Help needed for "GLOW". Anyone?
        mStringBuilder = new StringBuilder(mMovieName.replaceAll("\\b?[a-z]", "*"));
    }

    /**
     * @return {@link #mStringBuilder} as so that other classes can replace
     * the strings with correctly guessed characters.
     */
    StringBuilder getStringBuilder() {
        return mStringBuilder;
    }

    /**
     * @return the Randomly generated move name.
     */
    String getRandomMovieName() {
        return mMovieName;
    }
}