package clashclass.battle.battlereport;

public class BattleReportViewImpl implements BattleReportView {


    private static final String STAR_FILLED = "★";
    private static final String STAR_EMPTY = "☆";

    @Override
    public void update(final BattleReportModel model) {
        displayDestructionPercentage(model.getDestructionPercentage());
        displayStars(model.getStars());
        displayStolenResources(model.getStolenResources());
        displayBattleResult(model.isVictory());
        displayTroopCount(model.getTroopCount());
    }

    @Override
    public void displayDestructionPercentage(final double percentage) {
        System.out.println("Destruction: " + String.format("%.1f", percentage) + "%");
    }

    @Override
    public void displayStars(final int stars) {
        StringBuilder starDisplay = new StringBuilder();

        // Add filled stars
        for (int i = 0; i < stars; i++) {
            starDisplay.append(STAR_FILLED);
        }

        // Add empty stars
        for (int i = stars; i < 3; i++) {
            starDisplay.append(STAR_EMPTY);
        }

        System.out.println("Stars: " + starDisplay.toString());
    }

    @Override
    public void displayStolenResources(final ResourceManager resources) {
        System.out.println("Resources stolen:");
        System.out.println("  Gold: " + resources.getGold());
        System.out.println("  Elixir: " + resources.getElixir());
    }

    @Override
    public void displayBattleResult(final boolean isVictory) {
        if (isVictory) {
            System.out.println("VICTORY!");
        } else {
            System.out.println("DEFEAT!");
        }
    }

    @Override
    public void displayTroopCount(final int troopCount) {
        System.out.println("Troops used: " + troopCount);
    }

    public void displayBattleReportSummary(final BattleReportModel model) {
        System.out.println("=== BATTLE REPORT ===");

        // Display stars
        StringBuilder starDisplay = new StringBuilder();
        for (int i = 0; i < model.getStars(); i++) {
            starDisplay.append(STAR_FILLED);
        }
        for (int i = model.getStars(); i < 3; i++) {
            starDisplay.append(STAR_EMPTY);
        }
        System.out.println(starDisplay.toString());

        // Display destruction percentage
        System.out.println("Destruction: " + String.format("%.1f", model.getDestructionPercentage()) + "%");

        // Display resources
        ResourceManager resources = model.getStolenResources();
        System.out.println("Resources stolen:");
        System.out.println("  Gold: " + resources.getGold());
        System.out.println("  Elixir: " + resources.getElixir());

        // Display troop count
        System.out.println("Troops used: " + model.getTroopCount());

        // Display result
        if (model.isVictory()) {
            System.out.println("VICTORY!");
        } else {
            System.out.println("DEFEAT!");
        }

        System.out.println("=====================");
    }
}
