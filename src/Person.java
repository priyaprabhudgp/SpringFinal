import java.util.ArrayList;
import java.util.Scanner;

/**
 * Character class - for wagon members and leader
 */
class Person {
    public String profession;//to blend in with humans
    public String name;
    public double money;//for trail spending
    public int oxen;//for travel--cannot use super speed since humans all around
    public int food;//blood
    public Boolean alive = true;
    //Wagon members
    public Person[] companions;

    /**
     * No argument empty constructor bc Person has to one of three options according to game rules
     */
    public Person() {

    }
    /**
     * Automatically instantiates wagon member objects based on twilight characters
     * @param partySize - # of objects and travelers
     */
    public void createParty(int partySize) {
        Scanner scanner = new Scanner(System.in);

        companions = new Person[partySize];

        System.out.println("Please enter which of you will be the leader.");
        //Data Structure 1
        ArrayList<String> vamps = new ArrayList<String>();
        vamps.add("Edward");
        vamps.add("Bella");
        vamps.add("Carlisle");
        vamps.add("Esme");
        vamps.add("Alice");
        vamps.add("Jasper");
        vamps.add("Rosalie");
        vamps.add("Emmett");
        companions[0] = this;
        companions[0].name = scanner.nextLine();
        vamps.remove(companions[0].name);

        for (int i = 1; i < companions.length; i++) {
            System.out.println("Wagon member #" + (i + 1));
            companions[i] = new Person();
            companions[i].name = vamps.get(i-1);
            System.out.println(companions[i].name);
        }

        System.out.println("\nYour wagon leader's name is " + companions[0].name);
    }

    /**
     * Method to let user know their starting stats
     */
    public void PrintDescription() {
        System.out.println("You are a " + profession + "." + " You have $" + money + " to spend at the general store.");
        System.out.println("You enter the general store and purchase supplies: ");
        System.out.println(oxen + " oxen");
        System.out.println(food + " liters of blood");

    }

    /**
     * Prints living companions
     * @return string of wagon members
     */
    public String GetActiveCompanions() {
        String members = "";
        for (int i = 1; i < companions.length; i++) {
            if (companions[i].alive) {
                members += " " + companions[i].name;
            }
        }
        return members;
    }//finalize

}
