package borkowski.domain;
import borkowski.store.TimeStampInterface;
import java.time.LocalDate;

import java.util.Date;

public class TimeStamp implements TimeStampInterface {

    @Override

    public LocalDate getTimeNow() {
        return LocalDate.now();
    }
}
