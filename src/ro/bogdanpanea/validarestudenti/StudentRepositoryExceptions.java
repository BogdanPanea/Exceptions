package ro.bogdanpanea.validarestudenti;

public class StudentRepositoryExceptions extends Exception {

    private String errCode;

    public StudentRepositoryExceptions(String message, String errCode) {

        super(message);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }

}
