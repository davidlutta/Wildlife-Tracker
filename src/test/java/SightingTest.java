import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    //Test to check if sighting instantiates correctly
    @Test
    public void Sighting_InstantiatesCorrectly_True(){
        Sighting newsighting = new Sighting("Roger","Zone 1","endangered",1);
        assertTrue(true, newsighting instanceof Sighting);
    }
}
