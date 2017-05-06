import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {
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

  @Test
    public void all_returnsAllInstancesOfStylist_true() {
    Stylist firstStylist = new Stylist("Qwemba");
            firstStylist.save();
            Stylist secondStylist = new Stylist("Awino");
            secondStylist.save();
            assertEquals(true, Stylist.all().get(0).equals(firstStylist));
            assertEquals(true, Stylist.all().get(1).equals(secondStylist));
    }

    @Test
  public void clear_emptiesAllStylistsFromList_0() {
    assertEquals(Stylist.all().size(), 0);
}

@Test
  public void getId_stylistsInstantiateWithAnId_1() {
       mStylist.save();
       assertTrue(mStylist.getId() > 0);
  }

  @Test
public void getClients_initiallyReturnsEmptyList_ArrayList() {
  assertEquals(0, mStylist.getClients().size());
}

  @Test
  public void find_returnsStylistWithSameId_secondStylist() {
    // Category.clear();
    Stylist firstStylist = new Stylist("Qwemba");
       firstStylist.save();
       Stylist secondStylist = new Stylist("Awino");
       secondStylist.save();
       assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
  }
}
