package com.myname.game.gameScreen.inventory;

import com.badlogic.gdx.utils.Array;
import static com.myname.game.gameScreen.utils.Constants.*;

public class Inventory {

    private Array<Slot> itemList;

    public Inventory()
    {
        itemList = new Array<>(10);
    }


}
