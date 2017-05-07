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
          con.createQuery(deleteStylistsQuery).executeUpdate();
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

  @Test
 public void find_returnsNullWhenNoClientFound_null() {
   assertTrue(Stylist.find(999) == null);
 }

 @Test
     public void equals_returnsTrueIfNamesAretheSame() {
       Stylist firstStylist = new Stylist("Qwemba");
       Stylist secondStylist = new Stylist("Qwemba");
       assertTrue(firstStylist.equals(secondStylist));
     }

     @Test
            public void save_savesIntoDatabase_true() {
              mStylist.save();
              assertTrue(Stylist.all().get(0).equals(mStylist));
            }

            @Test
              public void save_assignsIdToObject() {
                mStylist.save();
                Stylist savedStylist = Stylist.all().get(0);
                assertEquals(mStylist.getId(), savedStylist.getId());
              }

              @Test
      public void getClients_retrievesALlClientsFromDatabase_clientsList() {
        mStylist.save();
        Client firstClient = new Client("Qwemba", mStylist.getId());
        firstClient.save();
        Client secondClient = new Client("Awino", mStylist.getId());
        secondClient.save();
        Client[] clients = new Client[] { firstClient, secondClient };
        assertTrue(mStylist.getClients().containsAll(Arrays.asList(clients)));
      }
}
