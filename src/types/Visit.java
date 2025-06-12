package types;

import java.util.Date;

public record Visit(Prisoner visitedPrisoner, String name, Date date, String reason) {
    @Override
    public boolean equals(Object o) {
        if (o instanceof Visit(Prisoner visitedOther, String nameOther, Date dateOther, String reasonOther)) {
            return visitedOther.equals(visitedPrisoner) && nameOther.equals(name) && dateOther.equals(date) && reasonOther.equals(reason);
        }
        return false;
    }
}
