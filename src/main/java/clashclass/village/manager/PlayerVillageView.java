package clashclass.village.manager;

import clashclass.shop.ShopMenuView;

public interface PlayerVillageView {
    void setController(PlayerVillageController controller);
    void update(PlayerVillageModel model);
    void clearScene();
    ShopMenuView getShopMenuView();
}
