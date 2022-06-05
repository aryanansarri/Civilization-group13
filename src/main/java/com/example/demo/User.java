package com.example.demo;

public class User {
    private int timeOfLastWin;
    private String username;
    private String password;
    private String nickname;
    private int score;
    private int gold;
    private int turns;

    public User(String username, String nickname, String password) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        turns=0;
        gold=0;
        score = 0;
    }

    public int getGold() {
        return gold;
    }

    public int getTimeOfLastWin() {
        return timeOfLastWin;
    }

    public void setTimeOfLastWin(int timeOfLastWin) {
        this.timeOfLastWin = timeOfLastWin;
    }

    public int getTurns() {
        return turns;
    }

    public void setGold(int gold) {
        this.gold += gold;
    }

    public void setTurns(int turns) {
        this.turns += turns;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void increaseScore(int amount) {
        this.score += amount;
    }

    public void decreaseScore(int amount) {
        this.score -= amount;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", score=" + score +
                '}';
    }
}