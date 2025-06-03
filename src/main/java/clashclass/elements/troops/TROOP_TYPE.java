package clashclass.elements.troops;

import java.util.Arrays;
import java.util.Optional;

public enum TROOP_TYPE {
    BARBARIAN("barbarian"),
    ARCHER("archer");

    private final String name;

    TROOP_TYPE(final String name) {
        this.name = name;
    }

    public static Optional<TROOP_TYPE> getValueFromName(String id) {
        return Arrays.stream(values())
                .filter(x -> x.name.equalsIgnoreCase(id))
                .findFirst();
    }

    public String getName() {
        return this.name;
    }
}
