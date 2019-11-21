public class Point
{
   private int x;
   private int y;
   public Point(int x, int y) { this.x = x; this.y = y; }
   public String toString() { return getClass().getName() + "[x=" + x + ",y=" + y + "]"; }
   public boolean equals(Object otherObject)
   {
      if (!(otherObject instanceof Point)) return false;
      Point other = (Point) otherObject;
      return x == other.x && y == other.y;
   }
}
