package com.example.triviaapp.model;

public class Question {
    private String pitanje;
    private boolean tacnost;

    public Question() {
    }

    public Question(String pitanje, boolean tacnost) {
        this.pitanje = pitanje;
        this.tacnost = tacnost;
    }

    public String getPitanje() {
        return pitanje;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public boolean isTacnost() {
        return tacnost;
    }

    public void setTacnost(boolean tacnost) {
        this.tacnost = tacnost;
    }

    @Override
    public String toString() {
        return "Question{" +
                "pitanje='" + pitanje + '\'' +
                ", tacnost=" + tacnost +
                '}';
    }
}
