package engine.entity;


public class Assessment {


    public static final String SUCCESS = "Congratulations, you're right!";
    public static final String NO_SUCCESS = "Wrong answer! Please, try again.";
    private boolean success;

    public Assessment(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return success ? SUCCESS : NO_SUCCESS;
    }



}
