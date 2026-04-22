import java.util.ArrayList;
import java.util.Scanner;

public class twentyone {

    private static ArrayList<String> deck = new ArrayList<>();

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

public static void main(String[] args) throws Exception {
        System.out.println("Deck created with " + getDeck().size() + " cards.");
        getDeck().forEach(System.out::println);
    }

    private static Scanner scanner = new Scanner(System.in);

    public static void scannertest2() {
        System.out.println(scanner.nextLine());

    }
}
