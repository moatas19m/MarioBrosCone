package com.mariobros.game.Sprites.Enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mariobros.game.Screens.PlayScreen;
import com.mariobros.game.Sprites.Mario;

public abstract class Enemy extends Sprite {
    public abstract void hitOnHead();
    public abstract void update(float dt);
    protected abstract void defineEnemy();
    //public void hitByEnemy(Enemy userData) {
    //}

    //public void reverseVelocity(boolean b, boolean b1) {
    //}

    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    public Vector2 velocity;


    public Enemy(PlayScreen screen, float x, float y)
    {
        this.world = screen.getWorld();
        this.screen=screen;
        setPosition(x, y);
        defineEnemy();
        velocity = new Vector2(1,0);
    }







    public void reverseVelocity(boolean x, boolean y) //talk about this bit of skulduggery
    {
        if(x)
            velocity.x=-velocity.x;
        if(y)
            velocity.y=-velocity.y;
    }

}
