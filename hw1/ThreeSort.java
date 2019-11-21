// ThreeSort.java: Takes three integers as command-line arguments and writes
// them in ascending order, separated by spaces.

//import edu.princeton.cs.algs4.StdOut;

public class ThreeSort {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);

        int lowest = Math.min(Math.min(a, b), c);
        int max = Math.max(Math.max(a, b), c);
        int middle = 0;
        if ((lowest == a && max == b) || (lowest == b && max == a)) {
            middle = c;
        } else if ((lowest == b && max == c) || (lowest == c && max == b)) {
            middle = a;
        } else if ((lowest == c && max == a) || lowest == a && max == c) {
            middle = b;
        }

        System.out.println(lowest + " " + middle + " " + max);

    }
}
