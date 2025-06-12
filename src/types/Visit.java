package types;

import java.util.Date;

public record Visit(Prisoner visited, String name, Date date, String reason) {
    @Override
    public boolean equals(Object o) {
        if (o instanceof Visit(Prisoner visited1, String name1, Date date1, String reason1)) {
            return visited1.equals(visited) && name1.equals(name) && date1.equals(date) && reason1.equals(reason);
        }
        return false;
    }
}
