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
}