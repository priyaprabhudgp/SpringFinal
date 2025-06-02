import java.util.Random;
import java.util.Scanner;

class Trail {


    public String[] Locations;
    public int CurrentStop = 0;
    public int TotalDays = 0;

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

                    person.food = 0;
                }
            } else {
                int newAmmuntofFood = person.food + foundFood;

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
                System.out.println("You find " + foundOxen + " friendly vampire clans. They give you some live oxen for emergency situations. How lucky...");
                person.oxen += foundOxen;
            } else {
                if (person.oxen > 0) {
                    person.oxen -= GenerateRandomNumber(1, 3);
                    System.out.println("You stepped on another vampire clan's hunting ground! Oh my you have to give up some oxen.");
                } else {
                    System.out.println("You think about your emergency supply oxen.");
                }

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
                    System.out.println("""
                            You've gone bankrupt! Not just in this game, but in real life dummies. The Volturi have seized all your assets, so when you leave this game you will be completely broke!""");
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
        System.out.println("Your party consumes " + foodConsumed + "lbs of food along the way. You have " + person.food + "lbs. of food left.");

        System.out.println("\n");
    }

    public void PrintStats(Person person) {
        System.out.println("Round Stats:");
        System.out.println("Number of days on the Trail: " + TotalDays);
        System.out.println("Amount of Food in the Wagon " + person.food);
        System.out.println("Number of Oxen: " + person.oxen);
        System.out.println("Amount of Money: " + person.money);
        System.out.println("Last stop visited: " + Locations[CurrentStop]);

        System.out.println("Active Companions: " + person.GetActiveCompanions());

    }

    public void Hunt(Person person) {
        int foodHunted = GenerateRandomNumber(1, 200);

        System.out.println("You go hunting and collect " + foodHunted + " liters of blood");
        int currentFood = person.food + foodHunted;

        if (currentFood > 600) {
            System.out.println("You can only fit 600 liters of blood in your wagon.");
            person.food = 600;
        } else {
            person.food += foodHunted;
        }

        System.out.println("You now have " + person.food + " liters of blood in the wagon.");

    }

    public int GenerateRandomNumber(int min, int max) {
        Random random = new Random();
        int randomNumber_int = random.nextInt(max - 1) + min;

        return randomNumber_int;
    }

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
