package company.busmanagement.exception;

public class BusAlreadyExistException extends Exception {

    private final String number;


    public BusAlreadyExistException(String number) {
        this.number = number;
    }

    @Override
    public String getMessage() {
        return "Bus with number " + number +  " already exists";
    }

}
