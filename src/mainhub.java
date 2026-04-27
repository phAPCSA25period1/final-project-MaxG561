import java.util.Scanner;
public class mainhub {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello welcome to the minigame hub area please choose a game to play");
        System.out.println("1. symbol generator");
        System.out.println("2. 21");
        Scanner Scanner = new Scanner(System.in);
        int choice=Scanner.nextInt();
        if (choice==2){
        System.out.println(choice +"you chose 21");
        twentyone game = new twentyone();
        game.startGame();
        game.hit();
        }
    }
}
