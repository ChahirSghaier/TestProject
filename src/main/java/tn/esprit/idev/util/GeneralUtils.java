package tn.esprit.idev.util;

import java.util.Calendar;
import java.util.Date;

public class GeneralUtils {

    public static Date calculateExpiryDate(final int expiryTime) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MONTH, expiryTime);
        return new Date(cal.getTime().getTime());
    }
}
