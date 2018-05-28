import org.sql2o.Connection;
import java.sql.Timestamp;
import java.util.List;

public class Sighting implements DataBaseManagement{
    private String name;
    private String location;
    private int animalId;
    private Timestamp timestamp;
    private int id;

    public Sighting(String name, String location, int animalId) {
        //Exception for name
        if (name.equals("")) {
            throw new IllegalArgumentException("Please enter a name mate");
        }
        //Exception for location
        if (location.equals("")) {
            throw new IllegalArgumentException("Please enter a location mate");
        }
        this.name = name;
        this.location = location;
        this.animalId = animalId;
    }

    //Get Method for Ranger's Name
    public String getName() {
        return name;
    }
    //Get Method for Location of the Sighted Animal
    public String getLocation() {
        return location;
    }
    //Get Method for the Sighting Id
    public int getId() {
        return id;
    }
    //Get Method for TimeStamp
    public Timestamp getTimestamp() {
        return timestamp;
    }
    //Get Method to get Animal object
    public Animal getAnimal() {
        String sql = "SELECT * FROM animal WHERE id = :id";
        try(Connection con = DB.sql2o.open()){
            Animal myAnimal = con.createQuery(sql)
                    .addParameter("id",this.animalId)
                    .executeAndFetchFirst(Animal.class);
            return myAnimal;
        }
    }
    //Method to save Sightings
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sighting (name, location, animalId, timestamp) VALUES (:name, :location, :animalId, now());";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("location", this.location)
                    .addParameter("animalId", this.animalId)
                    .executeUpdate()
                    .getKey();
        }
    }
    //Method to Get All Sightings
    public static List<Sighting> all(){
        String sql = "SELECT * FROM sighting;";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }
    //Method for finding
    public static Sighting find(int id){
        String sql = "SELECT * FROM sighting WHERE id = :id";
        try(Connection con = DB.sql2o.open()) {
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }
    }
    //Overriding Equals method and satisfying Interface
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

    //Method for deleting sightings and also satisfying Interface
    @Override
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM sighting WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}

