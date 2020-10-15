class CasinoCustomer
{
    private String playerName;
    private double moneyState;


    public CasinoCustomer(String playerName, double moneyState)
    {
        this.playerName = playerName;
        this.moneyState = moneyState;
    }

    public void payBet(double bet)
    {
        moneyState -= bet;
    }

    public void collectBet(double bet)
    {
        moneyState += bet;
    }

    public boolean canCover(double bet)
    {
        return moneyState >= bet;
    }

    public boolean isBroke()
    {
        return moneyState < 1;
    }

    public String toString()
    {
        return playerName;
    }

    public void printState()
    {
        System.out.println("Player : "+ playerName +"  |  Money Left : "+"$"+ moneyState);
        System.out.println();
    }

    public void setPlayerName(String newName)
    {
        this.playerName = newName;
    }

    public double getMoneyState()
    {
        return moneyState;
    }

    public void setMoneyState(double moneyState)
    {
        this.moneyState = moneyState;
    }


    public boolean equals(CasinoCustomer other)
    {
        return  this.playerName.equals(other.playerName);
    }

}
