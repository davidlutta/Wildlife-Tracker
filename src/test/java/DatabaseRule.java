import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {

    //Making a connection to our test database before running our tests
    @Override
    public void before(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", null, null);
    }

    //Clearing our database after running all our tests
    @Override
    public void after(){
        try(Connection con = DB.sql2o.open()) {
            String deleteAnimalQuery = "DELETE FROM animal *;";
            String deleteSightingQuery = "DELETE FROM sighting *;";
            con.createQuery(deleteAnimalQuery).executeUpdate();
            con.createQuery(deleteSightingQuery).executeUpdate();
        }
    }

}
