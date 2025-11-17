import exceptions.*;
import models.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Main {
    static Player[] players = new Player[2];

    public static void main(String[] args) {
        Player player1 = new Player("Bonk", 4);
        Player player2 = new Player("Hoppe Trold", 30);
        LootCrate lootCrate1 = new LootCrate("Standard crate", 10);
        LootCrate lootCrate2 = new LootCrate("Diamond crate", 50);


        //Adding players to Array
        players[0] = player1;
        players[1] = player2;

        System.out.println("\n *** Stats on all users ***");
        displayStatsOnAllUsers();

        //error - player opens crate
        System.out.println("*** Hoppe Trold tries to open a Diamond crate ***");
        playerOpenCrate(player2, lootCrate2);

        //Evil highElf gives 30 credits
        int creditsGained = 30;
        System.out.println("\n*** Hoppe Trold slays an Evil HighElf and gains " + creditsGained + " credits ***");
        player2.addCredits(creditsGained); //adding 30 credits to Hoppe Trold

        //succes - Player opens crate (Hoppe Trold opens Diamond Crate)
        System.out.println("\n*** Hoppe Trold tries to open a Diamond Crate again ***");
        playerOpenCrate(player2, lootCrate2);

        //error - player adds negative credits
        System.out.println("\n*** Bonk tries to add a negative amount of credits ***");
        int creditsAdded = -10;
        addCreditsToPlayer(player1, creditsAdded);


        //PlayerNotFoundException
        System.out.println("\n*** Searching for a user by username ***");
        String wantedUser = "Santa";
        searchForPlayerByUsername(wantedUser);


        //Getting an exception when non-existing player tries to open crate
        System.out.println("\n*** Trying to have non-existent player open a crate ***");
        try {
            for (int i = 0; i <= players.length; i++) { //Intentionally using <= to get an ArrayIndexOutOfBoundsException
                playerOpenCrate(players[i], lootCrate1);
                System.out.println();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Player doesn't exist (and thus cannot open crate)");
        } catch (Exception e) {
            System.out.println("An error has occurred: " + e.getMessage());
        }


        System.out.println("\n*** Stats on all users ***");
        displayStatsOnAllUsers();

        System.out.println("*** Saving stats in txt file ***");
        saveStatsToTxt();
    }



    public static void playerOpenCrate(Player player, LootCrate lootCrate) {
        try {
            lootCrate.openCrate(player);
            System.out.println(player.getUsername() + " successfully opened a " + lootCrate.getIdentification() + ".");
        } catch (NotEnoughCreditsException e) {
            System.out.println(player.getUsername() + " doesn't have enough credits to open a " + lootCrate.getIdentification() + "." + "\nYou need " + lootCrate.getCredits() + " credits.\n" + player.getUsername() + " only has " + player.getCredits() + " credits.");
        } catch (Exception e) {
            System.out.println("An error has occurred: " + e.getMessage());
        }
    }


    public static void addCreditsToPlayer(Player player, int credits) {
        try {
            player.addCredits(credits);
            System.out.println(player.getUsername() + " added " + credits + " credits.");
        } catch (NegativeAmountException e) {
            System.out.println("Adding negative credits is not allowed. " + player.getUsername() + " tried adding " + credits + " credits.");
        } catch (Exception e) {
            System.out.println("An error has occurred: " + e.getMessage());
        }
    }

    public static void searchForPlayerByUsername(String username) {
        try {
            for (int i = 0; i < players.length; i++) {
                if (players[i].getUsername().equals(username)) {
                    System.out.println("User with username " + username + " found at index " + i + ".");
                } else {
                    throw new PlayerNotFoundException("Player with username " + username + " does not exist.");
                }
            }
        } catch (PlayerNotFoundException e) {
            System.out.println("Player " + username + " cannot be found.");
        } catch (Exception e) {
            System.out.println("An error has occurred: " + e.getMessage());
        }
    }

    public static void displayStatsOnAllUsers() {
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].displayStats() + "\n");
        }
    }

    public static void saveStatsToTxt() {
        try {
            for (int i = 0; i < players.length; i++) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(players[i].getUsername() + ".txt"));
                writer.write(players[i].displayStats());
                writer.close();
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
}