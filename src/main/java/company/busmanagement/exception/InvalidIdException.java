package company.busmanagement.exception;


public class InvalidIdException extends Exception {

    public InvalidIdException() {
    }

    @Override
    public String getMessage() {
        return "Invalid ID supplied";
    }
}
