import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;

public class Sighting {
    private String name;
    private String location;
    private Timestamp timestamp;
    private int animalId;
    private String species;
    private int id;

    public Sighting(String name, String location, int animalId, String species) {
        //Exception for name
        if (name.equals("")) {
            throw new IllegalArgumentException("Please enter a name mate");
        }
        //Exception for location
        if (location.equals("")) {
            throw new IllegalArgumentException("Please enter a location mate");
        }
        //Exception for species
        if (species.equals("")) {
            throw new IllegalArgumentException("Please enter the species of the animal");
        }
        this.name = name;
        this.location = location;
        this.animalId = animalId;
        this.species = species;
    }

    //Get Method for Ranger's Name
    public String getName() {
        return name;
    }
    //Get Method for TimeStamp of Sighting
    public String getTime() {
        return String.format("%1$TD %1$TR", timestamp);
    }
    //Get Method for Location of the Sighted Animal
    public String getLocation() {
        return location;
    }
    //Get Method for species of Animal Sighted
    public String getSpecies() {
        return species;
    }
    //Get Method for the Sighting Id
    public int getId() {
        return id;
    }
    //Method to save Sightings
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sighting (name, location, animalId, species) VALUES (:name, :location, :animalId, :species);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("location", this.location)
                    .addParameter("animalId", this.animalId)
                    .addParameter("species", this.species)
                    .executeUpdate()
                    .getKey();
        }
    }
    //Method to Get All Sightings in Order of timestamp
    public static List<Sighting> all(){
        String sql = "SELECT * FROM sighting;";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }
    //Overriding method
    @Override
    public boolean equals(Object otherSighting){
        if(!(otherSighting instanceof Object)){
            return false;
        }
        Sighting myAnimal = (Sighting) otherSighting;
        return this.getName().equals(myAnimal.getName())&&
                this.getLocation().equals(myAnimal.getLocation())&&
                this.getId()==myAnimal.getId() ;
    }
}

