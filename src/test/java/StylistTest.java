import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class CategoryTest {
  private Stylist mStylist;

  @Before
  public void instantiate() {
    mStylist = new Stylist("Qwemba");
  }

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

      @Test
      public void stylist_instantiatesCorrectly_true() {
        assertEquals(true, mStylist instanceof Stylist);
      }

      @Test
  public void getName_stylistInstantiatesWithName_String() {
    assertEquals("Qwemba", mStylist.getName());
  }

}
