import java.util.Scanner;

/**
 * Main hub/driver for the mini-game collection.
 * <p>
 * Currently supports launching and playing the 21 (Blackjack-style) game.
 */
public class mainhub {

    /**
     * Entry point for the program.
     * Prints the menu, reads the user's choice, and runs the selected game loop.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) throws Exception {
        // Print a welcome message and the available game options.
        System.out.println("Hello welcome to the minigame hub area please choose a game to play");
        System.out.println("1. 21");

        // Create a scanner to read user input from standard input.
        Scanner Scanner = new Scanner(System.in);

        // Read the user's selected menu option.
        int choice = Scanner.nextInt();

        // Consume the leftover newline so the next nextLine() reads the user's real input.
        Scanner.nextLine();

        // If the user chose game #1, run the 21 game.
        if (choice == 1) {
            // Controls whether the user keeps playing again.
            boolean playAgainLoop = true;

            // Create a new instance of the 21 game.
            twentyone game = new twentyone();

            // Loop until the user chooses not to play again.
            while (playAgainLoop) {
                // Ask the player for a bet and store it in the game.
                game.placeBet(Scanner);

                // Reset hands/state for a new round.
                game.resetGame();

                // Start the round (deal initial cards).
                game.startGame();

                // Track whether the game should continue accepting hit/stand inputs.
                boolean gameActive = true;

                // Continue until the game is over (player busts, dealer busts, or stand resolves outcome).
                while (gameActive && !game.isGameOver()) {
                    // Prompt the user for their next action.
                    System.out.println("Would you like to hit or stand?");

                    // Read the user's choice, normalize it (lowercase + trimmed).
                    String hitorstand = Scanner.nextLine().toLowerCase().trim();

                    // Perform the action based on user input.
                    if (hitorstand.equals("hit")) {
                        // Player takes another card.
                        game.hit();
                    } else if (hitorstand.equals("stand")) {
                        // Player ends their turn and dealer plays out.
                        game.stand();
                    } else {
                        // If input isn't recognized, tell the user and keep looping.
                        System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
                    }
                }

                // Inform the user the round ended and ask to play again.
                System.out.println("Game over. Would you like to play again? (y/n): ");

                // Read the replay response, normalized.
                String playAgain = Scanner.nextLine().toLowerCase().trim();

                // If the user did not confirm replay, stop the loop and end the program.
                if (!playAgain.equals("y") && !playAgain.equals("yes")) {
                    playAgainLoop = false;
                    System.out.println("Thanks for playing 21!");
                }
            }
        }
    }
}

