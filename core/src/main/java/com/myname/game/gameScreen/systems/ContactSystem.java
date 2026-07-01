package com.myname.game.gameScreen.systems;

import static com.myname.game.gameScreen.utils.Constants.*;

import com.badlogic.gdx.physics.box2d.*;
import com.myname.game.gameScreen.entities.StaticEntity;
import com.myname.game.gameScreen.entities.player.Player;
import com.myname.game.gameScreen.event.EventManager;
import com.myname.game.gameScreen.event.ItemPickUpEvent.ItemPickUpEvent;
import com.myname.game.gameScreen.utils.Utils;

public class ContactSystem implements ContactListener {

    private boolean nearItem = false;
    private int nearItemFixtureId = -1;

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
                nearItemFixtureId = CARROT_FIXTURE;
            }
            else if(Utils.isTheyTheLookingFixtures(PLAYER_SENSOR_FIXTURE, BEETROOT_FIXTURE,dataA,dataB))
            {
                nearItem = true;
                nearItemFixtureId = BEETROOT_FIXTURE;
            }
            else if(Utils.isTheyTheLookingFixtures(PLAYER_SENSOR_FIXTURE,PEPPER_FIXTURE,dataA,dataB))
            {
                nearItem = true;
                nearItemFixtureId = PEPPER_FIXTURE;
            }
            else if(Utils.isTheyTheLookingFixtures(PLAYER_SENSOR_FIXTURE,POTATO_FIXTURE,dataA,dataB))
            {
                nearItem = true;
                nearItemFixtureId = POTATO_FIXTURE;
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

        nearItem = false;
        nearItemFixtureId = -1;

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
