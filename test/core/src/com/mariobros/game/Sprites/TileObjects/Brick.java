package com.mariobros.game.Sprites.TileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mariobros.game.MarioGame;
import com.mariobros.game.Scenes.Hud;
import com.mariobros.game.Screens.PlayScreen;
import com.mariobros.game.Sprites.Mario;

public class Brick extends InteractiveTileObject
{
    public Brick(PlayScreen screen, MapObject object)
    {
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(MarioGame.BRICK_BIT);

    }

    @Override
    public void HeadButt() {
        Gdx.app.log("Brick hit", "");
        setCategoryFilter(MarioGame.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(200);
        MarioGame.manager.get("audio/sounds/breakblock.wav", Sound.class).play();

    }

}
