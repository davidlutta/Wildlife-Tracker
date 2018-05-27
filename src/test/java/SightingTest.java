import org.junit.Ignore;
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
    //Sighting is assigined an Id 
    @Test
    public void sighting_EachSigthingIsAssignedAnId_getid(){
        Sighting newSighting = new Sighting("Roger","Zone 2",1,"endangered");
        newSighting.save();
        Sighting testSighting = Sighting.all().get(0);
        assertEquals(newSighting.getId(), testSighting.getId());
    }
    //Testing the find Id method
    @Test
    public void find_WillReturnSightingWithTheSameID_SecnondSighting(){
        Sighting firstSighting = new Sighting("Roger","Zone 2",1,"endangered");
        firstSighting.save();
        Sighting SecondSighting = new Sighting("Chris","Zone 5",3,"safe");
        SecondSighting.save();
        assertEquals(SecondSighting, Sighting.find(SecondSighting.getId()));
    }
    //Test to check if sightings can be deleted from database
    @Test
    public void Sightings_CanBeDeletedFromDataBase_true() {
        Sighting newSighting = new Sighting("Brenden","Zone 5",1,"safe");
        newSighting.save();
        newSighting.delete();
        assertEquals(0, Sighting.all().size());
    }
}
