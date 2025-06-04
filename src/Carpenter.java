
/**
 * Carpenter Class - Subclass of Person class
 */
class Carpenter extends Person {
    /**
     * Carpenter Constructor - accesses PrintDescription method
     */
    public Carpenter() {
        profession = "Carpenter";
        money = 80;
        oxen = 4;
        food = 40;

        PrintDescription();
    }


}
