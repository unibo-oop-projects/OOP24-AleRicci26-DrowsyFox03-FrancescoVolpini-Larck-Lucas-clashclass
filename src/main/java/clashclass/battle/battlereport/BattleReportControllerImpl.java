package clashclass.battle.battlereport;

import clashclass.resources.ResourceManager;

public class BattleReportControllerImpl implements BattleReportController {
    private final BattleReportModel model;
    private final BattleReportView view;

    public BattleReportControllerImpl(int totalBuildings) {
        this.model = new BattleReportModelImpl(totalBuildings);
        this.view = new BattleReportViewImpl();
        updateView();
    }

    @Override
    public void updateDestructionPercentage(double percentage) {
        model.setDestructionPercentage(percentage);
        updateView();
    }

    @Override
    public void increaseDestructionPercentage() {
        model.increaseDestructionPercentage();
        updateView();
    }

    @Override
    public void addStolenGold(ResourceManager gold) {
        if (gold != null) {
            model.addStolenGold(gold);
            updateView();
        }
    }

    @Override
    public void addStolenElixir(ResourceManager elixir) {
        if (elixir != null) {
            model.addStolenElixir(elixir);
            updateView();
        }
    }

    @Override
    public void setTownHallDestroyed(boolean destroyed) {
        model.setTownHallDestroyed(destroyed);
        updateView();
    }

    @Override
    public void updateTroopCount(int count) {
        model.setTroopCount(count);
        updateView();
    }

    @Override
    public BattleReportModel getModel() {
        return model;
    }

    private void updateView() {
        view.update(model);
    }
}