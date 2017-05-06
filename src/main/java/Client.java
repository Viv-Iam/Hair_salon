import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
  private String name;
  private int id;
  private int stylistId;

//constructor
  public Client(String name) {
    this.name = name;
  }

  //implements getName()
  public String getName() {
    return name;
  }
  public static List<Client> all() {
         String sql = "SELECT id, name, stylistId FROM clients";
         try(Connection con = DB.sql2o.open()) {
          return con.createQuery(sql).executeAndFetch(Client.class);
         }
       }

}
