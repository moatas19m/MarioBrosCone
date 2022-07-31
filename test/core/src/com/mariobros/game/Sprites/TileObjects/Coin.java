package com.mariobros.game.Sprites.TileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mariobros.game.MarioGame;
import com.mariobros.game.Screens.PlayScreen;
import com.mariobros.game.Scenes.Hud;

import com.mariobros.game.Sprites.Mario;

public class Coin extends InteractiveTileObject
{
    private static TiledMapTileSet tileSet;
    //Tiled contains a unique ID for each tile(icon), and while Tiled starts counting from zeroth index, libGDX Tiledmaptileset counts
    //from 1, hence=>
    private final int BLANK_COIN = 28;
    public Coin(PlayScreen screen, MapObject object)
    {
        super(screen, object);
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(MarioGame.COIN_BIT);

    }
    @Override
    public void HeadButt() {
        Gdx.app.log("Coin hit", "");
        if(getCell().getTile().getId() == BLANK_COIN)
            MarioGame.manager.get("audio/sounds/bump.wav", Sound.class).play();
        else{
            MarioGame.manager.get("audio/sounds/coin.wav", Sound.class).play();
            getCell().setTile(tileSet.getTile(BLANK_COIN));
            Hud.addScore(100);
        }
    }
}
