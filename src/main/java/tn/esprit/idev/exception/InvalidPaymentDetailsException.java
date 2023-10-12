package tn.esprit.idev.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidPaymentDetailsException extends Exception {
String subject;
}
