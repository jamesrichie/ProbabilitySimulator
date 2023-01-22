package sets.cardDeck;

import java.util.*;

import distributions.*;
import static sets.cardDeck.Ranks.*;
import static sets.cardDeck.Suits.*;

public class Deck {

    private List<Card> deck;

    private static final List<String> ranks = Arrays.asList(TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT,
                                                            NINE, TEN, JACK, QUEEN, KING, ACE);
    private static final List<String> suits = Arrays.asList(CLUBS, DIAMONDS, HEARTS, SPADES);

    public Deck() {
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

    public Card draw(Boolean drawWithReplacement) {
        if (drawWithReplacement) {
            return deck.get((int) new DiscreteUniform(0, deck.size() - 1).sample());
        } else {
            return deck.remove((int) new DiscreteUniform(0, deck.size() - 1).sample());
        }
    }
}
