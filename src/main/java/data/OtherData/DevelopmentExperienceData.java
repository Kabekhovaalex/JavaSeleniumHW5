package data.OtherData;

public enum DevelopmentExperienceData {
    START("Только начал");
    private String nameDevelopmentExperience;

    DevelopmentExperienceData(String nameDevelopmentExperience) {
        this.nameDevelopmentExperience = nameDevelopmentExperience;
    }
    public String getNameDevelopmentExperience() {
        return nameDevelopmentExperience;
    }
}
