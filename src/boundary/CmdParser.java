/*
 * This is a simple parser class that is here instead of Andreas GUI
 * It took too long to implement into the gui, that I did not quite understand
 * so I made this isntead 
 *
 * This is temporary, so there is no form of validation in this class
 */

package boundary;

import control.Controller;
import interfaces.GameAbilities;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author casper
 */
public class CmdParser {
    
    Scanner scanner;
    GameAbilities ctrl;
    int roundsPlayed;
    
    public CmdParser() {
        ctrl = Controller.getInstance();
        scanner = new Scanner(System.in);
        roundsPlayed = 0;
        startGame();
        
        
        
        endGame();
    }
    
    /**
     * Starts up a new game
     */
    public void startGame() {
        
        // Start new game
        System.out.println("Welcome - start the game by typing in your name");
        String name = scanner.next();
        ctrl.startNewGame(name);
        
        // List the player info
        printPlayerInfo();  
        
        do {
            playRound();
            roundsPlayed ++;
        } while(roundsPlayed < 20);
    }
    
    /**
     * Ends a game and calls the method that prints the highscore
     */
    private void endGame() {
        System.out.println("You finished the game!");
        System.out.println("Your final score was: " + ctrl.getPlayerCredits());
        ctrl.endGame();
        
        // Print highscore
        printHighscore();
        
        startGame();
    }
    
    /**
     * Prints the highscore
     */
    private void printHighscore() {
        for (Map.Entry<Integer, String> entry : ctrl.highscore().entrySet())
        {
            System.out.format("%-20s %s", "Score: " + entry.getKey(), "Plaer: " + entry.getValue());
        }
    }
    
    /**
     * Play an entire round, where the user gets to travel, buy and sell drugs
     */
    private void playRound() {
        printPlayerInfo();
        System.out.println("");
        // List current country and inventory
        System.out.println("You are in: " + ctrl.getCurrentCountry());
        
        // If not first round - let the user buy some drugs
        if (roundsPlayed > 0) {
            System.out.println("Would you like to buy some drugs? - (y / n)");
            
            if (scanner.next().equals("y")) {
                
                boolean buying = true;
                
                do {
                    System.out.println("You have the following options:");
                    for (int i = 0, availableDrugs = ctrl.getAvailableDrugs().size(); i < availableDrugs ;i++) {
                        System.out.println("Drug: " + ctrl.getAvailableDrugs().get(i).getName() + " -  (number: " + i + ")");
                    }

                    System.out.println("");
                    System.out.println("What would you like to buy? (type the drug number / n to stop buying)");
                    
                    String cmd = scanner.next();

                    if (!cmd.equals("n")) {
                        int index = Integer.parseInt(cmd);
                        
                        System.out.println("How many would you like to buy? (integer)");
                        int amount = scanner.nextInt();
                        ctrl.buy(index, amount);
                        
                    } else {
                        buying = false;
                    }
                    
                } while (buying);
                 
            }
        }
        
        if (ctrl.getInventory().size() > 0) {
            System.out.println("Would you like to sell any drugs? - (y / n)");
            
            if (scanner.next().equals("y")) {
                
                boolean selling = true;
                
                do {
                    System.out.println("You have the following in you inventory:");
                    for (int i = 0, inventorySize = ctrl.getInventory().size(); i < inventorySize ;i++) {
                        System.out.println("Drug: " + ctrl.getInventory().get(i).toString() + " -  (number: " + i + ")");
                    }

                    System.out.println("");
                    System.out.println("What would you like to sell? (type the drug number / n to stop selling)");
                    
                    String cmd = scanner.next();
                    
                    if (!cmd.equals("n")) {
                        
                        int index = Integer.parseInt(cmd);
                        
                        System.out.println("How many would you like to sell? (Max: " + ctrl.getDrugAmount(index) + ")");
                        int amount = scanner.nextInt();
                        ctrl.sell(index, amount);
                        
                    } else {
                        selling = false;
                    }
                    
                } while (selling);
                 
            }
        }
        
        System.out.println("");
        // Let the user decide where he wants to travel
        System.out.println("Where would you like to travel to?");
        for (int i = 0, countries = ctrl.getCountries().size(); i < countries ;i++) {
            if (!ctrl.getCountries().get(i).getName().equals(ctrl.getCurrentCountry())) {
                System.out.println(i + " - " + ctrl.getCountries().get(i).getName());
            }
        }
        
        int index = scanner.nextInt();
        
        ctrl.travelTo(index);
        
    }
    
    /**
     * Print player information
     */
    private void printPlayerInfo() {
        System.out.println("Playername: " + ctrl.getPlayerName());
        System.out.println("Healt: " + ctrl.getPlayerHealth());
        System.out.println("Credits: " + ctrl.getPlayerCredits());
    }
}
