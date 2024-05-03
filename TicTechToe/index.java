import Board.Board;
import Player.Player;
import Game.Game;
import java.util.*;

public class index {
    
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        //Take input of size of Board
        System.out.print("Enter Size of Board : ");
        int n=sc.nextInt();
        Board board = new Board(n);


        while(true){
            
            //Taking Details of players
            System.out.print("Enter No of Players : ");
            int x;
            while((x=sc.nextInt())<2){
                System.out.println("Invalid Number Of Players");
                System.out.print("Enter Number Of Players Again : ");
            }
            System.out.println("Enter Details of Players");
            Player players[]=new Player[x];
            HashSet<Character> set=new HashSet<>();
            for(int i=0;i<x;i++){
                while(true){
                    System.out.println("< Player "+(i+1)+" >");
                    players[i]= getPlayerDetails();

                    if(set.contains(players[i].getPlayerSymbol())){
                        System.out.println("Symbol Has Been Taken");
                        System.out.println("Enter Details of Player Again");
                    }
                    else{
                        System.out.println("\n<- Details Stored successfully ->");
                        players[i].printPlayerDetails();
                        System.out.println();
                        set.add(players[i].getPlayerSymbol());
                        break;
                    }
                }

            }

            Game game = new Game(players, board);
            game.play();
            return;
        }
    
    }

    public static Player getPlayerDetails() {
        Player player = new Player();
        // Get Name
        System.out.print("Enter Name : ");
        String name = "";
        while ((name = sc.next()).equals("")) {
            System.out.println("Enter Valid Name");
            System.out.print("Enter Name Again : ");
        }
        player.setName(name);

        // Get Symbol
        System.out.print("Enter Symbol (Character Except '-' ) : ");
        char symbol=' ';
        while((symbol = sc.next().charAt(0))==' ' || symbol=='-'){
            System.out.println("Enter Valid Symbol");
            System.out.print("Enter Symbol Again : ");
        }
        player.setSymbol(symbol);
        return player;
    }
}
