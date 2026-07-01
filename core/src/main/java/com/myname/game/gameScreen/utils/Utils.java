package com.myname.game.gameScreen.utils;

import com.badlogic.gdx.Gdx;
import com.myname.game.gameScreen.entities.StaticEntity;
import com.myname.game.gameScreen.entities.player.Player;

public class Utils {

    public static boolean isTheyTheLookingFixtures(int dataA, int dataB,Object dataAA, Object dataBB) {
        if (dataAA == null || dataBB == null) {
            throw new IllegalStateException("Object data cannot find on contact");
        }

        if(dataAA instanceof Player)
        {
            StaticEntity entity = (StaticEntity)dataBB;
            if(entity.getUserNumberData() == dataB || entity.getUserNumberData() == dataA)
            {
                return true;
            }
        }
        if(dataBB instanceof Player)
        {
            StaticEntity entity = (StaticEntity)dataAA;
            if(entity.getUserNumberData() == dataA || entity.getUserNumberData() == dataB)
            {
                return true;
            }
        }

        return false;
    }

}
