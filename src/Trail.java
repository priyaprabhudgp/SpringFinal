import java.util.Random;
import java.util.Scanner;

/**
 * Trail Class that has all events that happen as user travels the trail
 */
class Trail {


    public String[] Locations;
    public int CurrentStop = 0;
    public int TotalDays = 0;

    /**
     * Trail Constructor - defines locations
     */
    public Trail() {
        Locations = new String[]
                {
                        "Forks High School",
                        "Portland, Oregon",
                        "Phoenix, Arizona",
                        "Florida",
                        "Volterra, Italy"
                };


    }

    /**
     * Third Option Method - Different possibilities to show luck on trail
     * @param person - leader
     */
    public void TakeAChance(Person person) {
        Random random = new Random();

        int chance = random.nextInt(4);
        if (chance == 0) // loose food
        {
            int foundFood = GenerateRandomNumber(10, 100);
            if (random.nextInt(4) % 2 == 0) {
                person.food -= foundFood;
                if (person.food < 0) {
                    System.out.println("You run out of blood. ");
                    KillAPerson(person);
                    //Cannot have negative food!
                    person.food = 0;
                }
            } else {
                int newAmmuntofFood = person.food + foundFood;
                //Checks to keep blood under limit
                if (newAmmuntofFood > 600) {
                    person.food = 600;
                } else {
                    person.food += foundFood;
                }

                System.out.println("You found " + foundFood + " liters of extra blood! Wow!");

            }

        }
        if (chance == 1) // find oxen
        {
            if (random.nextInt(4) % 2 == 0) {
                int foundOxen = GenerateRandomNumber(2, 4);
                System.out.println("You find " + foundOxen + " friendly vampire clans. They give you some oxen. How lucky...");
                person.oxen += foundOxen;
            } else {
                if (person.oxen > 0) {
                    person.oxen -= GenerateRandomNumber(1, 3);
                    System.out.println("You stepped on another vampire clan's hunting ground, and they saw Edward and Bella trying to recreate the Spider-Monkey Scene. Oh my you have to give up some oxen.");
                } else {
                    Scanner temp = new Scanner(System.in);
                    System.out.println("If you answer this question correctly you can earn back an oxen:");
                    System.out.println("What is your favorite book series? Remember to answer in all caps!");
                    String ans  = temp.nextLine();
                    if(ans.equals("TWILIGHT")){person.oxen++;}
                    else{System.out.println("WRONG, IT'S TWILIGHT!");}
                }
                //deal with negatives
                if (person.oxen < 0) {
                    person.oxen = 0;
                }
            }
        }
        if (chance == 2) // find money
        {
            int moneyFound = GenerateRandomNumber(10, 50);
            if (random.nextInt(4) % 2 == 0) {
                if (person.money > 0) {
                    person.money -= moneyFound;
                    System.out.println("You were robbed... sorry about that. (You lost $" + moneyFound + ")");
                } else {
                    System.out.println("You've lost all your money!");
                    person.money = 0;
                }
            } else {
                person.money += moneyFound;
                System.out.println("You found $" + moneyFound + " just laying on the ground... interesting.");
            }

            System.out.println("You currently have $" + person.money);

        }
        if (chance == 3) // kill a person
        {
            KillAPerson(person);
        }
    }

    /**
     * Kills a person unless they are dead already
     * @param person - wagon member
     */
    public void KillAPerson(Person person) {
        Random random = new Random();

        int luckyPerson = GenerateRandomNumber(1, person.companions.length);
        if (person.companions[luckyPerson].alive) {
            person.companions[luckyPerson].alive = false;

            if (random.nextInt(4) % 2 == 0) {
                System.out.println(person.companions[luckyPerson].name + " lost control and attacked a human. This vampire has been ejected.");
            } else {
                System.out.println(person.companions[luckyPerson].name + " was seen in the sun, shining bright like a diamond, by a group of humans.  This vampire has been ejected.");
            }
        } else {
            System.out.println("You wasted time.");
        }
    }

