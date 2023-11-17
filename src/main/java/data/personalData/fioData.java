package data.personalData;

public enum fioData {
    FNAME("fname"),
    LNAME("lname"),
    FNAME_LATIN("fname_latin"),
    LNAME_LATIN("lname_latin"),
    BLOG_NAME("blog_name"),
    GENDER ("gender");

    private String fioName;

    fioData(String fioName) {
        this.fioName = fioName;
    }
    public String getFioName() {
        return fioName;
    }

}
