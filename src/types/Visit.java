package types;

import java.util.Date;

public record Visit(Prisoner visited, String name, Date date) {
}
