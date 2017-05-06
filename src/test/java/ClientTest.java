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
          mClient = new Client("Vivian");
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
   Client firstClient = new Client("Vivian");
   firstClient.save();
   Client secondClient = new Client("Opondoh");
   secondClient.save();
   assertEquals(true, Client.all().get(0).equals(firstClient));
   assertEquals(true, Client.all().get(1).equals(secondClient));
 }

 public static List<Client> all() {
        String sql = "SELECT id, name, stylistId FROM clients";
        try(Connection con = DB.sql2o.open()) {
         return con.createQuery(sql).executeAndFetch(Client.class);
        }
      }
}
