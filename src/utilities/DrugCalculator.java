/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.Random;

/**
 *
 * @author Nikolaj
 */
public class DrugCalculator {

    private final Random random;

    public DrugCalculator() {
        random = new Random();
    }

    public double randomizeBasePrice(double value) {
        
        double percent = random.nextInt(85) + 1;
        double newPrice = 0;

        if (random.nextBoolean()) {
            newPrice = decreaseByPercent(value, percent);
        } else {
            newPrice = increaseByPercent(value, percent);
        }
        return Math.ceil(newPrice);
    }

    public double randomizeBaseAvailability(double value) {
        double percent = random.nextInt(35) + 15;
        double newPrice = 0;

        if (random.nextBoolean()) {
            newPrice = decreaseByPercent(value, percent);
        } else {
            newPrice = increaseByPercent(value, percent);
        }
        return Math.ceil(newPrice);
    }
    
    private double decreaseByPercent(double value, double percent) {
        return value - (value * (percent / 100));
    }

    private double increaseByPercent(double value, double percent) {
        return value * ((percent / 100) + 1);
    }
}
