package sets.cardDeck;

import java.util.*;

import distributions.*;

public class Deck {

    private List<Card> deck;

    private static final List<String> ranks = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");
    private static final List<String> suits = Arrays.asList("clubs", "diamonds", "hearts", "spades");

    public Deck(boolean drawWithReplacement) {
        deck = new ArrayList<>();

        for (String rank : ranks) {
            for (String suit : suits) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    public int size() {
        return deck.size();
    }

    public Card draw() {
        return deck.remove((int) new DiscreteUniform(0, deck.size() - 1).sample());
    }
}
