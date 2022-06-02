package code;

import code.card.Card;
import code.card.deck.Deck;
import code.network.Network;
import code.tile.world.World;
import code.unit.Unit;
import code.unit.UnitFraction;
import code.unit.UnitType;
import code.utilities.Const;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.event.Event;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.io.IOException;

public class Controller {
    private ArrayList<ArrayList<Unit>> army = new ArrayList<>();
    private ArrayList<String> unitlist = new ArrayList<>();

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Network network;

    private int intRedMana;
    private int intMana;
    private int intBlueMana;

    boolean rdy1;
    boolean rdy2;

    Card curCard;

    @FXML
    private Label redmana;
    @FXML
    private Label mana;
    @FXML
    private Label bluemana;
    @FXML
    private Label lwins;
    @FXML
    private Label llose;
    @FXML
    private Label username;

    @FXML
    private Label error;

    @FXML
    private Button playcardbtn;

    @FXML
    private Slider zoom;

    @FXML
    private ListView hand;

    private ArrayList<Card> _list = new ArrayList<>();
    private Deck _deck;
    private Deck _discard;

    @FXML
    private Group world;
    private World _world = new World();

    @FXML
    private Group units;

    @FXML
    private HBox playcard;
    @FXML
    private HBox spawnunit;

    @FXML
    private Button pos1;
    @FXML
    private Button pos2;
    @FXML
    private Button pos3;
    @FXML
    private Button pos4;
    @FXML
    private Button pos5;
    @FXML
    private Button pos6;
    @FXML
    private Button pos7;

    @FXML
    private Group mainGroup;

    @FXML
    private Group leftgroup;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;
    @FXML
    private PasswordField passwordcheck;

    @FXML
    private Button startGame;

    @FXML
    private void click() {
        startGame.setVisible(false);

        for (int i = 0; i < 7; i++) {
            int end = 4;
            if (i > 0 && i < 6) {
                if (i == 1 || i == 5) end = 5;
                else if (i == 2 || i == 4) end = 6;
                else end = 7;
            }

            ArrayList<Unit> row = new ArrayList<>();
            for (int j = 0; j < end; j++) {
                row.add(null);
            }
            army.add(row);
        }

        _deck = new Deck();
        _discard = new Deck();

        network = new Network();
        network.send(Const.GET_CARDS);

        String res;
        int size = network.waitForInt();
        for (; size > 0; size--) {
            res = network.waitForString();
            _deck.addCards(false, new Card(res), new Card(res), new Card(res));
            network.send(1);
        }
        _deck.shuffle();

        drawACard(5);

        hand.getItems().clear();

        for (int i = 0; i < _list.size(); i++) {
            hand.getItems().add(_list.get(i).getView());
        }

        network = new Network();
        network.send(Const.GET_UNITS);
        size = network.waitForInt();
        for (; size > 0; size--) {
            res = network.waitForString();
            unitlist.add(res);
            network.send(1);
        }

        world.getChildren().clear();
        world.getChildren().addAll(_world.getView());

        leftgroup.getChildren().add(new ImageView("Mana_orbs.png"));

        Unit soldier = new Unit(UnitType.HERO, UnitFraction.NEUTRAL, 0, 4, 1);
        army.get(3).set(0, soldier);

        soldier = new Unit(UnitType.HERO, UnitFraction.NEUTRAL, 1, 4, 7);
        army.get(3).set(6, soldier);

        units.getChildren().clear();
        for (int i = 0; i < army.size(); i++) {
            for (int j = 0; j < army.get(i).size(); j++) {
                if (army.get(i).get(j) != null) units.getChildren().add(army.get(i).get(j).getView());
            }
        }
    }

