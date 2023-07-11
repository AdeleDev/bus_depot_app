package company.busmanagement.exception;

public class BusNotExistException extends Exception {

    public BusNotExistException() {
    }

    @Override
    public String getMessage() {
        return "No required bus exists in repository";
    }
}
