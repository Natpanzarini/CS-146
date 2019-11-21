import java.util.*;

public class Prog1
{
   public static void main(String[] args)
   {
      Set<Point> set = new HashSet<>();
      int n = 1000;
      for (int i = 1; i <= n; i++)
         for (int j = 1; j <= n; j++)
            set.add(new Point(i % 2, j % 2));
      System.out.println(set.size());
   }
}
