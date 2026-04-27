import java.util.ArrayList;
import java.util.Scanner;

public class twentyone {

    private static ArrayList<String> deck = new ArrayList<>();
    private ArrayList<String> playerHand;
    private ArrayList<String> DealerHand;
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
    public void hit(){
        int randomIndex = (int) (Math.random() * currentDeck.size());
        String card = currentDeck.remove(randomIndex);
        playerHand.add(card);
        System.out.println("Drew: " + card + " (Value: " + getCardValue(card) + ")");
         System.out.println("Total hand value: " + getPlayerHandValue());
         System.out.println("Dealer Total hand value: " + getDealerHandValue());
         if (getPlayerHandValue() > 21) {
             System.out.println("Player busts!");
         }
         if (getDealerHandValue() > 21) {
             System.out.println("Dealer busts!");
         }
    }

    public void dealerLogic(){
        if (getDealerHandValue()<17){
        while (getDealerHandValue()<17){
            drawDealerCard();
            System.out.println("Dealer Total hand value: " + getDealerHandValue());
        }
    }
       else if (getDealerHandValue()>21){
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
