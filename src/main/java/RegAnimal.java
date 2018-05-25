public class RegAnimal extends Animal{

    //Setting a constant for the safe animals
    private static final String ANIMAL_SPECIES = "safe";

    //Constructor for Regular Animals
    public RegAnimal(String name, String age, String health){
        if (name.equals("")) {
            throw new IllegalArgumentException("Please enter a name");
        }
        if (age.equals("")) {
            throw new IllegalArgumentException("Please enter the age of the animal");
        }
        if (health.equals("")) {
            throw new IllegalArgumentException("Please enter the health status of the animal");
        }

        this.name = name;
        this.age = age;
        this.health = health;
        species = ANIMAL_SPECIES;
    }



}