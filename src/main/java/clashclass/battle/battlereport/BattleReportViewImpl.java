package clashclass.battle.battlereport;

import clashclass.resources.ResourceManager;

public class BattleReportViewImpl implements BattleReportView {
    private static final String SEPARATOR = "====================";

    @Override
    public void update(BattleReportModel model) {
        displayDestructionPercentage(model.getDestructionPercentage());
        displayStars(model.getStars());
        displayStolenResources(model.getStolenGold(), model.getStolenElixir());
        displayBattleResult(model.isVictory());
        displayTroopCount(model.getTroopCount());
    }

    @Override
    public void displayDestructionPercentage(double percentage) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("Battle Report");
        System.out.println(SEPARATOR);
        System.out.println("Destruction: " + formatPercentage(percentage));
    }

    @Override
    public void displayStars(int stars) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("Battle Stars");
        System.out.println(SEPARATOR);
        System.out.println("Stars Earned: " + stars + "/3");
        System.out.println(getStarDisplay(stars));
    }

    @Override
    public void displayStolenResources(ResourceManager resources) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("Resources Stolen");
        System.out.println(SEPARATOR);
        // Since the BattleReportModel separates gold and elixir, this method might need adjustment
        // Currently using default implementation for interface compatibility
        if (resources != null) {
            System.out.println("Resources: " + formatNumber(resources.getCurrentValue()));
        }
    }

    @Override
    public void displayBattleResult(boolean isVictory) {
        System.out.println("\nBattle Outcome: " + (isVictory ? "Victory!" : "Defeat"));
    }

    @Override
    public void displayTroopCount(int troopCount) {
        System.out.println("Troops Used: " + troopCount);
    }

    private String formatPercentage(double value) {
        return String.format("%.1f%%", value);
    }

    private String formatNumber(int value) {
        return String.format("%,d", value);
    }

    private String getStarDisplay(int stars) {
        return "★".repeat(stars) + "☆".repeat(3 - stars);
    }

    // Helper method to handle separate gold and elixir resources
    private void displayStolenResources(ResourceManager gold, ResourceManager elixir) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("Resources Stolen");
        System.out.println(SEPARATOR);
        System.out.println("Gold: " + formatNumber(gold.getCurrentValue()));
        System.out.println("Elixir: " + formatNumber(elixir.getCurrentValue()));
    }
}