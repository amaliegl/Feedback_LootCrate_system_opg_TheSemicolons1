package models;

import exceptions.NegativeAmountException;
import exceptions.NotEnoughCreditsException;

public class Player {
    private final String username;
    private int credits;

    public Player(String username, int credits){
        this.username = username;
        this.credits = credits;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public int getCredits(){
        return this.credits;
    }
    
    public String displayStats(){
        return ("Username: " + getUsername() + "\nCredits: " + getCredits());
    }
    
    public void addCredits(int addCredits) throws NegativeAmountException {
        if (addCredits < 0) {
            throw new NegativeAmountException("Attempting to add negative credits");
        } else {
            this.credits += addCredits;
        }
    }
    
    public void spendCredits(int credits) throws NotEnoughCreditsException {
        if (this.credits < credits) {
            throw new NotEnoughCreditsException("Not enough credits to spend");
        } else {
            this.credits -= credits;
        }
    }

}
