package connection;

public class ResponseUrls {

    private int responseCode;
    private String redirectedUrl;

    public ResponseUrls(int responseCode, String redirectedUrl) {
        this.responseCode = responseCode;
        this.redirectedUrl = redirectedUrl;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getRedirectedUrl() {
        return redirectedUrl;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public void setRedirectedUrl(String redirectedUrl) {
        this.redirectedUrl = redirectedUrl;
    }

    @Override
    public String toString() {
        return "ResponseUrls{" +
                "responseCode=" + responseCode +
                ", redirectedUrl='" + redirectedUrl + '\'' +
                '}';
    }
}
