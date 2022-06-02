package code.utilities;

public class Vector2 {
    public int x;
    public int y;

    public Vector2(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public static Vector2 coordsToVector(int _x, int _y) {
        if (_x <= 0) _x = 1;
        else if (_x > 7) _x = 7;
        if (_y <= 0) _y = 1;
        else if (_y > 7) _y = 7;

        Vector2 res = new Vector2(_x - 1, _y - 1);
        if (_x > 4) res.y -= _x - 4;
        else if (_x < 4 && _y > 4) res.y -= 4 - _x;
        return res;
    }
}
