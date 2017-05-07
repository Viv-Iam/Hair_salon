import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class ClientTest {
  private Client mClient;
  @Before
  public void instantiate() {
    mClient = new Client("Vivian", 1);
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
            // con.createQuery(deleteStylistsQuery).executeUpdate();
          }
        }



        @Test
        public void Client_instantiatesCorrectly_true() {
          assertEquals(true, mClient instanceof Client);
        }

        @Test
        public void lient_instantiatesWithName_String() {
          assertEquals("Vivian", mClient.getName());
}

@Test
 public void all_returnsAllInstancesOfClient_true() {
   Client firstClient = new Client("Vivian", 1);
   firstClient.save();
   Client secondClient = new Client("Opondoh", 1);
   secondClient.save();
   assertEquals(true, Client.all().get(0).equals(firstClient));
   assertEquals(true, Client.all().get(1).equals(secondClient));
 }

 @Test
public void clear_emptiesAllClientsFromArrayList_0() {
  assertEquals(Client.all().size(), 0);
}

@Test
public void getId_clientsInstantiateWithAnID() {
  mClient.save();
  assertTrue(mClient.getId() > 0);
}

@Test
  public void find_returnsClientWithSameId_secondClient() {
    Client firstClient = new Client("Vivian", 1);
    firstClient.save();
    Client secondClient = new Client("Opondoh", 2);
    secondClient.save();
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }

  @Test
public void equals_returnsTrueIfNamesAretheSame() {
  Client firstClient = new Client("Vivian", 1);
  Client secondClient = new Client("Vivian", 1);
  assertTrue(firstClient.equals(secondClient));
}

@Test
public void save_returnsTrueIfNamesAretheSame() {
  mClient.save();
  assertTrue(Client.all().get(0).equals(mClient));
}

@Test
public void save_assignsIdToObject() {
  mClient.save();
  Client savedClient = Client.all().get(0);
  assertEquals(mClient.getId(), savedClient.getId());
}

@Test
     public void save_savesStylistIdIntoDB_true() {
      Stylist  myStylist = new Stylist("Qwemba");
       myStylist.save();
       Client myClient = new Client("Vivian", myStylist.getId());
       myClient.save();
       Client savedClient = Client.find(myClient.getId());
       assertEquals(savedClient.getStylistId(), myStylist.getId());
     }

@Test
public void update_updatesClientsName_true() {
 mClient.save();
 mClient.update("Queen");
 assertEquals("Queen", Client.find(mClient.getId()).getName());
}

@Test
public void delete_deletesClient_true() {
  mClient.save();
  int mClientId = mClient.getId();
  mClient.delete();
  assertEquals(null, Client.find(mClientId));
}
}
