package com.mariobros.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mariobros.game.MarioGame;
import com.mariobros.game.Screens.PlayScreen;
import com.mariobros.game.Sprites.Enemies.Goomba;
import com.mariobros.game.Sprites.TileObjects.Brick;
import com.mariobros.game.Sprites.TileObjects.Coin;



public class B2WorldCreator {

    private Array<Goomba> goombas;
    public B2WorldCreator(PlayScreen screen)
    {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;



        //creating ground bodies/fixtures
        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/ MarioGame.PPM, (rect.getY()+rect.getHeight()/2)/MarioGame.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2/MarioGame.PPM, rect.getHeight()/2/MarioGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);

        }
        //create pipe bodies/fixtures
        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/MarioGame.PPM, (rect.getY()+rect.getHeight()/2)/MarioGame.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2/MarioGame.PPM, rect.getHeight()/2/MarioGame.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits=MarioGame.OBJECT_BIT;
            body.createFixture(fdef);

        }


        //create brick bodies/fixture
        for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
        {

            new Brick(screen, object);

        }
        //create coin bodies/fixtures
        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
        {
            new Coin(screen, object);

        }
        //create all goombas
        goombas = new Array<Goomba>();
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            goombas.add(new Goomba(screen, rect.getX() / MarioGame.PPM, rect.getY() / MarioGame.PPM));
        }


    }
    public Array<Goomba> getGoombas() {
        return goombas;
    }
}
