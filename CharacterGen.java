/* This file will handle anything NPC/Character Generation related. 
It will be used to generate random NPCs for the game. */
import java.util.Random;

public class CharacterGen {
    static Random rand = new Random();

    // Predefined data
    static String[] names = {"John", "Jane", "Alex", "Emily", "Chris"};
    static String[] traits = {"Brave", "Cunning", "Wise", "Strong", "Agile"};
    static String[] ages = {"Young", "Adult", "Elderly"};

    // Generate a random character
    public static NPC generateCharacter() {
        String name = names[rand.nextInt(names.length)];
        String trait = traits[rand.nextInt(traits.length)];
        String age = ages[rand.nextInt(ages.length)];

        return new NPC(name, trait, age);
    }

    // NPC class
    public static class NPC {
        public String name;
        public String traits;
        public String age;

        public NPC(String name, String traits, String age) {
            this.name = name;
            this.traits = traits;
            this.age = age;
        }
    }
}