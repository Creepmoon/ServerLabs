package org.example.domain;

public class GoalsPerSeason {




        public void setGoals(int goals) {
            this.goals = goals;
        }

        private int goals ;

        public void setAverageGoals(double averageGoals) {
            this.averageGoals = averageGoals;
        }

        private double averageGoals;

        public int getGoals() {
            return goals;
        }

        public double getAverageGoals() {
            return averageGoals;
        }
    }


