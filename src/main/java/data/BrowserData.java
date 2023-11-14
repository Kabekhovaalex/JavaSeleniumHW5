package data;

public enum BrowserData {
    CHROME("chrome");
    private String nameBrowser;

    BrowserData(String nameBrowser) {
        this.nameBrowser = nameBrowser;
    }

    public String getNameBrowser() {
        return nameBrowser;
    }
}
