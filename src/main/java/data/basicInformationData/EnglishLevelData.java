package data.basicInformationData;

public enum EnglishLevelData {
    BEGINNER("Начальный уровень (Beginner)");
    private String nameEnglishLevel;


    public String getNameEnglishLevel() {
        return nameEnglishLevel;
    }
    EnglishLevelData(String nameEnglishLevel) {
        this.nameEnglishLevel = nameEnglishLevel;
    }
}
