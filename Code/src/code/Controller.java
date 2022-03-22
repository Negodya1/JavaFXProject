package code;

import code.card.Card;
import code.card.deck.Deck;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class Controller {
    @FXML
    private ListView hand;

    private ArrayList<Card> _list = new ArrayList<>();
    private Deck _deck = new Deck(new Card(1, "Knight", "Atk: 2  Def: 2\nSpd: 1  HP: 3", "/Neutral_knight.png",3), new Card(2, "Knight", "Atk: 2  Def: 2\nSpd: 1  HP: 3", "/Neutral_knight.png",3), new Card(3, "Knight 2", "Atk: 2  Def: 2\nSpd: 1  HP: 3", "/Neutral_knight.png",3), new Card(4, "Knight 2", "Atk: 2  Def: 2\nSpd: 1  HP: 3", "/Neutral_knight.png",3), new Card(5, "Knight 2", "Atk: 2  Def: 2\nSpd: 1  HP: 3", "/Neutral_knight.png",3));

    @FXML
    private void click() {
        Card test = new Card(1, "Knight", "Atk: 2  Def: 2\nSpd: 1  HP: 3", "/Neutral_knight.png",3);
        _list.add(test);
        hand.getItems().add(test.getView());

        _deck.shuffle();
    }

    @FXML
    private void click2() {
        _list.get(0).addCost(1);
        hand.getItems().set(0, _list.get(0).getView());
    }

    @FXML
    private void handUp() {
        hand.setTranslateY(0.0);
    }

    @FXML
    private void handDown() {
        hand.setTranslateY(110.0);
    }
}
