package connection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;


public class CsvUtil {

    private String filePath;

    public CsvUtil(String filePath) {
        this.filePath = filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<String[]> getData() throws IOException, CsvException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> data = csvReader.readAll();
        csvReader.close();
        return data;
    }

    public List<String[]> getData(int startRow, int endRow) throws IOException, CsvException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> data = csvReader.readAll();
        csvReader.close();
        List<String[]> dataSubset = new ArrayList<>();
        for (int i = startRow; i <= endRow; i++) {
            dataSubset.add(data.get(i));
        }
        return dataSubset;
    }

    public List<String[]> getData(int startRow, int endRow, int sleepTime) throws IOException, CsvException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> data = csvReader.readAll();
        csvReader.close();
        List<String[]> dataSubset = new ArrayList<>();
        for (int i = startRow; i <= endRow; i++) {
            dataSubset.add(data.get(i));
            try {
                TimeUnit.SECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return dataSubset;
    }

    public List<TestUrls> readUrlsFromCSV() {
        long startTime = System.nanoTime();
        List<TestUrls> listUrls = new ArrayList<TestUrls>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVReader csvReader = new CSVReader(reader);) {
            String[] nextRecord;
            TestUrls testUrls;
            while ((nextRecord = csvReader.readNext()) != null) {
                testUrls = new TestUrls();
                testUrls.setOldUrl(nextRecord[2]);
                testUrls.setNewUrl(nextRecord[3]);
                listUrls.add(testUrls);
            }
        } catch (IOException e) {
            System.out.println("IOException"+e.getMessage());
        } catch (CsvValidationException e) {
            System.out.println("CsvValidationException"+e.getMessage());
        }

        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        return listUrls;
    }

    public void writeDataAtOnce(List<String[]> data)
    {

        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // create a List which contains String array
            writer.writeAll(data);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
