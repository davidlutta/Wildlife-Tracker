import org.sql2o.*;

import java.util.List;

public abstract class Animal {
    public String name;
    public String age;
    public String species;
    public String health;
    public int id;

    //Method to get Name of Animal
    public String getName() {
        return name;
    }

    //Method to get Age of Animal
    public String getAge() {
        return age;
    }

    //Method to get health of animal
    public String getHealth() {
        return health;
    }

    //Method to get Species
    public String getSpecies() {
        return species;
    }

    //Method for saving
    public void save(){
        try(Connection con = DB.sql2o.open()) {
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

    //Method to get Id
    public int getId() {
        return id;
    }


    //Method to get all species of animals
    public List<Animal> all(String species){
        String sql = "SELECT * FROM animal WHERE species = '' ;";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
        }
    }