    /**
     * First Option - Continuing to next stop, storyline for events that happen
     * @param person - leader
     */
    public void NextStop(Person person) {
        System.out.println("\n");
        PrintStats(person);


        CurrentStop++;
        CurrentStop = CurrentStop % 5;

        // move random amount of days no more than 10
        // party consumes 25lbs per day

        int daysToTravel = GenerateRandomNumber(1, 10);
        TotalDays += daysToTravel;
        int foodConsumed = 25 * daysToTravel;

        person.food -= foodConsumed;
        if (person.food < 0) {
            KillAPerson(person);
            person.food = 0;
        }

        System.out.println("You travel " + daysToTravel + " days to reach the next stop.");
        System.out.println("Your party consumes " + foodConsumed + "liters of blood along the way. You have " + person.food + "liters of blood left.");

        System.out.println("\n");
    }

    /**
     * Prints game stats for user
     * @param person
     */
    public void PrintStats(Person person) {
        System.out.println("Round Stats:");
        System.out.println("Number of days on the Trail: " + TotalDays);
        System.out.println("Amount of Food in the Wagon " + person.food);
        System.out.println("Number of Oxen: " + person.oxen);
        System.out.println("Amount of Money: " + person.money);
        System.out.println("Last stop visited: " + Locations[CurrentStop]);

        System.out.println("Active Companions: " + person.GetActiveCompanions());

    }

    /**
     * Second Option - Hunting
     * Uses trivia to reward user with food
     * @param person - leader
     */

