import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.Comparator;

@SuppressWarnings({"rawtypes", "unchecked"})
// A data type that provides autocomplete functionality for a given set of
// string and weights, using Term and BinarySearchDeluxe.
public class Autocomplete {
    private Term[] terms;
    private BinarySearchDeluxe search;

    // Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
      if(terms == null){
        throw new java.lang.NullPointerException();
      }
        search = new BinarySearchDeluxe();
        this.terms = terms;
        Arrays.sort(this.terms);
    }

    // All terms that start with the given prefix, in descending order of
    // weight.
    public Term[] allMatches(String prefix) {
      if(prefix == null){
        throw new java.lang.NullPointerException();
      }

      Term word = new Term(prefix);
      Comparator comparator = Term.byPrefixOrder(prefix.length());

      //to find all terms that match, we find left most term and right most term and take every index [left,right]

      //finding first and last index of matching words
      int left = BinarySearchDeluxe.firstIndexOf(terms, word, comparator);
      int right = BinarySearchDeluxe.lastIndexOf(terms, word, comparator);

      Term[] match = new Term[right-left];
      int index = left;
      for(int i = 0; i < match.length; i++){
        if(index <= right){
          match[i] = terms[index];
          index++;
        }
      }

      Comparator weightComp = Term.byReverseWeightOrder();
      Arrays.sort(match,weightComp);

      return match;
    }

    // The number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
      Term word = new Term(prefix);
      Comparator comparator = Term.byPrefixOrder(prefix.length());
      int left = BinarySearchDeluxe.firstIndexOf(terms, word, comparator);
      int right = BinarySearchDeluxe.lastIndexOf(terms, word, comparator);

      return right - left;
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++) {
                StdOut.println(results[i]);
            }
        }
    }
}
