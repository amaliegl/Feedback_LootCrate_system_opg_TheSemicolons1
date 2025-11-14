package models;

import exceptions.NotEnoughCreditsException;

public class LootCrate {
    private final String identification;
    private final int credits;

    public LootCrate(String identification, int credits){
        this.identification = identification;
        this.credits = credits;
    }

    public String getIdentification() {
        return this.identification;
    }

    public int getCredits(){
        return this.credits;
    }

    public void openCrate(Player player) throws NotEnoughCreditsException {
        player.spendCredits(this.credits);
    }
}
