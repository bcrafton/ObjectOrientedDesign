import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Brian on 2/13/2015.
 */
public class main {

  public static void main(String[] args) {
      /*
    LinkedList<NewCoinGamePlayer> l = new LinkedList<NewCoinGamePlayer>();
      l.add(new NewCoinGamePlayer("brian", 10));
      l.add(new NewCoinGamePlayer("david", 10));


      NewCoinGamePlayer[] files = l.toArray(new NewCoinGamePlayer[l.size()]);

      NewCoinGamePlayer temp = files[0];
      files[0] = files[1];
      files[1] = temp;


      System.out.println(l.get(0).name);
      System.out.println(l.get(1).name);
      can use the objects methods if do a toArray but cannot move there order in the list if moved in the array
    */
    HashSet<String> x = new HashSet<String>();

    Hashtable<String, Integer> h = new Hashtable<String, Integer>();
    h.put("Brian", 10);
    Set<String> keys = h.keySet();
    for (String key : keys) {
      System.out.println("Value of " + key + " is: " + h.get(key));
    }
  }

}