    public void Hunt(Person person) {
        int foodHunted = GenerateRandomNumber(5, 200);
        Scanner in = new Scanner(System.in);
        System.out.println("\n\nTo hunt you must do trivia!!!!");

        String[] questions = {"In what class does Bella sit next to Edward on the first day of school?",
                "How do the Cullens differentiate themselves from other vampires?",
                "How many (adopted) Cullen children are there?",
                "The Denali vampires shared what with the Cullens?",
                "Can Edward read Bella's mind?",
                "What do vampires really need in order to play baseball?",
                "How did Stephenie Meyer come up with the idea for the \"Twilight\" books?",
                "Where did Bella have to hide out from James in \"Twilight?\"",
                "What part of Bella is bitten by James, prompting Edward to heroically suck the poison out?",
                "How does Edward attempt to kill himself in \"New Moon?\"",
                "How do shape-shifters, like Jacob, find their soulmates?",
                "What age does Bella get married at?",
                "What is the name of the other peaceful half-human, half-vampire that proves that Renesmee isn't a threat?"};

        String[] answers = {"BIOLOGY",
                "VEGETARIAN",
                "FIVE",
                "VEGETARIANISM",
                "NO",
                "THUNDER",
                "DREAM",
                "ARIZONA",
                "WRIST",
                "SUNSHINE",
                "IMPRINTING",
                "NINETEEN",
                "NAHUEL"};


        int r = GenerateRandomNumber(0, 12);
        System.out.println("\nThe answer will will be ONE WORD in ALL CAPS!!\n");
        System.out.println(questions[r]);
        String response = in.nextLine();
        if(response.equals(answers[r])){
            System.out.println("                                                                      ,----,\n" +
                    "              ,----..                                               ,/   .`|\n" +
                    "  ,----..    /   /   \\ ,-.----.  ,-.----.      ,---,. ,----..     ,`   .'  :\n" +
                    " /   /   \\  /   .     :\\    /  \\ \\    /  \\   ,'  .' |/   /   \\  ;    ;     /\n" +
                    "|   :     :.   /   ;.  ;   :    \\;   :    \\,---.'   |   :     .'___,/    ,' \n" +
                    ".   |  ;. .   ;   /  ` |   | .\\ :|   | .\\ :|   |   ..   |  ;. |    :     |  \n" +
                    ".   ; /--`;   |  ; \\ ; .   : |: |.   : |: |:   :  |-.   ; /--`;    |.';  ;  \n" +
                    ";   | ;   |   :  | ; | |   |  \\ :|   |  \\ ::   |  ;/;   | ;   `----'  |  |  \n" +
                    "|   : |   .   |  ' ' ' |   : .  /|   : .  /|   :   .|   : |       '   :  ;  \n" +
                    ".   | '___'   ;  \\; /  ;   | |  \\;   | |  \\|   |  |-.   | '___    |   |  '  \n" +
                    "'   ; : .'|\\   \\  ',  /|   | ;\\  |   | ;\\  '   :  ;/'   ; : .'|   '   :  |  \n" +
                    "'   | '/  : ;   :    / :   ' | \\.:   ' | \\.|   |    '   | '/  :   ;   |.'   \n" +
                    "|   :    /   \\   \\ .'  :   : :-' :   : :-' |   :   .|   :    /    '---'     \n" +
                    " \\   \\ .'     `---`    |   |.'   |   |.'   |   | ,'  \\   \\ .'               \n" +
                    "  `---`                `---'     `---'     `----'     `---`                 ");
            System.out.println("$$$XXxxxXX$$$$$$$X$$$$$$$$$$$$$$XXXXXX$&&&&&$&$$&&&&&$XXXXX$$&&&&\n" +
                    "&&$Xxx+xxXX$$$XXXX$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$&&&&&$$&&$$\n" +
                    "&$XXx+++xxX$$$XX$$$$$$$$$XXXXXX$$$XXXXXXXXXXXXX$$$$$$X$$$$$$$$&$$\n" +
                    "XXXx++++xxX$$XXX$$$XXxxxxxxx++x    :&++++++++++++++++xXX$$$$X$$$$\n" +
                    "+xXx++;+xxXXXXXXX$$Xxx+++++++$+x+xx+$&++++++++++++xxxxx+X$$$X$$$X\n" +
                    "xxxx+;;;;+xXXXXXX$Xxx++++++++$+:+; .x&++++++++++xx..+&$+X$$$X$$$X\n" +
                    "+Xx++;;.  :+XXXXXXXxxxx++++++x+;+x;;+$x+++++++++&x:;;$&xxX$$X$$XX\n" +
                    "+Xx++++xx+x++XXxXXXX+xxxxxxxxx$+X+:.;$$xxxxxxxxx&++:;X&$xX$$xX$XX\n" +
                    "XXx+;;+;;x;+;XXxXXXXxx+xxxxxxx:    x:;X$xxxxxxxX&&xxx$&&xX$$xX$XX\n" +
                    "XXx+;::;++x+:xXxXXXxxx;;++xxxX;   .+:     :+xxX$&X   x&&$XX$xXXXx\n" +
                    "xXx++;;... ;;xXxxXXx+X;;++;;:.  :       ...;XXX$&;  :+xxxxxx+xXXx\n" +
                    "xX;......  ;xxXxxXXx+$;;+;;::       ....:..:+Xxx  . .xx+;xx++xXXx\n" +
                    "X;...:.:;+..:xXxxXXX+X:;+;+;:      ..::;X::.;xXx:  .:x+;+XX+xxXXx\n" +
                    "x..::x:..x:  :xxxXXX+X;;;:++;:  .+;:::;+$x;.:;xx:+:.:xx+xX$X;+xXX\n" +
                    ":...+X+;;;+: ;xx+XXx+X:;::++;+;+: .::;;+X&x+::;X... :xxxxX$xX&&$X\n" +
                    "x;;+$X++:;;+;.xx+XXx;X::::xx;;;...:;:;;+X$Xx;:.;+:; .XxxxX&xxX&$X\n" +
                    "+;x$$Xx+;++:;$Xx+XXX;x;;;;X+x;+:;++::;;xX$X$X+.:;+x.:xxxxX$x$$XXX\n" +
                    ";+x$$Xx++xx::+Xx+XXX;x:.:+X++X;::.:+;;;+XXXXXx:.:;x;;xXxXX&$xxXXX\n" +
                    "+xxXXXx++X+;++Xx;XXX;;..+X;++x;::;;;;;;+X$&&&$X;..;;;XXXXX$$xxX$$\n" +
                    "xXXXxxXx+X+;xx$x;XXx...;X+;x$+;:;+x+;;;+x$&&&$$x;:.:;$$XXX$$Xx$$&\n" +
                    "X$Xxxxxxxx+;:x$x;XX:.:;xx++x$x+;:.:+::;+xx$&&&$$x+;:.+XXXX$$$x$$&\n" +
                    "$$$Xxxxx++;;;XX:;;xx;++:+;++++::...:;;;::;;+++;;:;+++;:+++++++xXX\n" +
                    "&&$$$$$$$XXXXXXXXxxxxxxXXXXXXxxxxxxxxxxxxxxxxxxXXXX$$$&$$$$$$$&&&");
            System.out.println("You go hunting and collect " + foodHunted + " liters of blood");
            int currentFood = person.food + foodHunted;
            //limits food
            if (currentFood > 600) {
                System.out.println("You can only fit 600 liters of blood in your wagon.");
                person.food = 600;
            } else {
                person.food += foodHunted;
            }
        } else {
            System.out.println("Ha! You didn't catch any prey!");
            System.out.println(" _____ ____  ______        ___    ____  ____  \n" +
                    "| ____/ ___|/ ___\\ \\      / / \\  |  _ \\|  _ \\ \n" +
                    "|  _|| |  _| |  _ \\ \\ /\\ / / _ \\ | |_) | | | |\n" +
                    "| |__| |_| | |_| | \\ V  V / ___ \\|  _ <| |_| |\n" +
                    "|_____\\____|\\____|  \\_/\\_/_/   \\_|_| \\_|____/ ");
            System.out.println("                                                                                        \n" +
                    " ************************************************************************************** \n" +
                    " ************************************************************************************** \n" +
                    " **********************************++=++++++++++++++++********************************* \n" +
                    " *******************************+++*@@@@@%%%%%####%%@%++******************************* \n" +
                    " *****************************++*@%=-:--=====+=++==--=@#++***************************** \n" +
                    " **************************+++@@=-=+#####*#****######===@@+++************************** \n" +
                    " ************************++*@+---##*##**************#%*---#@++************************* \n" +
                    " ***********************++@*--+@%##+##*********#*****##%@=--@++************************ \n" +
                    " **********************+*@--=@#******#******************#%*--@++*********************** \n" +
                    " *********************+%%-:%@#***************+**********##@*-+@+*********************** \n" +
                    " ********************++@=:@%#****#*******####*****#********@+-+%+********************** \n" +
                    " *******************++@=:*@**+*+-.   :--=+******+++--=-=+*##@-=@#+********************* \n" +
                    " *******************+%+-+@#**-     .      =+*+=.           =#%.-%%+******************** \n" +
                    " ******************+*@-:@#*+= @@@@@@@@@@@@.-++=*@@@@@@@@@@@ +%@:-##+******************* \n" +
                    " ******************+@--#@**+#@@@:       =+--=++@@.       :@@+*@@:-@*+****************** \n" +
                    " ******************+@-:@#***++ @@@=@@@%@@@@@#+=-+@@@@@@ @@ -+**@%:=@+****************** \n" +
                    " *****************++@-.@**+**#*%@@@%@%@@@@@+=+*+=@@@+@@@: -=***%@.-@+****************** \n" +
                    " *****************+%#.#@****#*=+++*++#-=+*+=+++++::--  .:=+****#@ -@+****************** \n" +
                    " *****************=@+.@%*#******+=====+++*#******+==***+*+*****#@+:@******************* \n" +
                    " *****************=@= @%*#****#*+*++++++%@%##+*****+****#*#*#***@%.##+***************** \n" +
                    " *****************=@= @#*#************##@.  -==.::*#***+*#*#####@@ ##+***************** \n" +
                    " *****************=@= @%####*#*********#@@@@==*@@%#*#**##*#*####@*.##+***************** \n" +
                    " *****************=@* @@*#****##**#***+-*@@@@@@:-=+****#**#*##*#@-:@******************* \n" +
                    " *****************+#%.+@###************+.    :. -*+*####*##*##*%@ -@+****************** \n" +
                    " ******************+@: @@##*##****#*#=   .%@@==    :+**#%##*##*@@ -@=****************** \n" +
                    " ******************+@=.#@######*******@@@@@@@@@@@@@%#*###*####%@::%#+****************** \n" +
                    " ******************+*@- @@###**####**==@@#*=#%%++*+:******#**#@% -@+******************* \n" +
                    " *******************+#*. @@#####*#**#*==--:.---:  -=+***####%@@ :@*+******************* \n" +
                    " ********************+@+. @@#*##*#**##*+=-==+===+***#####*#%@@ .##+******************** \n" +
                    " ********************++@+. @@@##*##*****+++++*+*+*#**#*###@@@ .%%+********************* \n" +
                    " *********************++@*. +@@%#########%##############@@@  :@#+********************** \n" +
                    " ***********************+*@:  @@@%**####*#############@@@-  =@++*********************** \n" +
                    " ************************+=@=.  +@@@****###********#@@@.  :@#=+************************ \n" +
                    " *************************+@-=@@#++@@@@%**++++*%@@@@#**%@@=-@+************************* \n" +
                    " *************************+@-:@@@@@@@@@@@@@@@@@@@@@@@@@@@@-=@=++*********************** \n" +
                    " ************************+=%+-..%@@@@@%@#%%####%#%@@@@@@.::.=*@************************ \n" +
                    " ***********************++@%=  :          .-=:=- :         @@ =%+********************** \n" +
                    " ***********************+@.  @@  #@:  +@. *    #  @@  #@ *@@@ =@+********************** \n" +
                    " ************************% *@ @@@@ @@@@ @@@@@@ @   @@-@ @@ @@ =@+********************** \n" +
                    " ***********************+@  @ :+-@ @-:@ @@ @@ @@ @ @@*@ @@ @@ =@+********************** \n" +
                    " ***********************+@+      @ @* @ @#                    *%+********************** \n" +
                    " ***********************++*@@@@=          :@@@@@@@@@@@@@@@@@@@#************************ \n" +
                    " *************************++=++#@@@@@@@@@@*+=================++************************ \n" +
                    " ******************************++========++******************************************** \n" +
                    " ************************************************************************************** \n" +
                    " ************************************************************************************** \n" +
                    "                                                                                        ");
        }

        System.out.println("You now have " + person.food + " liters of blood in the wagon.");

    }
    /**
     * Generates random number using the Random class
     * @param min - lowest value
     * @param max - hightest value
     * @return random int betweeen those values inclusive both sides
     */


    public int GenerateRandomNumber(int min, int max) {
        Random random = new Random();
        int randomNumber_int = random.nextInt(max - min +1) + min;

        return randomNumber_int;
    }

    /**
     * Used for gameplay which is just displaying the choices
     * @param person
     * @return recursive - ensures proper input
     */
    public void DisplayChoices(Person person) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose from the following options: ");
        System.out.println("1.   Continue to next stop. ");
        System.out.println("2.   Hunt for food.");
        System.out.println("3.   Take your chances");

        System.out.println("");

        int choice = scanner.nextInt();

        if (choice == 1) {
            NextStop(person);
        } else if (choice == 2) {
            Hunt(person);
        } else if (choice == 3) {
            TakeAChance(person);
        } else {
            System.out.println("Incorrect choice. Please try again.");
            DisplayChoices(person);
        }
    }
}
