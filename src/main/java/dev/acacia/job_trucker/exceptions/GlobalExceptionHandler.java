package dev.acacia.job_trucker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        String customMessage = "ERROR 404: NOT FOUND. Can't perform this operation.";
        return new ResponseEntity<>(customMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        String customMessage = "\n      ERROR: 500. An unexpected error occurred. Please try again later.";
        return new ResponseEntity<>(customMessage, HttpStatus.INTERNAL_SERVER_ERROR); 
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        String customMessage = "\n      ERROR 404: USER NOT FOUND.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customMessage);
    }

    @ExceptionHandler(NoUsersFoundException.class)
    public ResponseEntity<String> handleNoUsersFoundException(NoUsersFoundException ex) {
        String customMessage = "\n      ERROR 404: NO USERS FOUND.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customMessage);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        String customMessage = "\n      ERROR 409: CONFLICT. EMAIL ALREADY EXISTS.";
        return ResponseEntity.status(HttpStatus.CONFLICT).body(customMessage);

    }

    @ExceptionHandler(OfferNotFoundException.class)
    public ResponseEntity<String> handelOfferNotFoundException(OfferNotFoundException ex) {
        String customMessage = "\n      ERROR 404: OFFER NOT FOUND.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customMessage);
    }

    @ExceptionHandler(OfferAssociatedWithUserException.class)
    public ResponseEntity<String> handelOfferAssociatedWithUserException(OfferAssociatedWithUserException ex) {
        String customMessage = "\n      ERROR 404: CAN'T BE DELETED USER BECAUSE HAS LINKED OFFERS.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customMessage);
    }
    
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException() {
            super("\n      ERROR 404: User not found");
        }
        public UserNotFoundException(String message) {
            super(message);
        }
    }
    
    public static class NoUsersFoundException extends RuntimeException {
        public NoUsersFoundException() {
            super("\n      ERROR 404: No users found");
        }
        public NoUsersFoundException(String message) {
            super(message);
        }
    }
    
    public static class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class OfferNotFoundException extends RuntimeException {
        public OfferNotFoundException() {
            super("\n      ERROR 404: Offer not found");
        }
        public OfferNotFoundException(String message) {
            super(message);
        }
    }

    public static class OfferAssociatedWithUserException extends RuntimeException {
        public OfferAssociatedWithUserException() {
            super("\n      ERROR 404: CAN'T BE DELETED USER BECAUSE HAS LINKED OFFERS");
        }
        public OfferAssociatedWithUserException(String message) {
            super(message);
        }
    }
}