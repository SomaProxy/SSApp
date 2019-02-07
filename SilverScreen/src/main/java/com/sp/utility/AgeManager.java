package com.sp.utility;

import java.time.LocalDate;
import java.time.Period;

public class AgeManager {

    public Integer getHumanAge(LocalDate CurrentDate, LocalDate BirthdayDate) {
        if (CurrentDate != null && BirthdayDate != null) {
            Period period = Period.between(BirthdayDate, CurrentDate);
            return period.getYears();
        } else {
            return 0;
        }
    }

}
