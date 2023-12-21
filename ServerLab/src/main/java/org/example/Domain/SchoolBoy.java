package org.example.Domain;

import java.io.Serializable;

public class SchoolBoy  implements Serializable{

    public SchoolBoy( String name, int minScore, double averageScore, int maxScore) {
        Name = name;
        MinScore = minScore;
        AverageScore = averageScore;
        MaxScore = maxScore;
    }
    public SchoolBoy(){}

    private String Name;

    private int MinScore;

    private double AverageScore;

    private int MaxScore;


    public int getMaxScore() {
        return MaxScore;
    }

    public void setMaxScore(int maxScore) {
        MaxScore = maxScore;
    }

    public int getMinScore() {
        return MinScore;
    }

    public void setMinScore(int minScore) {
        MinScore = minScore;
    }

    public double getAverageScore() {
        return AverageScore;
    }

    public void setAverageScore(double averageScore) {
        AverageScore = averageScore;
    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
