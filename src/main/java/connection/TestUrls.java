package connection;

public class TestUrls {
    private String oldUrl;
    private String newUrl;

    public TestUrls() {
    }

    public TestUrls(String oldUrl, String newUrl) {
        this.oldUrl = oldUrl;
        this.newUrl = newUrl;
    }

    public String getOldUrl() {
        return oldUrl;
    }

    public String getNewUrl() {
        return newUrl;
    }

    public void setOldUrl(String oldUrl) {
        this.oldUrl = oldUrl;
    }

    public void setNewUrl(String newUrl) {
        this.newUrl = newUrl;
    }

    @Override
    public String toString() {
        return "TestUrls{" +
                "oldUrl='" + oldUrl + '\'' +
                ", newUrl='" + newUrl + '\'' +
                '}';
    }
}
