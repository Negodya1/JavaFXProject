package code.card;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Card {
    private String _name;
    private String _desc;
    private int _id;
    private int _cost;
    private int _color;

    private ImageView _imageView;
    private Group _view;

    public Card(int id, String name, String desc, String img, int cost, int... color) {
        _id = id;
        _name = name;
        _desc = desc;
        _cost = cost;
        if (color.length > 0) _color = color[0];

        _imageView = new ImageView(img);
        _view = initView();
    }

    //JavaFX view
    public Group initView() {
        Label name = new Label(_name);
        name.setFont(new Font(10.0));
        name.setTranslateX(5);
        name.setTranslateY(3);
        name.setTextFill(Color.ANTIQUEWHITE);

        Label desc = new Label(_desc);
        desc.setFont(new Font(9.0));
        desc.setTranslateX(5);
        desc.setTranslateY(66);
        desc.setTextFill(Color.ANTIQUEWHITE);

        Label cast = new Label("" + _cost);
        if (_cost < 10) {
            cast.setFont(new Font(10.0));
            cast.setTranslateX(71);
        }
        else {
            cast.setFont(new Font(9.0));
            cast.setTranslateX(69);
        }
        cast.setTranslateY(53);
        cast.setTextFill(Color.YELLOW);

        _view = new Group(_imageView, name, desc, cast);
        return _view;
    }
    public Group getView() {
        return _view;
    }

    //Getters
    public int getCast() {
        return _cost;
    }
    public int getColor() {
        return _color;
    }
    public int getId() {
        return _id;
    }

    //Setters & adders
    public void setCost(int cost) {
        _cost = cost;
        initView();
    }
    public void addCost(int cost) {
        _cost += cost;
        initView();
    }
}
