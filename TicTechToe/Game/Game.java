package Game;

import java.util.Scanner;
import Board.Board;
import Player.Player;

public class Game {
    Scanner sc = new Scanner(System.in);
    public Board board;
    public Player players[];
    int turn;
    boolean isGameOver;
    int moves;

    public Game(Player players[], Board board) {
        this.players = players;
        this.board = board;

        this.turn = 0;
        this.isGameOver = false;
        this.moves = 0;
    }

    public int getIndex() {
        int size = board.size;
        
        System.out.print("Enter Number of Box from 1 to " + (size * size) + " : ");
        int idx = sc.nextInt();

        if (idx <= 0 || idx > (size * size)) {
            System.out.println("Invalid Index Enter Index Again");
            return getIndex();
        }

        int row = (idx - 1) / size;
        int col = (idx - 1) % size;

        if (board.matrix[row][col] != '-') {
            System.out.println("The Box You have Chosen is Already Occupied, Enter Index Again");
            return getIndex();
        }

        return idx - 1;
    }

    public void play() {
        board.printBoard();

        while (!isGameOver) {
            moves++;
            System.out.println("\nTurn : "+players[turn].getPlayerName());
            int idx = getIndex();

            int size = board.size;
            int row = idx / size;
            int col = idx % size;

            board.matrix[row][col] = players[turn].getPlayerSymbol();

            if (moves >= size * size) {
                board.printBoard();
                System.out.println("Game Draw");
                return;
            }

            if (checkCombination(players[turn].getPlayerSymbol())) {
                isGameOver = true;
                board.printBoard();
                System.out.println("Winner is : " + players[turn].getPlayerName());
                return;
            }

            turn = (turn + 1) % 2;
            board.printBoard();
        }
    }

    public boolean checkCombination(char symbol) {
        int size = board.size;

        for (int i = 0; i < size; i++) {
            int cnt = 0;

            for (int j = 0; j < size; j++) {
                if (board.matrix[i][j] == symbol) {
                    cnt++;
                }
            }

            if (cnt == size) {
                return true;
            }
            cnt = 0;

            for (int j = 0; j < size; j++) {
                if (board.matrix[j][i] == symbol) {
                    cnt++;
                }
            }

            if (cnt == size) {
                return true;
            }

        }
        int cnt=0;

        for(int i=0;i<size;i++){
            if(board.matrix[i][i]==symbol){
                cnt++;
            }
            else{
                break;
            }
        }

        if(cnt==size){
            return true;
        }
        cnt=0;
        for(int i=0;i<size;i++){
            if(board.matrix[i][size-i-1]==symbol){
                cnt++;
            }
            else{
                break;
            }
        }
        return cnt==size;
    }
}
