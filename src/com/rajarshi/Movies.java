package com.rajarshi;

/**
 * Class that will hold the name of the movies.
 */
class Movies {
    // Member variable for storing movie names
    private String movieName;

    /**
     * The constructor
     */
    Movies(String movieName) {
        this.movieName = movieName;
    }

    /**
     * @return movie names when invoked.
     */
    String getMovieName() {
        return movieName;
    }
}