package code.tile.world;

import java.util.ArrayList;
import code.tile.Tile;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

import code.utilities.Vector2;

public class World {
    ArrayList<ArrayList<Tile>> _tiles;
    Group _view;

    public World() {
        _tiles = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            int end = 4;
            if (i > 0 && i < 6) {
                if (i == 1 || i == 5) end = 5;
                else if (i == 2 || i == 4) end = 6;
                else end = 7;
            }

            ArrayList<Tile> row = new ArrayList<>();
            for (int j = 0; j < end; j++) {
                row.add(new Tile(randInt(1, 9), randInt(0, 2)));
            }
            _tiles.add(row);
        }

        initView();
    }

    public void initView() {
        _view = new Group();

        ImageView buf = getTileView(3, 0);
        _view.getChildren().add(buf);

        buf = getTileView(2, 0);
        _view.getChildren().add(buf);

        buf = getTileView(4, 0);
        _view.getChildren().add(buf);

        buf = getTileView(1, 0);
        _view.getChildren().add(buf);

        buf = getTileView(3, 1);
        _view.getChildren().add(buf);

        buf = getTileView(5, 0);
        _view.getChildren().add(buf);

        buf = getTileView(0, 0);
        _view.getChildren().add(buf);

        buf = getTileView(2, 1);
        _view.getChildren().add(buf);

        buf = getTileView(4, 1);
        _view.getChildren().add(buf);

        buf = getTileView(6, 0);
        _view.getChildren().add(buf);


        buf = getTileView(1, 1);
        _view.getChildren().add(buf);

        buf = getTileView(3, 2);
        _view.getChildren().add(buf);

        buf = getTileView(5, 1);
        _view.getChildren().add(buf);

        buf = getTileView(0, 1);
        _view.getChildren().add(buf);

        buf = getTileView(2, 2);
        _view.getChildren().add(buf);

        buf = getTileView(4, 2);
        _view.getChildren().add(buf);

        buf = getTileView(6, 1);
        _view.getChildren().add(buf);


        buf = getTileView(1, 2);
        _view.getChildren().add(buf);

        buf = getTileView(3, 3);
        _view.getChildren().add(buf);

        buf = getTileView(5, 2);
        _view.getChildren().add(buf);

        buf = getTileView(0, 2);
        _view.getChildren().add(buf);

        buf = getTileView(2, 3);
        _view.getChildren().add(buf);

        buf = getTileView(4, 3);
        _view.getChildren().add(buf);

        buf = getTileView(6, 2);
        _view.getChildren().add(buf);


        buf = getTileView(1, 3);
        _view.getChildren().add(buf);

        buf = getTileView(3, 4);
        _view.getChildren().add(buf);

        buf = getTileView(5, 3);
        _view.getChildren().add(buf);

        buf = getTileView(0, 3);
        _view.getChildren().add(buf);

        buf = getTileView(2, 4);
        _view.getChildren().add(buf);

        buf = getTileView(4, 4);
        _view.getChildren().add(buf);

        buf = getTileView(6, 3);
        _view.getChildren().add(buf);


        buf = getTileView(1, 4);
        _view.getChildren().add(buf);

        buf = getTileView(3, 5);
        _view.getChildren().add(buf);

        buf = getTileView(5, 4);
        _view.getChildren().add(buf);

        buf = getTileView(2, 5);
        _view.getChildren().add(buf);

        buf = getTileView(4, 5);
        _view.getChildren().add(buf);


        buf = getTileView(3, 6);
        _view.getChildren().add(buf);
    }

    private ImageView getTileView(int i, int j) {
        ImageView buf = _tiles.get(i).get(j).getView();

        buf.setTranslateX(i * 30);
        if (i > 0 && i < 6) {
            if (i == 1 || i == 5) buf.setTranslateY(buf.getTranslateY() - 16);
            else if (i == 2 || i == 4) buf.setTranslateY(buf.getTranslateY() - 32);
            else if (i == 3) buf.setTranslateY(buf.getTranslateY() - 48);
        }
        if (_tiles.get(i).get(j).getHeight() > 0) buf.setTranslateY(buf.getTranslateY() - 4 * _tiles.get(i).get(j).getHeight());
        buf.setTranslateY(buf.getTranslateY() + 32 * j);

        return buf;
    }

    public Group getView() {
        return _view;
    }

    public boolean terraforming(int posX, int posY, boolean water, int temp, int height, int seed_chop) {
        Vector2 pos = Vector2.coordsToVector(posX, posY);
        boolean result = _tiles.get(pos.x).get(pos.y).terraforming(water, temp, height, seed_chop);
        return result;
    }

    public void getSize() {
        for (int i = 0; i < _tiles.size(); i++) System.out.print(_tiles.get(i).size());
        System.out.println();
    }

    private int randInt(int min, int max) {
        return (int)(min + Math.random() * max);
    }
}