    @FXML
    private void login(Event e) throws IOException {
        Network net = new Network();
        net.send(Const.LOGIN);
        if (net.waitForInt() >= 0) {
            net.send(login.getText());
            net.waitForInt();
            net.send(password.getText());

            int status = net.waitForInt();
            System.out.println(status);
            if (status == Const.INVALID_LOGIN) error.setText("* Пользователя с таким именем не существует.\n* Может необходима регистрация?");
            else if (status == Const.WRONG_PASSWORD) error.setText("* Неверное имя пользователя или пароль");
            else if (status == Const.SUCCESS_LOGIN) {
                net.send(1);

                root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root, 1366, 768);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    @FXML
    private void updateStats() {
        if (username.getText().length() == 0) {
            network = new Network();
            network.send(Const.GET_STATS);
            String wl = network.waitForString();
            int index = wl.indexOf(';');
            int index2 = wl.indexOf('#');

            lwins.setText(wl.substring(index2 + 1, index));
            llose.setText(wl.substring(index + 1));
            username.setText(wl.substring(0, index2));
        }
    }

    @FXML
    private void play(Event e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("View.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void registration(Event e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.show();
        error.setText("* Внимание, это учебный проект и данные не шифруются.\nНастоятельно не рекомендуем вводить свои реальные данные");
    }

    @FXML
    private void register(Event e) throws IOException {
        if (!password.getText().equals(passwordcheck.getText())) error.setText("* Пароли отличаются друг от друга");
        else {
            network = new Network();
            network.send(Const.REGISTRATION);
            network.waitForInt();
            network.send(login.getText());
            network.waitForInt();
            network.send(password.getText());

            int status = network.waitForInt();
            if (status == Const.LOGIN_DENIED) error.setText("* Данное имя пользователя уже занято");
            else {
                String user = login.getText();

                root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root, 1366, 768);
                stage.setScene(scene);
                stage.show();

                username.setText(user);
            }
        }
    }

    @FXML
    private void backtologin(Event e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void fullscreen(Event e) throws IOException {
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setFullScreen(true);
        stage.show();
    }

    @FXML
    private void mapZoom() {
        mainGroup.setScaleX(2.0 + 0.02 * zoom.getValue());
        mainGroup.setScaleY(2.0 + 0.02 * zoom.getValue());
    }

    @FXML
    private void handUp() {
        hand.setTranslateY(0.0);
    }

    @FXML
    private void handDown() {
        hand.setTranslateY(110.0);
    }

    @FXML
    private void spawnunit(ActionEvent e) {
        Node node = (Node)e.getSource();
        String data = (String)node.getUserData();
        int posx = Integer.parseInt(data) - 1;

        int unt = curCard.getAction();

        String parse = "";
        for (int i = 0; i < unitlist.size(); i++) {
            if (Integer.parseInt(unitlist.get(i).split(";")[0]) == unt) {
                parse = unitlist.get(i);
            }
        }

        int posy = 3;
        int posyr = 4;
        if (posx == 1) {
            posy = 4;
            posyr = 7;
        }
        else if (posx == 2) {
            posy = 5;
            posyr = 7;
        }
        else if (posx == 3 || posx == 4) {
            posy = 5;
            posyr = 6;
        }
        else if (posx == 5) {
            posy = 4;
            posyr = 5;
        }


        Unit soldier = new Unit(parse, 1, posx + 1, posyr);
        army.get(posx).set(posy, soldier);

        units.getChildren().clear();
        for (int i = 0; i < army.size(); i++) {
            for (int j = 0; j < army.get(i).size(); j++) {
                if (army.get(i).get(j) != null) units.getChildren().add(army.get(i).get(j).getView());
            }
        }

        network = new Network();
        network.send(Const.SEND_PLAYER_ACTION);
        network.waitForInt();

        network.send("" + unt + ";" + posx);
        network.waitForInt();

        network = new Network();
        network.send(Const.GET_PLAYER_ACTION);
        parseActions(network.waitForString());

        spawnunit.setVisible(false);
    }

    private void parseActions(String parse) {
        if (parse.equals("-1")) return;
        while (parse.indexOf('#') >= 0) {
            String qu = parse.split("#")[0];
            System.out.println(qu);
            if (qu.equals("ready")) {
                rdy2 = true;
            }
            else {
                int posx = Integer.parseInt(qu.split(";")[1]);
                int unt = Integer.parseInt(qu.split(";")[0]);

                String uparse = "";
                for (int i = 0; i < unitlist.size(); i++) {
                    if (Integer.parseInt(unitlist.get(i).split(";")[0]) == unt) {
                        uparse = unitlist.get(i);
                    }
                }

                Unit soldier = new Unit(uparse, 0, posx + 1, 1);
                army.get(posx).set(0, soldier);

                units.getChildren().clear();
                for (int i = 0; i < army.size(); i++) {
                    for (int j = 0; j < army.get(i).size(); j++) {
                        if (army.get(i).get(j) != null) units.getChildren().add(army.get(i).get(j).getView());
                    }
                }
            }

            parse = parse.substring(parse.indexOf('#') + 1);
        }

        System.out.println(parse);
        if (parse.equals("ready")) {
            rdy2 = true;
        }
        else {
            int posx = Integer.parseInt(parse.split(";")[1]);
            int unt = Integer.parseInt(parse.split(";")[0]);

            String uparse = "";
            for (int i = 0; i < unitlist.size(); i++) {
                if (Integer.parseInt(unitlist.get(i).split(";")[0]) == unt) {
                    uparse = unitlist.get(i);
                }
            }

            Unit soldier = new Unit(uparse, 0, posx + 1, 1);
            army.get(posx).set(0, soldier);

            units.getChildren().clear();
            for (int i = 0; i < army.size(); i++) {
                for (int j = 0; j < army.get(i).size(); j++) {
                    if (army.get(i).get(j) != null) units.getChildren().add(army.get(i).get(j).getView());
                }
            }
        }
    }

    @FXML
    private void draw() {
        drawACard(1);
    }

    private boolean drawACard(int q) {
        for (; q > 0; q--) {
            if (_deck.getSize() <= 0) {
                if (_discard.getSize() <= 0) return false;

                _deck = _discard;
                _discard = new Deck();
            }

            Card crd = _deck.getCard();
            _list.add(crd);
            _discard.addCards(false, crd);
            hand.getItems().clear();

            for (int i = 0; i < _list.size(); i++) {
                hand.getItems().add(_list.get(i).getView());
            }
        }

        return true;
    }

    @FXML
    private void listClick() {
        if (!spawnunit.isVisible()) {
            playcard.setVisible(false);

            if (_list.size() > 0 && hand.getFocusModel().getFocusedIndex() >= 0) {
                playcard.setVisible(true);

                int cast = _list.get(hand.getFocusModel().getFocusedIndex()).getCast();
                int color = _list.get(hand.getFocusModel().getFocusedIndex()).getColor();
                int totalmana = intMana;

                if (color == 1) totalmana += intBlueMana;
                else if (color == 2) totalmana += intRedMana;

                if (totalmana >= cast) playcardbtn.setDisable(false);
                else playcardbtn.setDisable(true);
            }
        }
    }

    @FXML
    private void playCard() {
        curCard = _list.get(hand.getFocusModel().getFocusedIndex());

        _list.remove(hand.getFocusModel().getFocusedIndex());
        hand.getItems().remove(hand.getFocusModel().getFocusedIndex());

        playcard.setVisible(false);

        spawnunit.setVisible(true);
        if (army.get(0).get(3) == null) pos1.setDisable(false);
        else pos1.setDisable(true);
        if (army.get(1).get(4) == null) pos2.setDisable(false);
        else pos2.setDisable(true);
        if (army.get(2).get(5) == null) pos3.setDisable(false);
        else pos3.setDisable(true);
        if (army.get(3).get(5) == null) pos4.setDisable(false);
        else pos4.setDisable(true);
        if (army.get(4).get(5) == null) pos5.setDisable(false);
        else pos5.setDisable(true);
        if (army.get(5).get(4) == null) pos6.setDisable(false);
        else pos6.setDisable(true);
        if (army.get(6).get(3) == null) pos7.setDisable(false);
        else pos7.setDisable(true);

    }

    @FXML
    private void discard() {
        if (playcard.isVisible()) {
            int cast = _list.get(hand.getFocusModel().getFocusedIndex()).getColor();
            if (cast == 0) intMana++;
            else if (cast == 1) intRedMana++;
            else if (cast == 2) intBlueMana++;

            mana.setText("" + intMana);
            bluemana.setText("" + intBlueMana);
            redmana.setText("" + intRedMana);

            _list.remove(hand.getFocusModel().getFocusedIndex());
            hand.getItems().remove(hand.getFocusModel().getFocusedIndex());

            playcard.setVisible(false);
        }
    }
}