/**
 * Banker Class - Subclass of Person class
 */
class Banker extends Person {
    /**
     * Banker Constructor - accesses PrintDescription method
     */
    public Banker() {
        profession = "Banker";
        money = 160;
        oxen = 10;
        food = 60;

        PrintDescription();
    }
}
