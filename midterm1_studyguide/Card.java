import java.util.Comparator;

public class Card{

  private int suit;
  private int rank;

  public Card(int suit, int rank){
    this.suit = suit;
    this.rank = rank;
  }

  public boolean equals(Card that){
    return this.compareTo(that);
  }

  public String toString(){
    System.out.print(rank + " of " + suit);
  }

  public int compareTo(Card that){
    int thatRank = that.getRank();
    if(rank < thatRank){
      return -1;
    }
    else if(rank > thatRank){
      return 1;
    }

      return 0;
  }

  static class SuitOrder implements Comparator{
    public int compare ( Card c1 , Card c2) {
      return c1. suit - c2. suit ;
    }
  }

  static class ReverseRankOrder implements Comparator{
    public int compare ( Card c1 , Card c2) {
      return c2. rank - c1. rank ;
    }
  }
}








}
