import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

public class AnimalTest{

    //Database rule
    @Rule
    public DatabaseRule database = new DatabaseRule();

    //test to see if animal instantiates
    @Test
    public void animal_InstantiatesCorrectly_true(){
        RegAnimal myAnimal = new RegAnimal("panther","matrue");
        assertEquals(true, myAnimal instanceof RegAnimal);
    }

    //test to see if animal instantiates with a name
    @Test
    public void animal_InstantiatesWithName_panther(){
        RegAnimal myAnimal = new RegAnimal("panther","mature");
        assertEquals("panther", myAnimal.getName());
    }

    // test to see if animal instantiates with an age
    @Test
    public void animal_InstantiatesWithAge_mature(){
        RegAnimal myAnimal = new RegAnimal("panther","mature");
        assertEquals("mature", myAnimal.getAge());
    }
}