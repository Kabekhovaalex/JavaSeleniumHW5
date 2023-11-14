package data.basicInformationData;

import data.basicInformationData.ContryData;

public enum CityData {
    ASTRAKHAN("Астрахань", ContryData.RUSSIA);
    private String cityName;
    private ContryData contryData;

    CityData(String cityName, ContryData contryData) {
        this.cityName = cityName;
        this.contryData = contryData;
    }

    public String getCityName() {
        return cityName;
    }

    public ContryData getContryData() {
        return contryData;
    }

}
