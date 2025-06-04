/**
 * Farmer Class - Subclass of Person class
 */

class Farmer extends Person {
    /**
     * Farmer Constructor - accesses PrintDescription method
     */
    public Farmer() {
        profession = "Farmer";
        money = 40;
        oxen = 2;
        food = 20;

        PrintDescription();
    }
}
