package se.yrgo.spring.data;

public class CustomerCreditExceededException extends Exception {
    public CustomerCreditExceededException() {
        super("Could not complete the purchase.");
    }
    
}
