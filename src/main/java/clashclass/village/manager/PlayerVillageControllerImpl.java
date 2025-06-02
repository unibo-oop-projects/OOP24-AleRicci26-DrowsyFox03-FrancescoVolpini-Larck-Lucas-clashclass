package clashclass.village.manager;

public class PlayerVillageControllerImpl implements PlayerVillageController {
    private final PlayerVillageModel model;
    private final PlayerVillageView view;

    public PlayerVillageControllerImpl(final PlayerVillageModel model, final PlayerVillageView view) {
        this.model = model;
        this.view = view;
        this.updateView();
    }

    private void updateView()
    {
        this.view.update(this.model);
    }
}
