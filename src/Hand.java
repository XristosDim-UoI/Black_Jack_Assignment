import java.util.ArrayList;

class Hand
{
    private ArrayList<Card> handCards = new ArrayList<Card>();


    public void addCard(Card card)
    {
        handCards.add(card);
    }

    public int score()
    {
        int score = 0;
        int acesCount =0;
        boolean firstAceCountedAs11 = false;
        for(Card element : handCards)
        {
           if(element.isAce())
           {
               if(score +11 >21 && acesCount==0)
               {
                   score += 1;
               }
               else if(acesCount == 1 && firstAceCountedAs11)
               {
                   score += 1;
               }
               else
                   {
                       score += 11;
                       firstAceCountedAs11 = true;
                   }
               acesCount++;
               continue;
           }
           score += element.getValue();

            if(acesCount==1 && firstAceCountedAs11 && score>21)
            {
                score -= 10 ;
                firstAceCountedAs11=false;
            }
        }
        return score;
    }

    public boolean canSplit()
    {
        return handCards.get(0).equals(handCards.get(1));
    }

    public Hand [] split()
    {
        Hand [] splitHand = new Hand[2];
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();
        hand1.addCard(this.handCards.get(0));
        hand2.addCard(this.handCards.get(1));
        splitHand[0] = hand1;
        splitHand[1] = hand2;
        return splitHand;
    }

    public boolean isBlackjack()
    {
        return this.handCards.size()==2 && this.score()==21;

    }

    public boolean isBust()
    {
        return score()>21;
    }

    public String toString()
    {
        String result = "";
        String score= "";
        int count =0;
        for(Card element : handCards)
        {
            result += element +" ";
            if(element.isAce())
            {
                count++;
            }
        }
        //kwdikas gia na tupwnete kai to score ektos apo to xeri :
        if(count==1 && handCards.size()==1)
        {
            score = 1 +"/"+ score();
        }
        else if(count==1)
        {
            int count2=0;
            for(Card element : handCards)
            {
                if(!element.getCardString().equals("A"))
                {
                    count2 += element.getValue();
                }
            }
            if(count2<=10)
            {
                score = score() - 10 + "/" + score();
            }
            else
            {
                score = score()+"";
            }
        }
        else
        {
            score = score()+"";
        }

        return result + "   Score : "+score;
    }

    public ArrayList<Card> getHandCards()
    {
        return handCards;
    }




    public static void main(String[] args)
    {
        Hand hand = new Hand();
        hand.addCard(new Card("A"));
        hand.addCard(new Card("A"));
        System.out.println(hand);
        System.out.println(hand.score());
        System.out.println(hand.canSplit());
        Hand [] splitHand = hand.split();
        System.out.println(splitHand[0]);
        System.out.println(splitHand[1]);
        splitHand[0].addCard(new Card("K"));
        System.out.println(splitHand[0]);
        System.out.println(splitHand[0].score());
        System.out.println(splitHand[0].isBlackjack());
        splitHand[0].addCard(new Card("A"));
        System.out.println(splitHand[0]);
        System.out.println(splitHand[0].score());
        splitHand[0].addCard(new Card("10"));
        System.out.println(splitHand[0]);
        System.out.println(splitHand[0].score());
        System.out.println(splitHand[0].isBust());
    }

}
