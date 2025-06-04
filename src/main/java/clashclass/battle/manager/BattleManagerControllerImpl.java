package clashclass.battle.manager;

import clashclass.ai.behaviourtree.BehaviourTree;
import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.elements.troops.TROOP_TYPE;
import clashclass.gamestate.GameStateManager;
import clashclass.village.Village;

import java.util.Set;

public class BattleManagerControllerImpl implements BattleManagerController {
    private final BattleManagerModel model;
    private final BattleManagerView view;

    public BattleManagerControllerImpl(final BattleManagerModel model, final BattleManagerView view) {
        this.model = model;
        this.view = view;
        this.model.setController(this);
        this.view.setController(this);
    }

    @Override
    public void setGameStateManager(GameStateManager gameStateManager) {
        this.model.setGameStateManager(gameStateManager);

        final var battleVillage = this.model.getBattleVillage();
        final var gameEngine = this.model.getGameStateManager().getGameEngine();

        battleVillage.getGroundObjects().forEach(x -> {
            gameEngine.addGameObject(x);
            x.getComponentOfType(BehaviourTree.class).ifPresent(BehaviourTree::start);
        });
        battleVillage.getGameObjects().forEach(x -> {
            gameEngine.addGameObject(x);
            x.getComponentOfType(BehaviourTree.class).ifPresent(BehaviourTree::start);
        });

        this.view.setArmyCampTroops(this.model);
    }

    @Override
    public void clearScene() {
        final var battleVillage = this.model.getBattleVillage();
        battleVillage.getGroundObjects().forEach(GameObject::destroy);
        battleVillage.getGameObjects().forEach(GameObject::destroy);
        this.view.clearScene();
    }

    @Override
    public void endBattle() {
        this.model.getGameStateManager().setStatePlayerVillage();
    }

    @Override
    public void setCurrentSelectedTroop(final TROOP_TYPE troop) {
        this.model.setCurrentSelectedTroop(troop);
        this.view.updateArmyCampTroopsCount(this.model);
    }

    @Override
    public void createTroop(final Vector2D position) {
        this.model.createTroop(position);
        this.view.updateArmyCampTroopsCount(this.model);
    }

    @Override
    public Set<GameObject> getActiveTroops() {
        return this.model.getActiveTroops();
    }

    @Override
    public Village getBattleVillage() {
        return this.model.getBattleVillage();
    }

    @Override
    public void updateVillageState(final GameObject destroyedBuilding) {
        this.model.updateVillageState(destroyedBuilding);
    }

    @Override
    public void updateTroopsState(final GameObject destroyedTroop) {
        this.model.updateTroopsState(destroyedTroop);
    }
}
