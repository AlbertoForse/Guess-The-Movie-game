package com.rajarshi;

public class MovieGame {

    public static void main(String[] args) {

        GameBuilder builder = new GameBuilder("movies.txt");
        Game newGame = new Game(builder);
        newGame.play();

    }

}
