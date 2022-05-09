import java.io.Serializable;

public class Armour implements Serializable {

    /* Instance Variables */
    private static final long serialVersionUID = 1L;
    private int durability;
    private int defenseRating;
    private final String name;

    /* Constructors */
    public Armour(String name, int durability, int defenseRating) {
        this.name = name;
        this.durability = durability;
        this.defenseRating = defenseRating;
    }

    /* Instance Methods */
    public void reduceDurability(int reduction, Player player) {
        durability -= reduction;
        if (durability <= 0) {
            System.out.println("Your " + name + " has been rendered useless.");
            defenseRating = 0;
        }
    }

    /* Getters & Setters */
    public int getDefenseRating() {
        return this.defenseRating;
    }

    @Override
    public String toString() {
        return name + ", Defense Rating: " + defenseRating + "\n";
    }
}
