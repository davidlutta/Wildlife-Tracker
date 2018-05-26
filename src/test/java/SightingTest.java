import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    //Test to check if sighting instantiates correctly
    @Test
    public void Sighting_InstantiatesCorrectly_True(){
        Sighting newsighting = new Sighting("Roger","Zone 1",1,"endangered");
        assertTrue( newsighting instanceof Sighting);
    }

    //Test to check if sighting instantiates With Name
    @Test
    public void Sighting_IsSavedOnDataBase_True(){
        Sighting newSighting = new Sighting("Roger","Zone 2",1,"endangered");
        newSighting.save();
        assertTrue(Sighting.all().get(0).equals(newSighting));
    }
}
