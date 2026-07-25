package com.myname.game.gameScreen.systems;

import static com.myname.game.gameScreen.utils.Constants.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.myname.game.gameScreen.entities.StaticEntity;
import com.myname.game.gameScreen.entities.player.Player;
import com.myname.game.gameScreen.event.EventManager;
import com.myname.game.gameScreen.event.ItemPickUpEvent.ItemPickUpEvent;
import com.myname.game.gameScreen.utils.Utils;

public class ContactSystem implements ContactListener {

    private boolean nearItem = false;
    private int nearItemFixtureId = -1;
    private int nearItemCount = 0;

    public ContactSystem(World world)
    {
        world.setContactListener(this);
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Object dataA = fixtureA.getUserData();
        Object dataB = fixtureB.getUserData();

        if(dataA != null && dataB != null)
        {
            if(Utils.isTheyTheLookingFixtures(PLAYER_SENSOR_FIXTURE,CARROT_FIXTURE,dataA,dataB))
            {
                nearItem = true;
                nearItemCount++;
                nearItemFixtureId = CARROT_FIXTURE;
            }
            else if(Utils.isTheyTheLookingFixtures(PLAYER_SENSOR_FIXTURE, BEETROOT_FIXTURE,dataA,dataB))
            {
                nearItem = true;
                nearItemCount++;
                nearItemFixtureId = BEETROOT_FIXTURE;
            }
            else if(Utils.isTheyTheLookingFixtures(PLAYER_SENSOR_FIXTURE,PEPPER_FIXTURE,dataA,dataB))
            {
                nearItem = true;
                nearItemCount++;
                nearItemFixtureId = PEPPER_FIXTURE;
            }
            else if(Utils.isTheyTheLookingFixtures(PLAYER_SENSOR_FIXTURE,POTATO_FIXTURE,dataA,dataB))
            {
                nearItem = true;
                nearItemCount++;
                nearItemFixtureId = POTATO_FIXTURE;
            }
            else if(Utils.isTheyTheLookingFixtures(PLAYER_SENSOR_FIXTURE,SOUPMAN_FIXTURE,dataA,dataB))
            {
                nearItem = true;
                nearItemCount++;
                nearItemFixtureId = SOUPMAN_FIXTURE;
            }
            else if(Utils.isTheyTheLookingFixtures(PLAYER_SENSOR_FIXTURE,SAMURAI_FIXTURE,dataA,dataB))
            {
                nearItem = true;
                nearItemCount++;
                nearItemFixtureId = SAMURAI_FIXTURE;
            }

            if(dataA instanceof Player)
            {
                ((Player) dataA).setTarget((StaticEntity) dataB);
            }

            if(dataB instanceof Player)
            {
                ((Player) dataB).setTarget((StaticEntity) dataA);
            }

        }
    }

    @Override
    public void endContact(Contact contact) {

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Object dataA = fixtureA.getUserData();
        Object dataB = fixtureB.getUserData();

        if(dataA != null && dataB != null)
        {
            if(Utils.isTheyTheLookingFixtures(PLAYER_SENSOR_FIXTURE,CARROT_FIXTURE,dataA,dataB))
            {
                nearItemCount--;
            }
            else if(Utils.isTheyTheLookingFixtures(PLAYER_SENSOR_FIXTURE, BEETROOT_FIXTURE,dataA,dataB))
            {
                nearItemCount--;
            }
            else if(Utils.isTheyTheLookingFixtures(PLAYER_SENSOR_FIXTURE,PEPPER_FIXTURE,dataA,dataB))
            {
                nearItemCount--;
            }
            else if(Utils.isTheyTheLookingFixtures(PLAYER_SENSOR_FIXTURE,POTATO_FIXTURE,dataA,dataB))
            {
                nearItemCount--;
            }
            else if(Utils.isTheyTheLookingFixtures(PLAYER_SENSOR_FIXTURE,SOUPMAN_FIXTURE,dataA,dataB))
            {
                nearItemCount--;
            }
            else if(Utils.isTheyTheLookingFixtures(PLAYER_SENSOR_FIXTURE,SAMURAI_FIXTURE,dataA,dataB))
            {
                nearItemCount--;
            }
            if (nearItemCount <= 0) {
                nearItemCount = 0;
                nearItemFixtureId = -1;
                nearItem = false;
            }
        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public int getNearItem()
    {
        if(nearItem)
        {
            return nearItemFixtureId;
        }
        return -1;
    }
}
