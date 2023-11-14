package data.ContactInformationData;

public enum SocialNetworkCommunData {
    VK("vk"),
    TELEGRAM("telegram");

    private String nameSocialNetworkCommun;

    SocialNetworkCommunData(String nameSocialNetworkCommun) {
        this.nameSocialNetworkCommun = nameSocialNetworkCommun;
    }
    public String getNameSocialNetworkCommun() {
        return nameSocialNetworkCommun;
    }
}

