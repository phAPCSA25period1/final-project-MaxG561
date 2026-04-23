import java.util.ArrayList;
import java.util.Scanner;

public class twentyone {

    private static ArrayList<String> deck = new ArrayList<>();
    private ArrayList<String> playerHand;
    private ArrayList<String> currentDeck;

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

    public static void startGame() {
        currentDeck = getDeck();
        playerHand = new ArrayList<>();
        System.out.println("Starting game...");
        drawCard();
        drawCard();
        System.out.println("Total hand value: " + getHandValue());
    }

    public int getHandValue() {
        int total = 0;
        for (String card : playerHand) {
            total += getCardValue(card);
        }
        return total;
    }

    private void drawCard() {
        int randomIndex = (int) (Math.random() * currentDeck.size());
        String card = currentDeck.remove(randomIndex);
        playerHand.add(card);
        System.out.println("Drew: " + card + " (Value: " + getCardValue(card) + ")");
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

