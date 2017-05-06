import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
  private String name;
  private int id;

  public Stylist(String name) {
      this.name = name;
  }
//implements getName()
  public String getName() {
      return name;
    }
}
