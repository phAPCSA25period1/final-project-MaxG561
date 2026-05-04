import java.util.ArrayList;
import java.util.Scanner;

public class twentyone {

    private static ArrayList<String> deck = new ArrayList<>();
    private ArrayList<String> playerHand = new ArrayList<>();
    private ArrayList<String> DealerHand = new ArrayList<>();
    private ArrayList<String> currentDeck;
    public int balance = 500;
    public int bet;

    // Method to calculate reward dynamically
    public int getReward() {
        return bet;
    }

    // Method to place a bet
    public void placeBet(Scanner scanner) {
        System.out.println("Your current balance is: " + balance);
        System.out.println("Enter your bet amount:");
        int playerBet = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        while (playerBet > balance || playerBet <= 0) {
            if (playerBet > balance) {
                System.out.println("Insufficient balance! You have " + balance + ". Please enter a lower bet:");
            } else {
                System.out.println("Bet must be greater than 0! Please enter a valid bet:");
            }
            playerBet = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        }
        bet = playerBet;
        System.out.println("You placed a bet of: " + bet);
    }

    static {
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] values = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
        for (String suit : suits) {
            for (String value : values) {
                deck.add(value + " of " + suit);
            }
        }
    }

    public static ArrayList<String> getDeck() {
        return new ArrayList<>(deck);
    }

    public static int getCardValue(String pulledCard) {
        String face = pulledCard.substring(0, pulledCard.indexOf(" of "));
        if (face.equals("J") || face.equals("Q") || face.equals("K")) {
            return 10;
        } else if (face.equals("A")) {
            return 11;
        } else {
            return Integer.parseInt(face);
        }
    }

    public void resetGame() {
        playerHand.clear();
        DealerHand.clear();
        currentDeck = getDeck();
        gameOver = false;
    }

    public boolean isGameOver() {
        return getPlayerHandValue() > 21 || getDealerHandValue() > 21 || gameOver;
    }

    private boolean gameOver = false;

    public void startGame() {
        currentDeck = getDeck();
        playerHand = new ArrayList<>();
        DealerHand = new ArrayList<>();
        System.out.println("Starting game...");
        drawPlayerCard();
        drawPlayerCard();
        drawDealerCard();
        drawDealerCard();
        System.out.println("Total hand value: " + getPlayerHandValue());
        System.out.println("Dealer Total hand value: " + getDealerHandValue());
    }

    public int getPlayerHandValue() {
        int total = 0;
        for (String card : playerHand) {
            total += getCardValue(card);
        }
        return total;
    }

    public int getDealerHandValue() {
        int total = 0;
        for (String card : DealerHand) {
            total += getCardValue(card);
        }
        return total;
    }

    public void hit() {
        int randomIndex = (int) (Math.random() * currentDeck.size());
        String card = currentDeck.remove(randomIndex);
        playerHand.add(card);
        System.out.println("Drew: " + card + " (Value: " + getCardValue(card) + ")");
        System.out.println("Total hand value: " + getPlayerHandValue());
        System.out.println("Dealer Total hand value: " + getDealerHandValue());
        if (getPlayerHandValue() > 21) {
            System.out.println("Player busted with " + getPlayerHandValue() + "!");
            balance -= bet;
            System.out.println("You lost " + bet + "! Your new balance is " + balance);
            gameOver = true;
            return;
        }
    }

    public void stand() {
        if (getPlayerHandValue() > 21) {
            System.out.println("Dealer wins! Player busted.");
            gameOver = true;
            return;
        }
        dealerLogic();
        // Determine winner with balance updates
        if (getPlayerHandValue() > 21) {
            System.out.println("Dealer wins! Player busted.");
            balance -= bet;
            System.out.println("You lost " + bet + "! Your new balance is " + balance);
        } else if (getDealerHandValue() > 21) {
            System.out.println("Player wins! Dealer busted.");
            balance += bet ;
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
            System.out.println("Push! It's a tie.");
            System.out.println("Your bet of " + bet + " is returned.");
        }
        gameOver = true;
    }

    public void dealerLogic() {
        if (getDealerHandValue() < 17) {
            while (getDealerHandValue() < 17) {
                drawDealerCard();
                System.out.println("Dealer Total hand value: " + getDealerHandValue());
            }
            System.out.println("Dealer final total: " + getDealerHandValue());
            if (getDealerHandValue() > 21) {
                System.out.println("Dealer busted with " + getDealerHandValue() + "!");
                gameOver = true;
            }
        } else if (getDealerHandValue() > 21) {
            System.out.println("Dealer Total hand value: " + getDealerHandValue());
        }
    }

    private void drawPlayerCard() {
        int randomIndex = (int) (Math.random() * currentDeck.size());
        String card = currentDeck.remove(randomIndex);
        playerHand.add(card);
        System.out.println("Drew: " + card + " (Value: " + getCardValue(card) + ")");
    }

    private void drawDealerCard() {
        int randomIndex = (int) (Math.random() * currentDeck.size());
        String card = currentDeck.remove(randomIndex);
        DealerHand.add(card);
        System.out.println("Dealer Drew: " + card + " (Value: " + getCardValue(card) + ")");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Deck created with " + getDeck().size() + " cards.");
        getDeck().forEach(System.out::println);
    }

    private static Scanner scanner = new Scanner(System.in);

    public static void scannertest2() {
        System.out.println(scanner.nextLine());

    }
}
