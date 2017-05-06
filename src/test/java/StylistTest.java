import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class CategoryTest {

  @Before
      public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "postgres", "postgres");
      }

      @After
      public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
          String deleteClientsQuery = "DELETE FROM clients *;";
          String deleteStylistsQuery = "DELETE FROM stylists *;";
          con.createQuery(deleteClientsQuery).executeUpdate();
          con.createQuery(deleteStlistsQuery).executeUpdate();
        }
      }
      // @Test
      // public void category_instantiatesCorrectly_true() {
      //   Category testCategory = new Category("Home");
      //   assertEquals(true, testCategory instanceof Category);
      // }

}
