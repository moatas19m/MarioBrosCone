package com.mariobros.game.Sprites.TileObjects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mariobros.game.MarioGame;
import com.mariobros.game.Screens.PlayScreen;
import com.mariobros.game.Sprites.Mario;

public abstract class InteractiveTileObject
{
    protected PlayScreen screen;
    protected MapObject object;

    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;

    protected Fixture fixture;

    public InteractiveTileObject(PlayScreen screen, MapObject object)
    {
        this.object = object;
        this.screen = screen;
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = ((RectangleMapObject) object).getRectangle();

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX()+bounds.getWidth()/2)/ MarioGame.PPM, (bounds.getY()+bounds.getHeight()/2)/MarioGame.PPM);

        body = world.createBody(bdef);
        shape.setAsBox(bounds.getWidth()/2/MarioGame.PPM, bounds.getHeight()/2/MarioGame.PPM);
        fdef.shape = shape;
        fixture=body.createFixture(fdef);
    }

    public  abstract void HeadButt();


    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    //basically a mathemathical function to communicate between the "level1.tmx" file we created
    //with tiled, and our InteractiveTileObject children (coins, bricks)
    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int)(body.getPosition().x * MarioGame.PPM / 16),  //multiplpication by
                (int)(body.getPosition().y * MarioGame.PPM / 16));             //16 because libGDX box2d scales down by 16
    }


}
