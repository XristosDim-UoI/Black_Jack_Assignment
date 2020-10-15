import java.util.Scanner;

class BlackJackTable
{
    private River river;
    private CasinoCustomer[] casinoCustomers;
    private int tableParticipantsNumber;

    public BlackJackTable(int participantsNumber)
    {
        this.tableParticipantsNumber = participantsNumber;
        this.river = new River(6);
        creatCasinoCustomer();

    }

    private void creatCasinoCustomer()
    {
        Scanner input = new Scanner(System.in);
        casinoCustomers = new CasinoCustomer[tableParticipantsNumber];
        for(int i = 0; i< tableParticipantsNumber; i++)
        {
            System.out.println("Give name and available money of customer "+(i+1));
            String name = input.next();
            double money = input.nextDouble();
            this.casinoCustomers[i] = new CasinoCustomer(name,money);
        }
    }

    public void play()
    {
        int roundsNumber = 1;
        while (true)
        {
            if(tableParticipantsNumber==0)
            {
                System.out.println("Next round has 0 participants !\n");
                return;
            }
            if(roundsNumber>1)
            {
                /**
                 * methodos h opoia elegxei gia ton kathe paitki an thelei na synexisei ston epomeno gyro
                 * h ean thelei na apoxwrhsei apo to painidi
                 * se periptwsh pou o paiktis exei katw apo $1 ton rwtaei an thelei na prosthesei lefta h ean thelei
                 * na apoxwrhsei apo to paixnidi
                 *
                 */
                nextRoundJoinCheck();
            }
            Round round = new Round(this.river);
            for (CasinoCustomer element : casinoCustomers)
            {
                round.addPlayer(element);
            }
            if(this.river.shouldRestart())
            {
                this.river.restart();
            }
            System.out.println("----- ROUND "+roundsNumber+" -----");
            if(tableParticipantsNumber!=0)
            {
                round.playRound();
            }
            roundsNumber++;
            System.out.println();
        }


    }


    private void nextRoundJoinCheck()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("---------- ROUND ENDED ----------\n");
        for(CasinoCustomer element : casinoCustomers)
        {
            if(element.isBroke())
            {
                element.printState();
                System.out.println(element+" --> You dont have enough money to join next round ! ||"+
                        " Chose one of the following options :     (Input 1 or 2 )\n"+
                        "1) Add money\n"+
                        "2) Leave table");
                switch (input.nextInt())
                {

                    case 1:
                        System.out.println(element+" --> Input the amount of money you want to add :"+
                                "    Note: Add at least $1 or you will be removed from next round automatically !");
                        int answer = input.nextInt();
                        if(answer>=1)
                        {
                            element.setMoneyState(element.getMoneyState() + answer);
                            System.out.println("Done ! Available money now are $" + element.getMoneyState() + "\n");
                            break;
                        }
                    case 2 :
                        CasinoCustomer [] updatedCasinoCustomers = new CasinoCustomer[tableParticipantsNumber-1];
                        int count =0;
                        for(CasinoCustomer element2 : casinoCustomers)
                        {
                            if(element2.equals(element))
                            {
                                continue;
                            }
                            updatedCasinoCustomers[count] = element2;
                            count++;
                        }
                        casinoCustomers = updatedCasinoCustomers;
                        tableParticipantsNumber--;
                        break;
                    default:
                        System.out.println("Unexpected input || Forced program end ");
                        System.exit(-1);
                        break;
                }
            }
            else
                {
                    System.out.println(element+" --> Chose one of the following options :  (Input 1 or 2 )\n"+
                            "1) Join next round\n"+
                            "2) Leave table");
                    switch (input.nextInt())
                    {
                        case 1:
                            break;
                        case 2:
                            CasinoCustomer [] updatedCasinoCustomers = new CasinoCustomer[tableParticipantsNumber-1];
                            int count =0;
                            for(CasinoCustomer element2 : casinoCustomers)
                            {
                                if(element2.equals(element))
                                {
                                    continue;
                                }
                                updatedCasinoCustomers[count] = element2;
                                count++;
                            }
                            casinoCustomers = updatedCasinoCustomers;
                            tableParticipantsNumber--;
                            break;
                        default:
                            System.out.println("Unexpected input || Forced program end ");
                            System.exit(-1);
                            break;
                    }
                }
        }
    }


    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number participants :");
        int participantsNumber = input.nextInt();
        BlackJackTable table = new BlackJackTable(participantsNumber);
        table.play();
        System.out.println("----- END GAME -----");

        /**
         *
         *
         *
         * Xristos Dimitresis      A.M : 4351
         *
         *
         *
         */
    }

}

