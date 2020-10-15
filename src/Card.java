class Card
{
    private String cardString;
    private int cardValue;


    public Card(String card)
    {
        this.cardString = card;
        switch (card)
        {
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "10":
                this.cardValue = Integer.parseInt(card);
                break;
            case "J":
            case "Q":
            case "K":
                this.cardValue = 10;
                break;
            case "A":
                this.cardValue = 1;
        }
    }

    public int getValue()
    {
        return cardValue;
    }

    public boolean isAce()
    {
        return cardValue == 1;
    }

    public boolean equals(Card other)
    {
        return this.cardString.equals(other.cardString);
    }

    public String toString()
    {
        return cardString + "";
    }

    public String getCardString()
    {
        return cardString;
    }


}
