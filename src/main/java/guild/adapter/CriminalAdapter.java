package guild.adapter;

import guild.criminal.Criminal;
import guild.database.Database;

import java.util.ArrayList;
import java.util.List;

public class CriminalAdapter {
    private final Database database;

    public CriminalAdapter(Database database) {
        this.database = database;
    }

    public List<Criminal> getCriminals() {
        List<String> rawData = database.readData();
        List<Criminal> criminals = new ArrayList<>();
        for (String line : rawData) {
            String[] fields = line.split(",");
            String name = fields[0];
            String alias = fields[1];
            int threatLevel = Integer.parseInt(fields[2]);
            String lastKnownLocation = fields[3];
            criminals.add(new Criminal(name, alias, threatLevel, lastKnownLocation));
        }
        return criminals;
    }
}