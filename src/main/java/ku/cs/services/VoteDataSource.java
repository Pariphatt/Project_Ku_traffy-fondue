package ku.cs.services;

import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;
import ku.cs.models.reports.Vote;
import ku.cs.models.reports.VoteList;

import java.io.*;
import java.util.Date;

public class VoteDataSource implements DataSource<VoteList> {
    private String directoryName;
    private String fileName;

    public VoteDataSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    public VoteDataSource() {
        directoryName = "assets";
        fileName = "reports.csv";
    }

    private void checkFileIsExisted(){
        File file = new File(directoryName);
        if ( ! file.exists()){
            file.mkdirs();
        }
        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if ( ! file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public VoteList readData() {
        VoteList voteList = new VoteList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;
        try{
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while((line = buffer.readLine()) != null){
                String[] data = line.split(",");
                Vote vote = null;
                if (data.length == 1){
                    Date currentDate = new Date();
                    vote = new Vote(data[0].trim());
                }
                voteList.addVote(vote);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return voteList;
    }

    @Override
    public void writeData(VoteList voteList){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try{
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            for (Vote vote : voteList.getaAllVote()){
                String line = vote.getTopicAndUserReport();

                buffer.append(line);
                buffer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
