package com.mariobros.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mariobros.game.MarioGame;
import com.mariobros.game.Sprites.Enemies.Enemy;
import com.mariobros.game.Sprites.TileObjects.InteractiveTileObject;
import com.badlogic.gdx.physics.box2d.ContactListener;

public class WorldContactListener implements ContactListener{
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();


        int cdef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;


        if(fixA.getUserData()=="head" || fixB.getUserData()=="head")
        {
            Fixture head = fixA.getUserData()=="head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom((object.getUserData().getClass())))
            {
                ((InteractiveTileObject)object.getUserData()).HeadButt();
            }

        }

        switch (cdef){
            case MarioGame.ENEMY_HEAD_BIT | MarioGame.MARIO_BIT:
                if(fixA.getFilterData().categoryBits==MarioGame.ENEMY_HEAD_BIT)
                    ((Enemy) fixA.getUserData()).hitOnHead();
                else
                    ((Enemy) fixB.getUserData()).hitOnHead();
                break;
            case MarioGame.ENEMY_BIT|MarioGame.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits==MarioGame.ENEMY_BIT)
                    ((Enemy) fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Enemy) fixB.getUserData()).reverseVelocity(true, false);
                break;
            case MarioGame.MARIO_BIT|MarioGame.ENEMY_BIT:
                Gdx.app.log("MARIO", "DIED");
                break;
        }


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
