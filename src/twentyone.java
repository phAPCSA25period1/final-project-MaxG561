import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implements a simple Blackjack/21-style mini-game.
 * Handles deck creation, dealing cards, hit/stand logic, and balance updates.
 */
public class twentyone {

    /**
     * Full (static) set of cards used to generate a new deck each round.
     * Each card is represented as a human-readable string.
     */
    private static ArrayList<String> deck = new ArrayList<>();

    /** Holds the player's currently dealt cards for this round. */
    private ArrayList<String> playerHand = new ArrayList<>();

    /** Holds the dealer's currently dealt cards for this round. */
    private ArrayList<String> DealerHand = new ArrayList<>();

    /**
     * The working deck for the current round (cards are removed from this list).
     */
    private ArrayList<String> currentDeck;

    /** Player's running balance across rounds. */
    public int balance = 500;

    /** Bet amount for the current round. */
    public int bet;

    /**
     * Returns the bet amount as the current 'reward'.
     * (Note: reward meaning depends on how balance is updated elsewhere.)
     *
     * @return reward value for the current bet
     */
    public int getReward() {
        return bet;
    }

    /**
     * Prompts the player to enter a valid bet amount and stores it in bet.
     *
     * @param scanner scanner used to read numeric input from the user
     */
    public void placeBet(Scanner scanner) {
        // Show the player's current balance.
        System.out.println("Your current balance is: " + balance);
        // Ask the user for how much to bet.
        System.out.println("Enter your bet amount:");

        // Read the initial bet value.
        int playerBet = scanner.nextInt();
        // Consume newline so future nextLine() calls read the correct input.
        scanner.nextLine();

        // Keep asking until the bet is valid (positive and within balance).
        while (playerBet > balance || playerBet <= 0) {
            if (playerBet > balance) {
                // Inform the user the bet is too large.
                System.out.println("Insufficient balance! You have " + balance + ". Please enter a lower bet:");
            } else {
                // Inform the user the bet must be positive.
                System.out.println("Bet must be greater than 0! Please enter a valid bet:");
            }

            // Read a new bet amount and consume its newline.
            playerBet = scanner.nextInt();
            scanner.nextLine();
        }

        // Store the validated bet.
        bet = playerBet;
        // Confirm the bet to the user.
        System.out.println("You placed a bet of: " + bet);
    }

    /** Static initializer builds the full 52-card deck (values + suits). */
    static {
        // Card suits.
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        // Card faces/values.
        String[] values = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

        // Create one string entry for every (value, suit) pair.
        for (String suit : suits) {
            for (String value : values) {
                deck.add(value + " of " + suit);
            }
        }
    }

    /**
     * @return a new deck list containing all cards (copy of the static deck)
     */
    public static ArrayList<String> getDeck() {
        return new ArrayList<>(deck);
    }

    /**
     * Converts a card string into a Blackjack value.
     *
     * @param pulledCard card string like "A of Hearts"
     * @return integer value of that card
     */
    public static int getCardValue(String pulledCard) {
        // Extract the face portion (before " of ").
        String face = pulledCard.substring(0, pulledCard.indexOf(" of "));

        // Face cards are worth 10.
        if (face.equals("J") || face.equals("Q") || face.equals("K")) {
            return 10;
        } else if (face.equals("A")) {
            // Ace is treated as 11.
            return 11;
        } else {
            // Number cards are parsed directly.
            return Integer.parseInt(face);
        }
    }

    /** Resets hands and round state so a new round can start. */
    public void resetGame() {

        // Remove previously dealt cards.
        playerHand.clear();
        DealerHand.clear();

        // Start a fresh deck for the new round.
        currentDeck = getDeck();

        // Clear the round-over flag.
        gameOver = false;
    }

    /**
     * @return true if the round should end due to busts or explicit game-over
     */
    public boolean isGameOver() {
        // End if player or dealer has exceeded 21, or if gameOver was set.
        return getPlayerHandValue() > 21 || getDealerHandValue() > 21 || gameOver;
    }

    /** Tracks whether the round has been resolved. */
    private boolean gameOver = false;

    /** Deals the starting cards for a round. */
    public void startGame() {
        // Create a new working deck.
        currentDeck = getDeck();

        // Re-initialize hands.
        playerHand = new ArrayList<>();
        DealerHand = new ArrayList<>();

        System.out.println("Starting game...");

        // Deal two cards to the player.
        drawPlayerCard();
        drawPlayerCard();

        // Deal two cards to the dealer.
        drawDealerCard();
        drawDealerCard();

        // Show totals to the user.
        System.out.println("Total hand value: " + getPlayerHandValue());
        System.out.println("Dealer Total hand value: " + getDealerHandValue());
    }

    /** @return sum of the player's card values in the current round */
    public int getPlayerHandValue() {
        int total = 0;

        // Add each card's value.
        for (String card : playerHand) {
            total += getCardValue(card);
        }

        return total;
    }

