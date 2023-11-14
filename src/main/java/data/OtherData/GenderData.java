package data.OtherData;

public enum GenderData {
    MALE("m"),
    FEMALE("f");

    private String nameGender;

    GenderData(String nameGender) {
        this.nameGender = nameGender;
    }
    public String getNameGender() {
        return nameGender;
    }
}
