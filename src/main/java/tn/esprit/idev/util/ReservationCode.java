package tn.esprit.idev.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@NoArgsConstructor
@Service

public class ReservationCode {




    private static final int ENCODER_STRENGTH = 10;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(ENCODER_STRENGTH);

    public String generateReservationCode(Long reservationId) {
        String rawString = reservationId.toString() + System.currentTimeMillis();
        String encodedString = encoder.encode(rawString);
        return encodedString;
    }
}
