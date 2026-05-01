import java.util.Scanner;

public class mainhub {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello welcome to the minigame hub area please choose a game to play");
        System.out.println("1. symbol generator");
        System.out.println("2. 21");

        Scanner Scanner = new Scanner(System.in);
        int choice = Scanner.nextInt();
        Scanner.nextLine(); // Consume newline after nextInt to fix double prompt

if (choice == 2) {
            boolean playAgainLoop = true;
            twentyone game = new twentyone();
            while (playAgainLoop) {
                game.placeBet(Scanner);
                game.resetGame();
                game.startGame();
                boolean gameActive = true;
                while (gameActive && !game.isGameOver()) {
                    System.out.println("Would you like to hit or stand?");
                    String hitorstand = Scanner.nextLine().toLowerCase().trim();
                    if (hitorstand.equals("hit")) {
                        game.hit();
                    } else if (hitorstand.equals("stand")) {
                        game.stand();
                    } else {
                        System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
                    }
                }

                System.out.println("Game over. Would you like to play again? (y/n): ");
                String playAgain = Scanner.nextLine().toLowerCase().trim();
                if (!playAgain.equals("y") && !playAgain.equals("yes")) {
                    playAgainLoop = false;
                    System.out.println("Thanks for playing 21!");
                }
            }
        }
    }
}
