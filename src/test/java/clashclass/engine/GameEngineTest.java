package clashclass.engine;

import clashclass.commons.Vector2D;
import clashclass.elements.troops.BattleTroopFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameEngineTest {

    @Test
    void TestGameEngineThreadActsAsExpected() {
        final var gameEngine = new GameEngineImpl();

        gameEngine.start();

        gameEngine.addGameObject(new BattleTroopFactoryImpl().createBarbarian(Vector2D.zero()));

        gameEngine.stop();
    }
}
