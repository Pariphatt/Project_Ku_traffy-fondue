package ku.cs.models.reports;

import java.io.*;

public class DetailReport {
    private String directoryReport = "assets";

    public void reportButton(String topic, String detail) {
        String filePath = directoryReport + File.separator + "reports.csv";
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file,true);
            buffer = new BufferedWriter(writer);
            String report = topic + "," + detail;
            buffer.append(report);
            buffer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

}



