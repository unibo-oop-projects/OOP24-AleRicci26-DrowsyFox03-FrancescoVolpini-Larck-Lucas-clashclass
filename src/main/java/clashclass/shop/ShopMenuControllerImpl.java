package clashclass.shop;

public class ShopMenuControllerImpl implements ShopMenuController {
    private final ShopMenuModel model;
    private final ShopMenuView view;

    public ShopMenuControllerImpl(final ShopMenuModel model, final ShopMenuView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public ShopManager getShopManager() {
        return this.model.getShopManager();
    }

    @Override
    public boolean tryToBuyItem(final ShopItem item) {
        if (this.model.getShopManager().canAfford(item)) {
            this.model.getShopManager().buyItem(item);
            return true;
        }
        return false;
    }

    @Override
    public void show() {
        this.view.show();
    }

    @Override
    public void hide() {
        this.view.hide();
    }

    @Override
    public void clearScene() {
        this.view.clearScene();
    }
}
