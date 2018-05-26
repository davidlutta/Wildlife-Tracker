import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredAnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    //test to see if animal instantiates
    @Test
    public void animal_InstantiatesCorrectly_true(){
        EndangeredAnimal myEndangeredAnimal = new EndangeredAnimal("panther","young","weak","");
        assertEquals(true, myEndangeredAnimal instanceof EndangeredAnimal);
    }

    //test to see if animal instantiates with a name
    @Test(expected = IllegalArgumentException.class)
    public void animal_InstantiatesWithName_panther(){
        EndangeredAnimal myEndangeredAnimal = new EndangeredAnimal("","","","");
        assertEquals("panther", myEndangeredAnimal.getName());
    }

    // test to see if animal instantiates with an age and if an error is thrown
    @Test (expected = IllegalArgumentException.class)
    public void Endangeredesanimal_InstantiatesWithAge_mature(){
        EndangeredAnimal myEndangeredAnimal = new EndangeredAnimal("","","","");
    }

    // test to see if animal instantiates with the health status and if an error is thrown
    @Test (expected = IllegalArgumentException.class)
    public void EndangeredAnimal_InstantiatesWithHealth_Weak(){
        EndangeredAnimal myEndangeredAnimal = new EndangeredAnimal("","","","");
    }

    //test to check if info is saved into database
    @Test
    public void EndangeredAnimal_IsSavedInDatabase(){
        EndangeredAnimal myEndangeredAnimal = new EndangeredAnimal("panther", "adult", "healthy","endangered");
        myEndangeredAnimal.save();
        assertTrue(myEndangeredAnimal.all().get(0).equals(myEndangeredAnimal));
    }

    //Test to check if all instances of Endangered animals are made
    @Test
    public void EndangeredAnimal_AllInstancesOfAnimalAreReturned_True(){
        EndangeredAnimal myEndangeredAnimal1 = new EndangeredAnimal("panther", "adult", "healthy","endangered");
        myEndangeredAnimal1.save();
        EndangeredAnimal myEndangeredAnimal2 = new EndangeredAnimal("Black Rhino", "adult", "healthy","endangered");
        myEndangeredAnimal2.save();
        assertTrue(EndangeredAnimal.all().get(0).equals(myEndangeredAnimal1));
        assertTrue(EndangeredAnimal.all().get(1).equals(myEndangeredAnimal2));
    }

    //Test to find Animal with the Same Id
    public void find_WillReturnEndangeredAnimalWithTheSame_SecondAnimal(){
        EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("panther", "adult", "healthy","endangered");
        firstEndangeredAnimal.save();
        EndangeredAnimal SecondEndangeredAnimal = new EndangeredAnimal("panther", "adult", "healthy","endangered");
        SecondEndangeredAnimal.save();
        assertEquals(EndangeredAnimal.find(SecondEndangeredAnimal.getId()), SecondEndangeredAnimal);
    }
    //Animal is assigined an Id
    @Test
    public void EndangeredAnimal_AnimalIsAssingnedAnID_getid(){
        EndangeredAnimal myEndangeredAnimal = new EndangeredAnimal("panther", "adult", "healthy","endangered");
        myEndangeredAnimal.save();
        EndangeredAnimal testEndangeredAnimalia = EndangeredAnimal.all().get(0);
        assertEquals(myEndangeredAnimal.getId(), testEndangeredAnimalia.getId());
    }

}
