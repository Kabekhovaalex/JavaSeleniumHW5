package data.basicInformationData;

public enum ContryData {
    RUSSIA("Россия");
    private String countryName;
    ContryData(String countryName) {
        this.countryName = countryName;
    }
    public String getCountryName() {
        return countryName;
    }
}
