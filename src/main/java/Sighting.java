public class Sighting {
    private String name;
    private String location;
    private int animalId;
    private String species;

    public Sighting(String name, String location, int animalId, String species){
        //Exception for name
        if (name.equals("")){
            throw new IllegalArgumentException("Please enter a name mate");
        }
        //Exception for location
        if (location.equals("")){
            throw new IllegalArgumentException("Please enter a location mate");
        }
        //Exception for species
        if (species.equals("")){
            throw new IllegalArgumentException("Please enter the species of the animal");
        }
        this.name = name;
        this.location = location;
        this.animalId = animalId;
        this.species = species;
    }
}
