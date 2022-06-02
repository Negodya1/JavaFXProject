package code.unit;

import javafx.scene.image.ImageView;
import javafx.scene.Group;

import code.utilities.Vector2;

import java.util.Collections;
import java.util.Comparator;

public class Unit {
    UnitType _type;
    UnitFraction _fraction;
    int _hp;
    int _damage;
    int _range;
    int _speed;
    int _team;
    Vector2 _pos;

    ImageView _view;

    public Unit(UnitType id, UnitFraction fraction, int team, int posX, int posY) {
        _type = id;
        _team = team;
        _pos = Vector2.coordsToVector(posX, posY);

        if (id == UnitType.HERO) {
            _fraction = UnitFraction.NEUTRAL;
            _hp = 10;
            _damage = 3;
            _range = 1;
            _speed = 1;
            initView();
            return;
        }

        if (id == UnitType.KNIGHT) {
            _hp = 5;
            _damage = 1;
            _range = 1;
            _speed = 1;
        }
        else if (id == UnitType.ARCHER) {
            _hp = 3;
            _damage = 1;
            _range = 2;
            _speed = 1;
        }
        else if (id == UnitType.ASSASIN) {
            _hp = 3;
            _damage = 2;
            _range = 1;
            _speed = 2;
        }

        _fraction = fraction;
        if (fraction != UnitFraction.NEUTRAL) {
            if (fraction == UnitFraction.CHAOS) {
                _damage += 1;
            } else if (fraction == UnitFraction.ORDER) {
                _hp += 2;
            }
        }

        initView();
    }
    public Unit(int id, int fraction, int team, int posX, int posY) {
        UnitType enID = UnitType.KNIGHT;;
        if (id == 2) enID = UnitType.ARCHER;
        else if (id == 3) enID = UnitType.ASSASIN;

        UnitFraction enFr = UnitFraction.NEUTRAL;
        if (fraction == 1) enFr = UnitFraction.ORDER;
        else if (fraction == 2) enFr = UnitFraction.CHAOS;

        _type = enID;
        _team = team;
        _pos = Vector2.coordsToVector(posX, posY);

        if (enID == UnitType.KNIGHT) {
            _hp = 5;
            _damage = 1;
            _range = 1;
            _speed = 1;
        }
        else if (enID == UnitType.ARCHER) {
            _hp = 3;
            _damage = 1;
            _range = 2;
            _speed = 1;
        }
        else if (enID == UnitType.ASSASIN) {
            _hp = 3;
            _damage = 2;
            _range = 1;
            _speed = 2;
        }

        _fraction = enFr;
        if (enFr != UnitFraction.NEUTRAL) {
            if (enFr == UnitFraction.CHAOS) {
                _damage += 1;
            } else if (enFr == UnitFraction.ORDER) {
                _hp += 2;
            }
        }

        initView();
    }
    public Unit(String parse, int team, int posX, int posY) {
        int id = Integer.parseInt(parse.split(";")[1]);
        int fraction = Integer.parseInt(parse.split(";")[2]);

        UnitType enID = UnitType.KNIGHT;;
        if (id == 2) enID = UnitType.ARCHER;
        else if (id == 3) enID = UnitType.ASSASIN;

        UnitFraction enFr = UnitFraction.NEUTRAL;
        if (fraction == 1) enFr = UnitFraction.ORDER;
        else if (fraction == 2) enFr = UnitFraction.CHAOS;

        _type = enID;
        _team = team;
        _pos = Vector2.coordsToVector(posX, posY);

        if (enID == UnitType.KNIGHT) {
            _hp = 5;
            _damage = 1;
            _range = 1;
            _speed = 1;
        }
        else if (enID == UnitType.ARCHER) {
            _hp = 3;
            _damage = 1;
            _range = 2;
            _speed = 1;
        }
        else if (enID == UnitType.ASSASIN) {
            _hp = 3;
            _damage = 2;
            _range = 1;
            _speed = 2;
        }

        _fraction = enFr;
        if (enFr != UnitFraction.NEUTRAL) {
            if (enFr == UnitFraction.CHAOS) {
                _damage += 1;
            } else if (enFr == UnitFraction.ORDER) {
                _hp += 2;
            }
        }

        initView();
    }

    private void initView() {
        if (_type == UnitType.HERO) {
            if (_team == 0) _view = new ImageView("Hero_red.png");
            else _view = new ImageView("Hero_blue.png");
            return;
        }

        String url = "Neutral_";

        if (_fraction == UnitFraction.NEUTRAL) url = "Neutral_";
        else if (_fraction == UnitFraction.CHAOS) url = "Chaos_";
        else if (_fraction == UnitFraction.ORDER) url = "Order_";

        if (_type == UnitType.KNIGHT) url += "knight_unit.png";
        else if (_type == UnitType.ARCHER) url += "archer_unit.png";
        else if (_type == UnitType.ASSASIN) url += "assasin_unit.png";

        _view = new ImageView(url);
    }

    public Group getView() {
        ImageView shadow;
        if (_team == 0) shadow = new ImageView("Red_shadow.png");
        else shadow = new ImageView("Blue_shadow.png");

        Group res = new Group(shadow, _view);

        if (_pos.x > 0 && _pos.x <= 6) {
            if (_pos.x == 1) res.setTranslateY(res.getTranslateY() - 16);
            else if (_pos.x == 2) res.setTranslateY(res.getTranslateY() - 32);
            else if (_pos.x == 3) res.setTranslateY(res.getTranslateY() - 48);
            else if (_pos.x == 5) res.setTranslateY(res.getTranslateY() + 48);
            else if (_pos.x == 6) res.setTranslateY(res.getTranslateY() + 96);
        }
        res.setTranslateY(res.getTranslateY() + 32 * (_pos.y - 1) + 20);

        res.setTranslateX(_pos.x * 30);

        return res;
    }

    public boolean damage(int dmg) {
        _hp -= dmg;
        if (_hp <= 0) return true;
        return false;
    }

    public int getDamage() {
        return _damage;
    }
    public int getHP() {
        return _hp;
    }
    public int getRange() {
        return _range;
    }
    public int getSpeed() {
        return _speed;
    }

    public int getPosY() {
        return _pos.y;
    }
    public int getPosX() {
        return _pos.x;
    }
}