    /** @return sum of the dealer's card values in the current round */
    public int getDealerHandValue() {
        int total = 0;

        // Add each card's value.
        for (String card : DealerHand) {
            total += getCardValue(card);
        }

        return total;
    }

    /**
     * Gives the player one additional random card.
     * If the player busts, balance is reduced and the round ends.
     */
    public void hit() {
        // Pick a random card index from the remaining deck.
        int randomIndex = (int) (Math.random() * currentDeck.size());

        // Remove that card from the deck and add it to the player's hand.
        String card = currentDeck.remove(randomIndex);
        playerHand.add(card);

        // Print what was drawn and current totals.
        System.out.println("Drew: " + card + " (Value: " + getCardValue(card) + ")");
        System.out.println("Total hand value: " + getPlayerHandValue());
        System.out.println("Dealer Total hand value: " + getDealerHandValue());

        // If total exceeds 21, the player busts.
        if (getPlayerHandValue() > 21) {
            System.out.println("Player busted with " + getPlayerHandValue() + "!");

            // Deduct the bet from the player's balance.
            balance -= bet;
            System.out.println("You lost " + bet + "! Your new balance is " + balance);

            // Mark the game as over.
            gameOver = true;
            return;
        }
    }

    /**
     * Ends the player's turn, runs dealer logic, and updates balance based on the
     * outcome.
     */
    public void stand() {
        // If the player already busted, the dealer wins immediately.
        if (getPlayerHandValue() > 21) {
            System.out.println("Dealer wins! Player busted.");
            gameOver = true;
            return;
        }

        // Dealer draws until they reach the minimum threshold.
        dealerLogic();

        // Determine the winner and update balance.
        if (getPlayerHandValue() > 21) {
            System.out.println("Dealer wins! Player busted.");
            balance -= bet;
            System.out.println("You lost " + bet + "! Your new balance is " + balance);
        } else if (getDealerHandValue() > 21) {
            System.out.println("Player wins! Dealer busted.");
            balance += bet;
            System.out.println("You won " + bet + "! Your new balance is " + balance);
        } else if (getPlayerHandValue() > getDealerHandValue()) {
            System.out.println("Player wins!");
            balance += bet;
            System.out.println("You won " + bet + "! Your new balance is " + balance);
        } else if (getDealerHandValue() > getPlayerHandValue()) {
            System.out.println("Dealer wins!");
            balance -= bet;
            System.out.println("You lost " + bet + "! Your new balance is " + balance);
        } else {
            // Tie: no win/loss; bet is considered returned.
            System.out.println("Push! It's a tie.");
            System.out.println("Your bet of " + bet + " is returned.");
        }

        // Mark the game as finished.
        gameOver = true;
    }

    /**
     * Dealer draw behavior: hits until dealer hand value reaches at least 17.
     */
    public void dealerLogic() {
        // If dealer is below 17, keep drawing.
        if (getDealerHandValue() < 17) {
            while (getDealerHandValue() < 17) {
                // Draw another card and print updated total.
                drawDealerCard();
                System.out.println("Dealer Total hand value: " + getDealerHandValue());
            }

            // Show dealer final total.
            System.out.println("Dealer final total: " + getDealerHandValue());

            // If dealer busts, end the round.
            if (getDealerHandValue() > 21) {
                System.out.println("Dealer busted with " + getDealerHandValue() + "!");
                gameOver = true;
            }
        } else if (getDealerHandValue() > 21) {
            // Dealer already busts; print current total.
            System.out.println("Dealer Total hand value: " + getDealerHandValue());
        }
    }

    /** Draws one random card from the deck and adds it to the player's hand. */
    private void drawPlayerCard() {
        // Select random card index.
        int randomIndex = (int) (Math.random() * currentDeck.size());

        // Remove the card from the deck.
        String card = currentDeck.remove(randomIndex);

        // Add it to the player's hand.
        playerHand.add(card);

        // Print the result.
        System.out.println("Drew: " + card + " (Value: " + getCardValue(card) + ")");
    }

    /** Draws one random card from the deck and adds it to the dealer's hand. */
    private void drawDealerCard() {
        // Select random card index.
        int randomIndex = (int) (Math.random() * currentDeck.size());

        // Remove the card from the deck.
        String card = currentDeck.remove(randomIndex);

        // Add it to the dealer's hand.
        DealerHand.add(card);

        // Print the result.
        System.out.println("Dealer Drew: " + card + " (Value: " + getCardValue(card) + ")");
    }

    /**
     * Debug/testing main method for verifying deck generation.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Deck created with " + getDeck().size() + " cards.");
        getDeck().forEach(System.out::println);
    }

    /** Scanner instance for the optional test method below. */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Simple scanner test helper (reads one line and prints it).
     */
    public static void scannertest2() {
        System.out.println(scanner.nextLine());

    }
}
