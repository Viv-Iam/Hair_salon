import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
  private String name;
  private int id;
  private int stylistId;

//constructor
  public Client(String name, int stylistId) {
    this.name = name;
    this.stylistId = stylistId;
  }

  //implements getName()
  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public int getStylistId() {
     return stylistId;
   }

   public static Client find(int id) {
try(Connection con = DB.sql2o.open()) {
  String sql = "SELECT * FROM clients where id=:id";
  Client client = con.createQuery(sql)
    .addParameter("id", id)
    .executeAndFetchFirst(Client.class);
  return client;
}


  public static List<Client> all() {
         String sql = "SELECT id, name, stylistId FROM clients";
         try(Connection con = DB.sql2o.open()) {
          return con.createQuery(sql).executeAndFetch(Client.class);
         }
       }

       public void save() {
                 try(Connection con = DB.sql2o.open()) {
                   String sql = "INSERT INTO tasks(description, stylistId) VALUES (:name, :stylistId)";
                   this.id = (int) con.createQuery(sql, true)
                     .addParameter("name", this.name)
                     .addParameter("stylistId", this.stylistId)
                     .executeUpdate()
                     .getKey();
                 }
               }

               public void update(String name) {
try(Connection con = DB.sql2o.open()) {
  String sql = "UPDATE clients SET name = :name WHERE id = :id";
  con.createQuery(sql)
    .addParameter("name", name)
    .addParameter("id", id)
    .executeUpdate();
}
}

public void delete() {
  try(Connection con = DB.sql2o.open()) {
  String sql = "DELETE FROM clients WHERE id = :id;";
  con.createQuery(sql)
    .addParameter("id", id)
    .executeUpdate();
  }
}
}
