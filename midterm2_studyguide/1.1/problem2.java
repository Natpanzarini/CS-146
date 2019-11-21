class problem2{
  public static int [][] mystery (int n, int k) {
    int [][] a = new int [n][n];
    for (int i = 0; i < n; i ++) {
        for (int j = 0; j < n; j++) {
            a[i][j] = (i > j)? 0 : k;
          }
        }
        return a;
  }

  public static void main(String[] args) {
    int[][] a = mystery(4,5);
    for (int i = 0; i < n; i ++) {
        for (int j = 0; j < n; j++) {
          System.out.print(a[i][j]);
      }
    }
  }
}
