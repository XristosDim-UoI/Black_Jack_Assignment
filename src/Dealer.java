class Dealer
{
    private River river;
    private Hand hand;


    public Dealer(River river, Hand hand)
    {
        this.river = river;
        this.hand = hand;
    }

    public Hand getHand()
    {
        return hand;
    }

    public void draw()
    {
        hand.addCard(river.nextCard());
    }

    public void deal(Player player)
    {
        player.getHand().addCard(this.river.nextCard());
    }

    public void play()
    {
        System.out.println("Dealer Hand : "+ hand);
        while (hand.score() <17)
        {
            draw();
            System.out.println("Dealer Hand : "+ hand);
        }
    }

    public void settle(Player player)
    {
        if(this.hand.isBust())
        {
            player.wins();
        }
        else if(this.hand.score() > player.getHand().score())
        {
            player.loses();
        }
        else if(this.hand.score() < player.getHand().score())
        {
            player.wins();
        }
        else
        {
            System.out.println("None Wins | Tie with "+player.getCasinoCustomer());
        }
    }

    public String toString()
    {
        return "Dealer :" + hand;
    }

    public static void main(String[] args)
    {
        Dealer dealer = new Dealer(new River(1),new Hand());
        dealer.play();
        System.out.println(dealer);
        Player player = new Player(new CasinoCustomer("XristosDim",500),new Hand(),0);
        dealer.deal(player);
        dealer.deal(player);
        System.out.println(player);
        dealer.settle(player);

    }
}
