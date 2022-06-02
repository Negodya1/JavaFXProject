package code.tile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile {
    ImageView _imageView;
    TileType _type;
    int _height;

    public Tile() {
        _type = TileType.GRASS;
        _height = 0;
        initView();
    }

    public Tile(int type, int height) {
        _type = toType(type);
        _height = height;
        initView();
    }

    private TileType toType(int type) {
        switch (type) {
            case 1:
                return TileType.GRASS;
            case 2:
                return TileType.DARK_FOREST;
            case 3:
                return TileType.DESERT;
            case 4:
                return TileType.FOREST;
            case 5:
                return TileType.ICE;
            case 6:
                return TileType.LAVA;
            case 7:
                return TileType.SWAMP;
            case 8:
                return TileType.TAIGA;
            case 9:
                return TileType.TUNDRA;
            default:
                return TileType.GRASS;
        }
    }
    public TileType getType() {
        return _type;
    }
    public int getHeight() {
        return _height;
    }

    public void terraforming(TileType type) {
        _type = type;
        initView();
    }
    public void setHeight(int height) {
        _height = height;
    }
    public boolean terraforming(boolean water, int temp, int height, int seed_chop) {
        boolean access = false;

        if (seed_chop != 0) {
            if (seed_chop < 0) {
                if (chop()) access = true;
            }
            else {
                if (seed()) access = true;
            }
        }

        if (height != 0) {
            if (_type == TileType.DARK_FOREST || _type == TileType.FOREST || _type == TileType.TAIGA) {
                if (_type == TileType.DARK_FOREST) _type = TileType.DESERT;
                else if (_type == TileType.TAIGA) _type = TileType.TUNDRA;
                else _type = TileType.GRASS;
            }
            else _height += height;
            access = true;
        }

        if (water) {
            if (_type == TileType.DARK_FOREST) {
                _type = TileType.FOREST;
                access = true;
            }
            else if (_type == TileType.GRASS) {
                _type = TileType.SWAMP;
                access = true;
            }
            else if (_type == TileType.TUNDRA) {
                _type = TileType.ICE;
                access = true;
            }
            else if (_type == TileType.LAVA) {
                _type = TileType.DESERT;
                access = true;
            }
            else if (_type == TileType.DESERT) {
                _type = TileType.GRASS;
                access = true;
            }
        }

        while (temp != 0) {
            if (temp > 0) {
                if (_type == TileType.DESERT) {
                    _type = TileType.LAVA;
                    temp = 0;
                    access = true;
                }
                else if (_type == TileType.GRASS) {
                    _type = TileType.DESERT;
                    temp--;
                    access = true;
                }
                else if (_type == TileType.TUNDRA) {
                    _type = TileType.GRASS;
                    temp--;
                    access = true;
                }
                else if (_type == TileType.SWAMP) {
                    _type = TileType.GRASS;
                    temp--;
                    access = true;
                }
                else if (_type == TileType.ICE) {
                    _type = TileType.SWAMP;
                    temp--;
                    access = true;
                }
                else if (_type == TileType.TAIGA) {
                    _type = TileType.FOREST;
                    temp--;
                    access = true;
                }
                else if (_type == TileType.FOREST) {
                    _type = TileType.DARK_FOREST;
                    temp = 0;
                    access = true;
                }
            }
            else {
                if (_type == TileType.GRASS) {
                    _type = TileType.TUNDRA;
                    temp = 0;
                    access = true;
                }
                else if (_type == TileType.DESERT) {
                    _type = TileType.GRASS;
                    temp--;
                    access = true;
                }
                else if (_type == TileType.LAVA) {
                    _type = TileType.DESERT;
                    temp--;
                    access = true;
                }
                else if (_type == TileType.DARK_FOREST) {
                    _type = TileType.FOREST;
                    temp--;
                    access = true;
                }
                else if (_type == TileType.FOREST) {
                    _type = TileType.TAIGA;
                    temp = 0;
                    access = true;
                }
                else if (_type == TileType.SWAMP) {
                    _type = TileType.ICE;
                    temp = 0;
                    access = true;
                }
            }
        }

        if (access) initView();
        return access;
    }

    void initView() {
        if (_type == TileType.DARK_FOREST) _imageView = new ImageView("/Hex_dark.png");
        else if (_type == TileType.ICE) _imageView = new ImageView("/Hex_ice.png");
        else if (_type == TileType.SWAMP) _imageView = new ImageView("/Hex_swamp.png");
        else if (_type == TileType.TAIGA) _imageView = new ImageView("/Hex_taiga.png");
        else if (_type == TileType.FOREST) _imageView = new ImageView("/Hex_forest.png");
        else if (_type == TileType.GRASS) _imageView = new ImageView("/Hex_grass.png");
        else if (_type == TileType.LAVA) _imageView = new ImageView("/Hex_lava.png");
        else if (_type == TileType.TUNDRA) _imageView = new ImageView("/Hex_snow.png");
        else if (_type == TileType.DESERT) _imageView = new ImageView("/Hex_sand.png");
    }

    boolean chop() {
        if (_type == TileType.DARK_FOREST || _type == TileType.FOREST || _type == TileType.TAIGA) {
            if (_type == TileType.DARK_FOREST) _type = TileType.DESERT;
            else if (_type == TileType.TAIGA) _type = TileType.TUNDRA;
            else _type = TileType.GRASS;
            return true;
        }

        return false;
    }

    boolean seed() {
        if (_type == TileType.GRASS) {
            _type = TileType.FOREST;
            return true;
        }

        if (_type == TileType.DESERT) {
            _type = TileType.DARK_FOREST;
            return true;
        }

        return false;
    }

    public ImageView getView() {
        return _imageView;
    }
}