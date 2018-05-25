public class RegAnimal extends Animal{

    //Setting a constant for the safe animals
    private static final String ANIMAL_SPECIES = "safe";

    //Constructor for Regular Animals
    public RegAnimal(String name, String age){
        if (name.equals("")) {
            throw new IllegalArgumentException("Please enter a name");
        }
        this.name = name;
        species = ANIMAL_SPECIES;
    }



}