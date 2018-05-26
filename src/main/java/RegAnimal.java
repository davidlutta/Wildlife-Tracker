import org.sql2o.*;

import java.util.List;

public class RegAnimal extends Animal{

    //Setting a constant for the safe animals
    private static final String ANIMAL_SPECIES = "safe";

    //Constructor for Regular Animals
    public RegAnimal(String name, String age, String health, String species){
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
        this.species = ANIMAL_SPECIES;
    }

    //Method to save all the animals
    @Override
    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animal (name, age, health, species) VALUES (:name, :age, :health, :species);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("health", this.health)
                    .addParameter("species", this.species)
                    .executeUpdate()
                    .getKey();
        }
    }

    //Method to override Equals
    @Override
    public boolean equals(Object otherAnimal){
        if(!(otherAnimal instanceof Object)){
            return false;
        }
        Animal myAnimal = (Animal) otherAnimal;
        return this.getName().equals(myAnimal.getName())&&
                this.getSpecies().equals(myAnimal.getSpecies())&&
                this.getId()==myAnimal.getId() ;

    }


    //Method to get all species of animals
    public static List<RegAnimal> all(){
        String sql = "SELECT * FROM animal WHERE species='safe'";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(RegAnimal.class);
        }
    }



}