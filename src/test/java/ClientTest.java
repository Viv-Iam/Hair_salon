import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class ClientTest {
  private Client mClient;
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

        @Before
        public void instantiate() {
          mClient = new Client("Vivian", 1);
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
  assertTrue(myClient.getId() > 0);
}

@Test
  public void find_returnsClientWithSameId_secondClient() {
    Task firstClient = new Client("Vivian", 1);
    firstClient.savClient
    Task secondClient = new Client("Opondoh", 2);
    secondClient.save();
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }
}
