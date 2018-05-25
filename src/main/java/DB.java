import org.sql2o.*;

//Making a connection to our main database
public class DB{
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", null, null);
}