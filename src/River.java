import java.util.Random;

class River
{
    private Card [] cards;
    private int numberOfCards;
    private int cardsLeft;

    public River(int numberOfPacks)
    {
        numberOfCards = numberOfPacks*52;
        cardsLeft = numberOfCards;
        cards =  new Card[numberOfCards];

        int count = 0;
        int j = 0;
        while(count<numberOfPacks)
        {
            for (int i=0; i < 52; i++) {
                if (i < 4) {
                    cards[i+j] = new Card("2");
                } else if (i < 8) {
                    cards[i+j] = new Card("3");
                } else if (i < 12) {
                    cards[i+j] = new Card("4");
                } else if (i < 16) {
                    cards[i+j] = new Card("5");
                } else if (i < 20) {
                    cards[i+j] = new Card("6");
                } else if (i < 24) {
                    cards[i+j] = new Card("7");
                } else if (i < 28) {
                    cards[i+j] = new Card("8");
                } else if (i < 32) {
                    cards[i+j] = new Card("9");
                } else if (i < 36) {
                    cards[i+j] = new Card("10");
                } else if (i < 40) {
                    cards[i+j] = new Card("J");
                } else if (i < 44) {
                    cards[i+j] = new Card("Q");
                } else if (i < 48) {
                    cards[i+j] = new Card("K");
                } else
                {
                    cards[i+j] = new Card("A");
                }
            }
            count++;
            j += 52;
        }
    }


    public Card nextCard()
    {
        if(cardsLeft == 0)
        {
            return null;
        }
        Random random = new Random();
        int nextCard = random.nextInt(cardsLeft);
        Card swap = cards[nextCard];
        cards[nextCard] = cards[cardsLeft-1];
        cards[cardsLeft-1] = swap;
        cardsLeft--;
        return swap;

    }

    public boolean shouldRestart()
    {
        return cardsLeft < numberOfCards/4;
    }

    public void restart()
    {
        cardsLeft = numberOfCards;
    }

    public static void main(String[] args)
    {
        River river = new River(1);
        while(!river.shouldRestart())
        {
            System.out.print(river.nextCard()+" ");
        }
        river.restart();
        System.out.println();
        while (true)
        {
            Card card = river.nextCard();
            System.out.print(card +" ");
            if(card==null)
            {
                break;
            }
        }
    }
}
