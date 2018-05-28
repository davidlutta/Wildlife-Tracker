import org.sql2o.*;


public class Animal {
    protected String name;
    protected String age;
    protected String health;
    protected String species;
    protected int id;

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


    @Override
    public boolean equals(Object otherAnimal){
        if(!(otherAnimal instanceof Object)){
            return false;
        }
        Animal myAnimal = (Animal) otherAnimal;
        return this.getName().equals(myAnimal.getName())&&
                this.getSpecies().equals(myAnimal.getSpecies())&&
                this.getId()==myAnimal.getId()&&
                this.getAge()==myAnimal.getAge()&&
                this.getHealth().equals(myAnimal.getHealth());
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
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }

    //Method to get Id
    public int getId() {
        return id;
    }


    //Method to Find Id
    public static Animal find(int id){
        String sql = "SELECT * FROM animal WHERE id = :id;";
        try(Connection con = DB.sql2o.open()){
            Animal myAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
            return myAnimal;
        }
    }

    }



