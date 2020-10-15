import java.util.Scanner;

class Player
{
    private CasinoCustomer casinoCustomer;
    private Hand hand;
    private double bet;

    public Player(CasinoCustomer casinoCustomer)
    {
        this.casinoCustomer = casinoCustomer;
    }

    public Player(CasinoCustomer casinoCustomer, Hand hand, double bet)
    {
        this.casinoCustomer = casinoCustomer;
        this.hand = hand;
        this.bet = bet;
    }

    public CasinoCustomer getCasinoCustomer()
    {
        return this.casinoCustomer;
    }

    public Hand getHand()
    {
        return this.hand;
    }

    public double getBet()
    {
        return this.bet;
    }

    public void wins()
    {
        System.out.println("Player "+ casinoCustomer +" won! | Collect : "+"$"+ bet );
        casinoCustomer.collectBet(bet);
    }

    public void winsBlackjack()
    {
        double blackJackWinBet = 1.5*bet;
        System.out.println("BlackJack ! | Player "+ casinoCustomer +" Collect : "+"$"+ blackJackWinBet);
        casinoCustomer.collectBet(blackJackWinBet);
    }

    public void loses()
    {
        System.out.println("Player "+ casinoCustomer +" lost ! | Pay : "+"$"+ bet );
        casinoCustomer.payBet(bet);
    }

    public void placeBet()
    {
        Scanner input = new Scanner(System.in);
        casinoCustomer.printState();
        System.out.println(casinoCustomer+ " Place your bet :");
        double playerBet = input.nextDouble();
        System.out.println();
        while(playerBet<1)
        {
            System.out.println("Bet has to be at least 1 $ | Input a new bet :");
            playerBet = input.nextDouble();
        }
        while (!casinoCustomer.canCover(playerBet))
        {
            System.out.println("No available money to make this bet ! | Input a new bet :");
            playerBet =  input.nextDouble();
        }
        bet =  playerBet;
    }

    public void doubleBet()
    {
        bet = 2*bet;
    }

    public boolean wantsToDouble()
    {
        if(!casinoCustomer.canCover(2*bet))
        {
            return false;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to double ? || Input : Yes/y or No/n ");
        String answer = input.next();
        return answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("Yes");
    }

    public boolean wantsToSplit()
    {
        if(!casinoCustomer.canCover(2*bet))
        {
            return false;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to split ? || Input : Yes/y or No/n ");
        String answer = input.next();
        return answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("Yes");
    }

    public String toString()
    {
        return casinoCustomer +" : "+ hand;
    }

    public static void main(String[] args) {
        CasinoCustomer casinoCustomer1 = new CasinoCustomer("XristosDim",50);
        Player player1 = new Player(casinoCustomer1);
        player1.placeBet();
        player1.getCasinoCustomer().printState();
        player1.wantsToSplit();
        player1.getCasinoCustomer().printState();
        player1.wantsToDouble();
        player1.getCasinoCustomer().printState();
        player1.wins();
        player1.getCasinoCustomer().printState();
        player1.winsBlackjack();
        player1.getCasinoCustomer().printState();
        player1.loses();
        player1.getCasinoCustomer().printState();
    }
}

