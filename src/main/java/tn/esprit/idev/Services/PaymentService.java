package tn.esprit.idev.Services;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    /*
    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    public Boolean validatePaymentDetails(PaymentDetails paymentDetails) throws InvalidPaymentDetailsException {
        // Validate payment details
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<PaymentDetails>> violations = validator.validate(paymentDetails);
        if (!violations.isEmpty()) {
            throw new InvalidPaymentDetailsException("Invalid payment details: " + violations.toString());
        }
        // Payment details are valid
        return true;
    }

    public void storePaymentDetails(PaymentDetails paymentDetails) throws ApiResponse {
        try {
            paymentDetailsRepository.save(paymentDetails);
        } catch (Exception e) {
            throw new ApiResponse("Failed to store payment details. Please try again later.");
        }
    }

*/
}
