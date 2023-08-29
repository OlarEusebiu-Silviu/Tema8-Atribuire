import java.util.*;
import java.util.stream.Collectors;

enum ShootingResult {
    HIT("x"), MISS("o");

    private final String value;

    ShootingResult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

