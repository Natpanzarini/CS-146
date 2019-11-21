class InsertionSort{

  private static boolean less(int a, int b){
    return a < b;
  }

  private static void exch(char[] a, int i, int j) {
      char swap = a[i];
      a[i] = a[j];
      a[j] = swap;
  }

  public static void sort(char[] a){
    int N = a.length;
    for(int i = 1; i < N; i++){
      for(int j = i; j > 0 && less(a[j],a[j-1]); j--){
        exch(a,j,j-1);
      }
    }
  }

  public static void main(String[] args) {
    String sTest = "I N D I R E C T I N S E R T I O N S O R T E X A M P L E";
    char[] input = sTest.toCharArray();
    int[] test = new int[sTest.length()];
    sort(input);
    for(char c: input){
      System.out.print(c + " ");
    }
  }
}
