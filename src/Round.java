import java.util.ArrayList;
import java.util.Scanner;

class Round
{
    private Dealer dealer;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Player> noSettledPlayers = new ArrayList<Player>();

    public Round(River river)
    {
        this.dealer = new Dealer(river, new Hand());

    }

    public void addPlayer(CasinoCustomer casinoCustomer)
    {
        Player player =  new Player(casinoCustomer, new Hand() , 0);
        this.players.add(player);
    }


    private void playNormalHand(Player player)
    {
        Scanner input = new Scanner(System.in);
        while (true)
        {
            System.out.println("Hit ?  || Input: Yes/y or No/n");
            String answer = input.next();
            if(!(answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("y")))
            {
                break;
            }
            dealer.deal(player);
            System.out.println(player);
            if(player.getHand().isBust())
            {
                player.loses();
                return;
            }
        }
        this.noSettledPlayers.add(player);
        System.out.println();

    }



    private void playDoubleHand(Player player)
    {
        player.doubleBet();
        dealer.deal(player);
        System.out.println(player.getHand());
        if(player.getHand().isBust())
        {
            player.loses();
        }
        else
        {
            this.noSettledPlayers.add(player);
        }
    }

    private void playSplitHand(Player player, Hand [] splitHand)
    {
        Player player1 = new Player(player.getCasinoCustomer(), splitHand[0] , player.getBet());
        Player player2 = new Player(player.getCasinoCustomer(), splitHand[1] , player.getBet());
        System.out.println("----------  Hand 1  ----------");
        System.out.println(player1);
        playNormalHand(player1);
        System.out.println("\n----------  Hand 2  ----------");
        System.out.println(player2);
        playNormalHand(player2);
    }

    private void playPlayer(Player player)
    {
        System.out.println("\n------------------------");
        System.out.println("Now plays --> "+player);
        if(player.getHand().canSplit() && player.wantsToSplit())
        {
            Hand [] splitHand = player.getHand().split();
            playSplitHand(player,splitHand);
        }
        else if(player.wantsToDouble())
        {
            playDoubleHand(player);

        }
        else
        {
            playNormalHand(player);
        }
    }

    public void playRound()
    {
        for(Player element : players)
        {
            element.placeBet();
        }
        for(Player element : players)
        {
            dealer.deal(element);
        }
        dealer.draw();
        System.out.println("------------------------");
        System.out.println("Dealer : "+dealer.getHand() +"\n");
        for(Player element : players)
        {
            dealer.deal(element);
        }
        dealer.draw();
        for(Player element : players)
        {
            System.out.println(element);
        }
        if(dealer.getHand().isBlackjack())
        {
            for(Player element : players)
            {
                if(!element.getHand().isBlackjack())
                {
                    System.out.println(dealer);
                    System.out.println("\nDealer has BlackJack !\n");
                    element.loses();
                }
                else
                {
                    System.out.println(dealer);
                    System.out.println("Both "+element.getCasinoCustomer()
                            +" & dealer have BlackJack | None wins !");
                }
            }
            return;
        }
        for(Player element : players)
        {
            if(element.getHand().isBlackjack())
            {
                element.winsBlackjack();
            }
            else
            {
                playPlayer(element);
            }
        }

        if(this.noSettledPlayers.size()==0)
        {
            return;
        }
        System.out.println("----- Dealer's turn -----");
        dealer.play();
        for(Player element : noSettledPlayers)
        {
            dealer.settle(element);

        }
    }


    public static void main(String[] args)
    {
      Round round = new Round(new River(6));
      round.addPlayer(new CasinoCustomer("XristosDim",100));
      round.playRound();
    }

}
