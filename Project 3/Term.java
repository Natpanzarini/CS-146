import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

// An immutable data type that represents an autocomplete term: a query string
// and an associated real-valued weight.
public class Term implements Comparable<Term> {
    private String query;
    private long weight;

    // Construct a term given the associated query string, having weight 0.
    public Term(String query) {
        if (query == null) {
            throw new java.lang.NullPointerException();
        }

        this.query = query;
        this.weight = 0;
    }

    // Construct a term given the associated query string and weight.
    public Term(String query, long weight) {
        if (query == null) {
            throw new java.lang.NullPointerException();
        } else if (weight < 0) {
            throw new java.lang.IllegalArgumentException();
        }

        this.query = query;
        this.weight = weight;
    }

    // A reverse-weight comparator.
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();
    }

    // Helper reverse-weight comparator.
    private static class ReverseWeightOrder implements Comparator<Term> {
        public int compare(Term v, Term w) {
            if (v.weight > w.weight) {
                return -1;
            } else if (v.weight < w.weight) {
                return 1;
            }
            return 0;
        }
    }

    // A prefix-order comparator.
    public static Comparator<Term> byPrefixOrder(int r) {
        return new PrefixOrder(r);
    }

    // Helper prefix-order comparator.
    private static class PrefixOrder implements Comparator<Term> {
        private int r;

        PrefixOrder(int r) {
            this.r = r;
        }

        public int compare(Term v, Term w) {
            return v.compareTo(w);
        }
    }

    // Compare this term to that in lexicographic order by query and
    // return a negative, zero, or positive integer based on whether this
    // term is smaller, equal to, or larger than that term.
    public int compareTo(Term that) {
        //smaller = comes before other one in the alphabet = -1
        //bigger = comes after other one in the alphabet = 1
        //equal = same string = 0;
        for (int i = 0; i < Math.min(this.query.length(), that.query.length()); i++) {
            if (this.query.charAt(i) < that.query.charAt(i)) {
                return -1;
            } else if (this.query.charAt(i) > that.query.charAt(i)) {
                return 1;
            }
        }

        //terms have to be equal at the point, so we return 0.
        return 0;
    }

    // A string representation of this term.
    public String toString() {
        return weight + "\t" + query;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        StdOut.printf("Top %d by lexicographic order:\n", k);
        Arrays.sort(terms);
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
        StdOut.printf("Top %d by reverse-weight order:\n", k);
        Arrays.sort(terms, Term.byReverseWeightOrder());
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
    }
}
