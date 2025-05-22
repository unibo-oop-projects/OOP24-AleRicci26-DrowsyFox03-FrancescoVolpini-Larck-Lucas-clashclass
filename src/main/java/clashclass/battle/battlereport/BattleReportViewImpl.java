package clashclass.battle.battlereport;

import clashclass.resources.ResourceManager;

public class BattleReportViewImpl implements BattleReportView {
    private static final String SEPARATOR = "====================";

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(BattleReportModel model) {
        displayDestructionPercentage(model.getDestructionPercentage());
        displayStars(model.getStars());
        displayStolenResources(model.getStolenGold(), model.getStolenElixir());
        displayBattleResult(model.isVictory());
        displayTroopCount(model.getTroopCount());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayDestructionPercentage(double percentage) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("Battle Report");
        System.out.println(SEPARATOR);
        System.out.println("Destruction: " + formatPercentage(percentage));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayStars(int stars) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("Battle Stars");
        System.out.println(SEPARATOR);
        System.out.println("Stars Earned: " + stars + "/3");
        System.out.println(getStarDisplay(stars));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayStolenResources(ResourceManager resources) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("Resources Stolen");
        System.out.println(SEPARATOR);
        if (resources != null) {
            System.out.println("Resources: " + formatNumber(resources.getCurrentValue()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayBattleResult(boolean isVictory) {
        System.out.println("\nBattle Outcome: " + (isVictory ? "Victory!" : "Defeat"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayTroopCount(int troopCount) {
        System.out.println("Troops Used: " + troopCount);
    }

    /**
     * Formats a decimal number as a percentage string with one decimal place.
     *
     * @param value The decimal value to format
     * @return A formatted percentage string (e.g., "45.5%")
     */
    private String formatPercentage(double value) {
        return String.format("%.1f%%", value);
    }

    /**
     * Formats an integer with thousand separators.
     *
     * @param value The integer value to format
     * @return A formatted number string (e.g., "1,000,000")
     */
    private String formatNumber(int value) {
        return String.format("%,d", value);
    }

    /**
     * Creates a visual representation of earned stars using star symbols.
     *
     * @param stars The number of stars earned (0-3)
     * @return A string with filled (★) and empty (☆) stars
     */
    private String getStarDisplay(int stars) {
        return "★".repeat(stars) + "☆".repeat(3 - stars);
    }

    /**
     * Displays the stolen resources, separating gold and elixir values.
     *
     * @param gold The ResourceManager containing stolen gold
     * @param elixir The ResourceManager containing stolen elixir
     */
    private void displayStolenResources(ResourceManager gold, ResourceManager elixir) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("Resources Stolen");
        System.out.println(SEPARATOR);
        System.out.println("Gold: " + formatNumber(gold.getCurrentValue()));
        System.out.println("Elixir: " + formatNumber(elixir.getCurrentValue()));
    }
}