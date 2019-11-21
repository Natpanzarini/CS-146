import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CertifyHeap {
    // Return true of v is less than w and false otherwise.
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // Return true if a[] represents a maximum-ordered heap and false
    // otherwise. Remember to index from 1.
    private static boolean maxOrderedHeap(Comparable[] a){
        for(int i = 1; i < a.length; i++){
          int child1 = 2*i;
          int child2 = child1 + 1;

          if(child1 > a.length - 1 || child2 > a.length - 1){
            break;
          }

          if(less(a[i],a[child1]) || less(a[i],a[child2])){
            return false;
          }
        }
        return true;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        StdOut.println(maxOrderedHeap(a));
    }
}
