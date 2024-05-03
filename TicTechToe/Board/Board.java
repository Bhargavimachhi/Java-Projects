package Board;

import java.util.Arrays;

public class Board {
    public char matrix[][];
    public int size;

    public Board(int size) {
        this.size = size;
        matrix = new char[size][size];

        for (char arr[] : matrix)
            Arrays.fill(arr, '-');
    }

    public void printBoard() {
        for (char arr[] : matrix) {
            for (char ch : arr) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }
}
