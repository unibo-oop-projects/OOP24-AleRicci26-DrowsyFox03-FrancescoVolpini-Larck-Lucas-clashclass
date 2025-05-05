package clashclass.battle.battlereport;

public class BattleReportControllerImpl implements BattleReportController {

    private final BattleReportModel model;
    private final BattleReportView view;

    public BattleReportControllerImpl(final BattleReportModel model, final BattleReportView view) {
        this.model = model;
        this.view = view;

        // Initialize the view with the current model data
        updateView();
    }

    @Override
    public void increaseDestructionPercentage() {
        model.increaseDestructionPercentage();
        updateView();
    }

    @Override
    public void increaseStolenResources(final ResourceManager resourceManager) {
        model.addStolenResources(resourceManager);
        updateView();
    }

    private void updateView() {
        view.update(model);
    }

    public void notifyTownHallDestroyed() {
        model.setTownHallDestroyed(true);
        updateView();
    }

    public double getDestructionPercentage() {
        return model.getDestructionPercentage();
    }

    public int getStars() {
        return model.getStars();
    }

    public boolean isVictory() {
        return model.isVictory();
    }

    @Override
    public void setTroopCount(final int count) {
        model.setTroopCount(count);
        updateView();
    }
}
