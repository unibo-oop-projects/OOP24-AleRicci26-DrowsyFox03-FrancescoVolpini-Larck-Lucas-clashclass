package clashclass.ai.logic;

import clashclass.ecs.AbstractComponent;

public class DamageLogicComponent extends AbstractComponent {
    private final CalculateDamageLogic damageLogic;

    public DamageLogicComponent(final CalculateDamageLogic damageLogic) {
        this.damageLogic = damageLogic;
    }

    public CalculateDamageLogic getDamageLogic() {
        return damageLogic;
    }
}
