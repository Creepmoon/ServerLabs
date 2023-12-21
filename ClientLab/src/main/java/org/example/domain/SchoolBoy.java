package org.example.domain;

import com.github.javafaker.Faker;

import java.io.Serializable;

public class SchoolBoy  implements Serializable{

    Faker faker = new Faker();

    public SchoolBoy( String name, int minScore, double averageScore, int maxScore) {
        Name = name;
        MinScore = minScore;
        AverageScore = averageScore;
        MaxScore = maxScore;
    }
    public SchoolBoy(){}

    private String Name = faker.name().firstName();

    private int MinScore = faker.number().numberBetween(0,3);

    private double AverageScore =faker.number().randomDouble(3,3,4);

    private int MaxScore = faker.number().numberBetween(4,6);


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
