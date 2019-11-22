import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

// Implements binary search for clients that may want to know the index of
// either the first or last key in a (sorted) collection of keys.
public class BinarySearchDeluxe {
    // The index of the first key in a[] that equals the search key,
    // or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        if (a == null || key == null || comparator == null) {
            throw new java.lang.NullPointerException();
        }

        return firstBinarySearch(a,key,comparator,0,a.length - 1,a.length-1);
    }

    // The index of the last key in a[] that equals the search key,
    // or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new java.lang.NullPointerException();
        }

        return lastBinarySearch(a,key,comparator,0,a.length - 1,0);
    }

    private static <Key> int firstBinarySearch(Key[] a, Key key, Comparator<Key> comparator, int left, int right,int currEarliest){
      int mid = (left + right)/2;
      if(right >= left){
        if (comparator.compare(a[mid], key) == 0) {
          currEarliest = mid;
          if(right - left <= 1){
            return currEarliest;
          }
            return Math.min(firstBinarySearch(a,key,comparator,left,mid-1,currEarliest),currEarliest);
        } else if (comparator.compare(a[mid], key) > 0) {
          if(right - left <= 1){
            return currEarliest;
          }
            return Math.min(firstBinarySearch(a,key,comparator,left,mid-1,currEarliest),currEarliest);
        } else if (comparator.compare(a[mid], key) < 0) {
          if(right - left <= 1){
            return currEarliest;
          }
            return Math.min(firstBinarySearch(a,key,comparator,mid+1,right,currEarliest),currEarliest);
        }
      }

      return -1;
    }

    private static <Key> int lastBinarySearch(Key[] a, Key key, Comparator<Key> comparator, int left, int right,int currLatest){
      int mid = (left + right)/2;
      if(right >= left){
        if (comparator.compare(a[mid], key) == 0) {
          currLatest = mid;
          if(right - left <= 1){
            return currLatest;
          }
            return Math.max(lastBinarySearch(a,key,comparator,mid+1,right,currLatest),currLatest);
        } else if (comparator.compare(a[mid], key) > 0) {
          if(right - left <= 1){
            return currLatest;
          }
            return Math.max(lastBinarySearch(a,key,comparator,left,mid-1,currLatest),currLatest);
        } else if (comparator.compare(a[mid], key) < 0) {
          if(right - left <= 1){
            return currLatest;
          }
            return Math.max(lastBinarySearch(a,key,comparator,mid+1,right,currLatest),currLatest);
        }
      }

      return -1;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String prefix = args[1];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        Arrays.sort(terms);

        Term term = new Term(prefix);
        Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());
        int i = BinarySearchDeluxe.firstIndexOf(terms, term, prefixOrder);
        int j = BinarySearchDeluxe.lastIndexOf(terms, term, prefixOrder);
        int count = i == -1 && j == -1 ? 0 : j - i + 1;
        StdOut.println(count);
    }
}
