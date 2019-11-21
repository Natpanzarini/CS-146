import java.util.Random;
// A data type representing a six-sided die.
public class Die implements Comparable<Die> {
    private int value; // face value

    // Roll the die.
    public void roll() {
        // Code Here
        Random rand = new Random();
        this.value = rand.nextInt(5) + 1;
    }

    // Face value of the die.
    public int value() {
        // Code Here
        return this.value;
    }

    // Does the die have the same face value as that?
    public boolean equals(Die that) {
        // Code Here
        return that.value() == this.value();
    }

    // A negative integer, zero, or positive integer depending on whether this
    // die's value is less than, equal to, or greater than the that die's value.
    public int compareTo(Die that) {
        // Code Here
        if(this.value() > that.value()){
          return 1;
        }
        else if(this.value() < that.value()){
          return -1;
        }
        else{
          return 0;
        }
    }

    // A string representation of the die giving the current face value.
    public String toString() {
        // Code Here
        String temp = Integer.toString(this.value());
        return temp;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int z = Integer.parseInt(args[2]);
        Die a = new Die();
        a.roll();
        while (a.value() != x) {
            a.roll();
        }
        Die b = new Die();
        b.roll();
        while (b.value() != y) {
            b.roll();
        }
        Die c = new Die();
        c.roll();
        while (c.value() != z) {
            c.roll();
        }
        StdOut.println(a);
        StdOut.println(a.equals(b));
        StdOut.println(b.equals(c));
        StdOut.println(a.compareTo(b));
        StdOut.println(b.compareTo(c));
    }
}
