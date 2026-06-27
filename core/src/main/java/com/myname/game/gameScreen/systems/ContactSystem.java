package com.myname.game.gameScreen.systems;

import static com.myname.game.gameScreen.utils.Constants.*;

import com.badlogic.gdx.physics.box2d.*;
import com.myname.game.gameScreen.event.EventManager;
import com.myname.game.gameScreen.event.ItemEvent;
import com.myname.game.gameScreen.inventory.Item;
import common.Utils;

public class ContactSystem implements ContactListener {

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
            System.out.println("Contact between: " + dataA.toString() + " and " + dataB.toString());
            if(Utils.isTheyTheLookingFixtures(PLAYER_SENSOR_FIXTURE,CARROT_FIXTURE,dataA,dataB))
            {
                EventManager.fireItemEvent(new ItemEvent(new Item(CARROT_FIXTURE)));
            }
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
