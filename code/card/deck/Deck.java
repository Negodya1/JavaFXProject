package code.card.deck;

import code.card.Card;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> _cards;

    public Deck(Card... cards) {
        _cards = new ArrayList<>();

        for (int i = 0; i < cards.length; i++) {
            _cards.add(cards[i]);
        }
    }

    public void shuffle() {
        ArrayList<Card> _newDeck = new ArrayList<>();
        while (!_cards.isEmpty()) _newDeck.add(_cards.remove((int) (Math.random() * _cards.size())));

        _cards = _newDeck;
    }

    public Card getCard() {
        return _cards.remove(0);
    }

    public void addCards(boolean shuffle, Card... cards) {
        for (int i = 0; i < cards.length; i++) {
            _cards.add(cards[i]);
        }
        if (shuffle) shuffle();
    }

    public int getSize() {
        return _cards.size();
    }
}
