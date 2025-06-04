
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


public class Main
{

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args)
    {
        System.out.println("Welcome to Twilight Trail! This game starts after the end of the Twilight series. The Cullens have graduated High School and now must move out of Forks, Washington once again. However, as they prepare to move they are transported into this strange universe by the vengeful Volturi.  Bella quickly realizes that they are in some strange version of Oregon Trail as she spent most of her loner days playing this game. As the Cullen family runs around in mayhem, text starts to appear in the sky:\n" +
                " \" Dear Cullens, \n" +
                " You guys have proved your undying love for each other, so we thought to give you this little survival game to finally test whether you are ready to go out into the real world. This final test will determine if you guys can live freely or if you must be immediately euthanized by having you travel across the world. That is right, if you fail this game we will kill, and this time for real, not just in Alice's visions. Oh and if you haven't noticed yet, Renesmee is with us right now, so tell Edward and Bella to lock in! \n" +
                " Peace Out, \n" +
                " The Volturi\" ");

        Scanner in = new Scanner(System.in);
        Person leader = choosePerson();
        System.out.println("Edward, Bella, Carlisle, Esme, Alice, Jasper, Rosalie, Emmett all get ready as new text appears on the screen.");
        leader.createParty(8);


        Trail trail = new Trail();

        boolean inPlay = true;
        while (inPlay)
        {
            trail.DisplayChoices(leader);

            if(leader.GetActiveCompanions()==null){
                System.out.println("You lost all companions and fails this game!");


            }

            if(trail.Locations[trail.CurrentStop] == "Volterra, Italy")
            {
                System.out.println("Congratulations! You guys won under "+leader.name+"'s lead!");

                System.out.println(leader.name + " "+leader.GetActiveCompanions());
                System.out.println("You've completed the Twilight Trail and rescued Renesmee.");
                System.out.println("Total number of Days on the Trail: " + trail.TotalDays);
                System.out.println("Amount of Food in the Wagon: " + leader.food);
                System.out.println("Number of Oxen: " + leader.oxen);




                String filePath = "score.txt";

                try (FileWriter fw = new FileWriter(filePath, true); // true for append mode
                     PrintWriter pw = new PrintWriter(fw)) {

                    pw.println("Total number of Days on the Trail: " + trail.TotalDays);
                    pw.println("Vampires alive: "+leader.GetActiveCompanions());
                    pw.println("Amount of Food in the Wagon: " + leader.food);
                    pw.println("Number of Oxen: " + leader.oxen);
                    pw.println();

                    pw.close();
                    System.out.println("Data recorded.");



                } catch (IOException e) {
                    System.err.println("Error appending data to file: " + e.getMessage());
                }





                inPlay = false;

            }
        }


    }
/**
 * inheritance - returns Person subclasses
 */
    public static Person choosePerson()
    {
        int choice;
        //System.out.println("Many Kinds of People Made the Trip to Oregon");
        System.out.println("Who will you travel as?");
        System.out.println("1.)Bankers from Boston");
        System.out.println("2.)Carpenters from Ohio");
        System.out.println("3.)Farmers from Illinois");
        System.out.println("Please enter your choice as a number:");
        choice = scanner.nextInt();


        if (choice == 1)
        {
            return new Banker();
        }
        else if (choice == 2)
        {
            return new Carpenter();
        }
        else if (choice == 3)
        {
            return new Farmer();
        }
        else
        {
            System.out.println("Incorrect choice. Please try again.");
            return choosePerson();
        }
    }
}

