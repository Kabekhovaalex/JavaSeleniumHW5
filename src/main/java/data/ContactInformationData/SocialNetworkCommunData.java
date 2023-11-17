package data.ContactInformationData;

public enum SocialNetworkCommunData {
    VK("contact-0-value"),
    TELEGRAM("contact-1-value");

    private String nameSocialNetworkCommun;

    SocialNetworkCommunData(String nameSocialNetworkCommun) {
        this.nameSocialNetworkCommun = nameSocialNetworkCommun;
    }
    public String getNameSocialNetworkCommun() {
        return nameSocialNetworkCommun;
    }
}

