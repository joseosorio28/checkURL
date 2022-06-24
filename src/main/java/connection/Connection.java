package connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {

    private static final String USER_AGENT = "Mozilla/5.0";
    private String inputHostUrl;
    private String urlPath;

    public Connection() {
        this.inputHostUrl = "http://localhost:8080/";
        this.urlPath = "";
    }

    public Connection(String inputHostUrl, String urlPath) {
        this.inputHostUrl = inputHostUrl;
        this.urlPath = urlPath;
    }

    public void setInputHostUrl(String inputHostUrl) {
        this.inputHostUrl = inputHostUrl;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public ResponseUrls sendGetRequest() {
        String url = inputHostUrl + urlPath;
        int responseCode =-1;
        String redirectedUrl = "null";
        try {
            //System.out.println("Sending request to: " + url);
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("HEAD");
            //con.setRequestProperty("User-Agent", USER_AGENT);
            responseCode = con.getResponseCode();
            redirectedUrl = con.getHeaderField("Location");
            Thread.sleep(150);
            if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP
                    || responseCode == HttpURLConnection.HTTP_MOVED_PERM) {
                URL newUrl = new URL(redirectedUrl);
                con = (HttpURLConnection) newUrl.openConnection();
                con.setRequestMethod("HEAD");
                responseCode = con.getResponseCode();

            }
            Thread.sleep(150);


            //System.out.println("Response Code : " + responseCode);
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                String inputLine;
//                StringBuilder response = new StringBuilder();
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                in.close();
//                //System.out.println(response.toString());
//            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return new ResponseUrls(responseCode, redirectedUrl);
    }
}
