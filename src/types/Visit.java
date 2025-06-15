package types;

import java.time.LocalDate;

public record Visit(String prisonerID, String name, LocalDate date, String reason) {
    @Override
    public boolean equals(Object o) {
        if (o instanceof Visit(String idOther, String nameOther, LocalDate dateOther, String reasonOther)) {
            return idOther.equals(prisonerID) && nameOther.equals(name) && dateOther.equals(date) && reasonOther.equals(reason);
        }
        return false;
    }
}
