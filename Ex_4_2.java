/*
* Create a program to collect and store all the cards to assist the users in finding all the cards 
* in a given symbol using Collection interface.
* */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Ex4_2 {
    private static final Scanner          SC = new Scanner(System.in);
    private static final Collection<card> cards = new ArrayList<>();

    static class card {
        private final String symbol;
        private final String value;

        public card(String symbol, String value) {
            this.symbol = symbol;
            this.value = value;
        }

        public String get_symbol() { return symbol; }

        @Override
        public String toString() {
            return "[" + value + " of " + symbol + "]";
        }
    }

    private static void add_card() {
        System.out.print("Enter Card Symbol (e.g., Hearts, Diamonds, Clubs, Spades): ");
        String symbol = SC.nextLine();
        
        System.out.print("Enter Card Value (e.g., Ace, 2, King, Queen): ");
        String value = SC.nextLine();

        cards.add(new card(symbol, value));
        
        System.out.println("Card added successfully.");
    }

    private static void find_cards_by_symbol() {
        System.out.print("Enter Symbol to search (e.g., Hearts, Diamonds): ");
        String symbol = SC.nextLine();

        boolean found = false;
        for (card c : cards) {
            if (c.get_symbol().equalsIgnoreCase(symbol)) {
                System.out.println(c);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No cards found for the given symbol.");
        }
    }

    private static void display_cards() {
        if (cards.isEmpty()) {
            System.out.println("No cards available.");
        } else {
            System.out.println("All Cards:");
            cards.forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Card\n2. Find Cards by Symbol\n3. Display All Cards\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = SC.nextInt();
            
            SC.nextLine(); 

            switch (choice) {
                case 1 -> add_card();
                case 2 -> find_cards_by_symbol();
                case 3 -> display_cards();
                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
