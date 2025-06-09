package guild.database;

import java.util.List;

public interface Database {
    List<String> readData();
    void writeData(List<String> data);
}