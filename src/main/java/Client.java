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
}
