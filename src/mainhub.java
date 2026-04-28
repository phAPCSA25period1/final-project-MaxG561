import java.util.Scanner;
public class mainhub {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello welcome to the minigame hub area please choose a game to play");
        System.out.println("1. symbol generator");
        System.out.println("2. 21");

        Scanner Scanner = new Scanner(System.in);
        int choice=Scanner.nextInt();
        boolean playing21=false;
        if (choice==2){
            playing21=true;

        System.out.println(choice +"you chose 21");
        twentyone game = new twentyone();
        game.startGame();

        while (playing21=true){
        System.out.println("would you to hit or stand");
        String hitorstand=Scanner.nextLine();
        if (hitorstand.equals("hit") ){
        game.hit();
        }
        if ((hitorstand.equals("stand"))){
        game.stand();
    }
}
        }

    }
    }

