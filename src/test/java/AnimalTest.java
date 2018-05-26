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
        RegAnimal myAnimal = new RegAnimal("panther","young","weak","");
        assertEquals(true, myAnimal instanceof RegAnimal);
    }

    //test to see if animal instantiates with a name
    @Test(expected = IllegalArgumentException.class)
    public void animal_InstantiatesWithName_panther(){
        RegAnimal myAnimal = new RegAnimal("","","","");
        assertEquals("panther", myAnimal.getName());
    }

    // test to see if animal instantiates with an age and if an error is thrown
    @Test (expected = IllegalArgumentException.class)
    public void animal_InstantiatesWithAge_mature(){
        RegAnimal myAnimal = new RegAnimal("","","","");
    }

    // test to see if animal instantiates with the health status and if an error is thrown
    @Test (expected = IllegalArgumentException.class)
    public void animal_InstantiatesWithHealth_Weak(){
        RegAnimal myAnimal = new RegAnimal("","","","");
    }

    //test to check if info is saved into database
    @Test
    public void animal_IsSavedInDatabase(){
        RegAnimal myAnimal = new RegAnimal("panther", "adult", "healthy","safe");
        myAnimal.save();
        assertTrue(RegAnimal.all().get(0).equals(myAnimal));
    }

    //Test to check if all instances of regular animals are made
    @Test
    public void animal_AllInstancesOfAnimalAreReturned_True(){
        RegAnimal myAnimal1 = new RegAnimal("panther", "young","healthy","safe");
        myAnimal1.save();
        RegAnimal myAnimal2 = new RegAnimal("puma","mature","healthy","safe");
        myAnimal2.save();
        assertTrue(RegAnimal.all().get(0).equals(myAnimal1));
        assertTrue(RegAnimal.all().get(1).equals(myAnimal2));
    }
}