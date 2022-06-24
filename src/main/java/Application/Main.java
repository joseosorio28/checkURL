package Application;

import connection.Connection;
import connection.CsvUtil;
import connection.ResponseUrls;
import connection.TestUrls;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("===========================");
        System.out.println("Welcome to the application");

        String inputHostUrl = "http://localhost:8085";//"http://34.225.202.229";

        Connection connection = new Connection(inputHostUrl,"");

        String filePath = "/Users/jose.osorio/Mi unidad/Grupo Familia/Redirects/url.csv";
        CsvUtil csvUtil = new CsvUtil(filePath);
        List<TestUrls> testUrls = csvUtil.readUrlsFromCSV();
        List<String[]> statusCodes = new ArrayList<>();
        double testUrlCount = testUrls.size();
        double i= 0;
        int totalTime = 0;
        String redirectedUrl = "";
        for (TestUrls testUrl : testUrls) {
            connection.setUrlPath(testUrl.getOldUrl());
            ResponseUrls responseUrls = connection.sendGetRequest();
            redirectedUrl = responseUrls.getRedirectedUrl();
            statusCodes.add(new String[] {testUrl.getOldUrl(),testUrl.getNewUrl(),redirectedUrl,
                    ""+redirectedUrl.equals(testUrl.getNewUrl()),""+responseUrls.getResponseCode()});

            i++;totalTime= (int) (100*i/testUrlCount);
            System.out.print("Processing: "+totalTime+"%\r");
        }

        csvUtil.setFilePath("/Users/jose.osorio/Mi unidad/Grupo Familia/Redirects/url_status_code.csv");
        csvUtil.writeDataAtOnce(statusCodes);

        System.out.println("Application finished");
        System.out.println("===========================");

    }
}
