package CustomerPackage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class GymVisit {
    LocalDate date;
    LocalTime time;

    //Constructor
    public GymVisit(LocalDateTime dateTime) {
        this.date = dateTime.toLocalDate();
        this.time = dateTime.toLocalTime();
    }

    @Override
    public String toString() {
        return date.toString() + ", klockan " + time.toString();
    }
}
