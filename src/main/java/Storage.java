import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Storage {

    private Path path;

    public Storage(Path path) {
        this.path = path;
    }

    public List<Task> load() {
        boolean directoryExists = java.nio.file.Files.exists(path);
        List<Task> list = new ArrayList<>();

        try {
            if (directoryExists) {
                FileReader fileReader = new FileReader(String.valueOf(path));
                BufferedReader buffReader = new BufferedReader(fileReader);
                while (buffReader.ready()) {
                    String savedTask = buffReader.readLine();
                    String type = savedTask.substring(0, 1);
                    switch (type) {
                        case "T":
                            list.add(new ToDo(savedTask.substring(4).trim(), savedTask.substring(2, 3).equals("T")));
                            break;
                        case "D":
                            String deadlineDetails = savedTask.substring(4);
                            String[] deadlineArr = deadlineDetails.split("/by");
                            Deadline deadline = new Deadline(
                                    deadlineArr[0].trim(),
                                    savedTask.substring(2, 3).equals("T"),
                                    LocalDateTime.parse(deadlineArr[1].trim()));
                            list.add(deadline);
                            break;
                        case "E":
                            String eventDetails = savedTask.substring(4);
                            String[] eventArr = eventDetails.split("/at");
                            Event event = new Event(
                                    eventArr[0].trim(),
                                    savedTask.substring(2, 3).equals("T"),
                                    LocalDateTime.parse(eventArr[1].trim()));
                            list.add(event);
                            break;
                    }
                }
            } else {
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void addData(String data) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(String.valueOf(path), true), "UTF-8"));
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateData(String data, int taskNumber) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)));
            String newData = "";
            String oldData;
            int lineNumber = 1;
            while (br.ready()) {
                oldData = br.readLine();
                if (lineNumber == taskNumber) {
                    newData += data.equals("") ? data : data + "\n";
                } else {
                    newData += oldData + "\n";
                }
                lineNumber++;
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(String.valueOf(path)));
            bw.write(newData);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}