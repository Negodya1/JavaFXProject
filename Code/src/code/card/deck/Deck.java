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

    public boolean check() {
        ArrayList<CardPair> ids = new ArrayList<>();

        for (int i = 0; i < _cards.size(); i++) {
            int id = _cards.get(i).getId();

            boolean finded = false;
            for (int j = 0; j < ids.size(); j++) {
                if (ids.get(j)._id == id) {
                    if (ids.get(j)._quantity == 2) return false;
                    ids.get(j)._quantity++;
                    finded = true;
                }
            }

            if (!finded) ids.add(new CardPair(id));
        }

        return true;
    }
    public void shuffle() {
        ArrayList<Card> _newDeck = new ArrayList<>();
        while (!_cards.isEmpty()) _newDeck.add(_cards.remove((int) (Math.random() * _cards.size())));

        _cards = _newDeck;
    }
    public Card getCard() {
        return _cards.remove(0);
    }
    public Card lookCard() {
        return _cards.get(0);
    }
    public void addCards(boolean shuffle, Card... cards) {
        for (int i = 0; i < cards.length; i++) {
            _cards.add(cards[i]);
        }
        if (shuffle) shuffle();
    }

    public class CardPair {
        public int _id;
        public int _quantity;

        CardPair(int id) {
            _id = id;
            _quantity = 1;
        }
    }

    public int getSize() {
        return _cards.size();
    }
}
