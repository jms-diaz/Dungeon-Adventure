package io.diaz;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        String[] enemies = {"Undead", "Dullahan", "Wraith", "Ogre"};
        int maxEnemyHealth = 75;
        int maxEnemyAttackDamage = 25;

        int health = 100;
        int maxPlayerAttackDamage = 50;
        int potionHealAmount = 25;
        int potionDropChance = 50;
        int numHealthPotions = 3;

        int score=0;

        boolean running = true;

        System.out.println("Welcome to the dungeon, challenger!");

        GAME:
        while (running) {
            System.out.println("------------------------------------------------");

            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemyName = enemies[rand.nextInt(enemies.length)];
            System.out.println("\t# " + enemyName + " has appeared! #\n");

            while (enemyHealth > 0 ){
                System.out.println("\tYour HP: " + health);
                System.out.println("\t" + enemyName + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion");
                System.out.println("\t3. Run!");

                String input = in.nextLine();
                switch (input) {
                    case "1":
                        int damageDealt = rand.nextInt(maxPlayerAttackDamage);
                        int damageTaken = rand.nextInt(maxEnemyAttackDamage);

                        enemyHealth -= damageDealt;
                        health -= damageTaken;

                        System.out.println("\t> You strike the " + enemyName + " for " + damageDealt + " damage.");
                        System.out.println("\t> You receive " + damageTaken + " in return!");

                        if (health < 1) {
                            System.out.println("\t> You have taken too much damage, you are too weak to go on!");
                            break;
                        }
                        break;
                    case "2":
                        if (numHealthPotions > 0) {
                            numHealthPotions--;
                            health +=potionHealAmount;
                            System.out.println("\t> You drink a health potion, healing yourself for " + potionHealAmount + ". "
                                + "\n\t> You now have " + health + " HP."
                                + "\n\t> You have " + numHealthPotions + " potion(s) remaining.");
                        } else {
                            System.out.println("\tYou have no potions left! Defeat enemies for a chance to get a potion.");
                        }
                        break;
                    case "3":
                        System.out.println("\tYou run away from the " + enemyName + "!");
                        continue GAME;
                    default:
                        System.out.println("Invalid input!");
                }
                if (health < 1) {
                    System.out.println("\t> You limp out of the dungeon, weak from battle.");
                    System.out.println(" ***** TOTAL SCORE: " + score + "  *****");
                    break GAME;
                }
            }
            score+=30;
            System.out.println("------------------------------------------------");
            System.out.println(" # " + enemyName + " was defeated! # ");
            System.out.println(" # You have " + health + " HP left. # ");

            if (rand.nextInt(100) < potionDropChance) {
                numHealthPotions++;
                System.out.println(" # The " + enemyName + " dropped a health potion! # ");
                System.out.println("You now have " + numHealthPotions + " health potion(s)");
            }
            System.out.println("------------------------------------------------");
            System.out.println("What would you like to do now?");
            System.out.println("1. Continue fighting");
            System.out.println("2. Exit dungeon");

            String input = in.nextLine();

            while(!input.equals("1") && !input.equals("2")) {
                System.out.println("Invalid command");
                input = in.nextLine();
            }

            if (input.equals("1")) {
                System.out.println("You continue on your adventure");
                System.out.println("CURRENT SCORE: " + score);
            } else if(input.equals("2")) {
                System.out.println("You exit the dungeon from your adventures.");
                System.out.println("***** TOTAL SCORE: " + score + " *****");
                break;
            }

        }
    }
}
