package com.myname.game.gameScreen.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.myname.game.gameScreen.entities.GameEntity;

import java.util.Comparator;

public class RenderSystem {

    private Array<GameEntity> gameEntities;

    private Comparator<GameEntity> comparator = new Comparator<GameEntity>() {
        @Override
        public int compare(GameEntity o1, GameEntity o2) {
            return Float.compare(o2.getSortY(),o1.getSortY());
        }
    };

    public void draw(SpriteBatch batch)
    {
        gameEntities.sort(comparator);
        for(GameEntity gameEntity : gameEntities)
        {
            gameEntity.draw(batch);
        }
    }

    public RenderSystem()
    {
        gameEntities = new Array<>();
    }

    public void addGameEntity(GameEntity gameEntity)
    {
        gameEntities.add(gameEntity);
    }

}
