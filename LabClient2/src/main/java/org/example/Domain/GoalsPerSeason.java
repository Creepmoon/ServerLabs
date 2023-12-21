package org.example.Domain;

import com.github.javafaker.Faker;

public class GoalsPerSeason {

    Faker faker = new Faker();

    public void setGoals(int goals) {
        this.goals = goals;
    }

    private int goals = faker.number().numberBetween(10,20);

    public void setAverageGoals(double averageGoals) {
        this.averageGoals = averageGoals;
    }

    private double averageGoals = faker.number().randomDouble(2,0,2);

    public int getGoals() {
        return goals;
    }

    public double getAverageGoals() {
        return averageGoals;
    }
}
