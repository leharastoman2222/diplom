package com.example.diplom;

public class User {
    public String username, email , userUid;
    public int wordsScore;
    private int lettersScore;
    private int sentencesScore;
    private int numbersScore;
    private int symbolsScore;

    public User(){};

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.wordsScore = wordsScore;
        this.lettersScore = lettersScore;
        this.sentencesScore = sentencesScore;
        this.numbersScore = numbersScore;
        this.symbolsScore = symbolsScore;

    }
    public String getUsername(){
        return username;
    }
    public void setName(String username) {
        this.username = username;
    }
    public int getWordsScore(){
        return wordsScore;
    }
    public void setWordsScore(int wordsScore) {
        this.wordsScore = wordsScore;
    }
    public int getLettersScore(){
        return lettersScore;
    }
    public void setLettersScore(int lettersScore) {
        this.lettersScore = lettersScore;
    }
    public int getSentencesScore(){
        return sentencesScore;
    }
    public void setSentencesScore(int sentencesScore) {
        this.sentencesScore = sentencesScore;
    }
    public int getNumbersScore(){
        return numbersScore;
    }
    public void setNumbersScore(int numbersScore) {
        this.numbersScore = numbersScore;
    }
    public int getSymbolsScore(){
        return symbolsScore;
    }
    public void setSymbolsScore(int symbolsScore) {
        this.symbolsScore = symbolsScore;
    }


}
