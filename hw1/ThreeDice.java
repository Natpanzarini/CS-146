// ThreeDice.java: Writes the sum of three random integers between 1 and 6, such
// as you might get when rolling three dice.

import java.util.Random;

public class ThreeDice {
    public static void main(String[] args) {
        Random rand = new Random();
        final int NUM_DICE = 3;
        int sum = 0;
        for (int i = 0; i < NUM_DICE; i++) {
            sum += rand.nextInt(5) + 1;
        }
        System.out.println(sum);
    }
}
