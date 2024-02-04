package sample;

import java.util.Random;

public class GuessGame {
    private int bestScore;
    private int numberOfGuesses;
    private int range;
    private int value;

    public GuessGame(int range) {
        this.bestScore = Integer.MAX_VALUE;
        this.numberOfGuesses = 0;
        this.range = range;
        this.value = new Random().nextInt(1, range + 1);
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    public void setNumberOfGuesses(int numberOfGuesses) {
        this.numberOfGuesses = numberOfGuesses;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public GuessResult guess(int guess){
        double error = guess - value;
        numberOfGuesses++;
        if(error == 0){
            bestScore = Math.min(bestScore, numberOfGuesses);
            return GuessResult.EXACT;
        }
        if(error/range > -0.33 && error < 0){
            return GuessResult.LOW;
        }
        if(error/range < 0.33 && error > 0){
            return GuessResult.HIGH;
        }
        if(error < 0){
            return GuessResult.TOO_LOW;
        }
        return GuessResult.TOO_HIGH;
    }

    public void restart(){
        this.numberOfGuesses = 0;
        this.value = new Random().nextInt(1, range + 1);
    }

    public int giveUp(){
        return this.value;
    }
}
