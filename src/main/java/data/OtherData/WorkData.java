package data.OtherData;

public enum WorkData {
    COMPANY("company"),
    JOB("work");

    private String nameWork;


    WorkData(String nameWork) {
        this.nameWork = nameWork;
    }

    public String getNameWork() {
        return nameWork;
    }
}
