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

        boolean isFirstLine = true;
        for (String line : rawData) {
            if (isFirstLine && line.contains("ThreatLevel")) {
                isFirstLine = false;
                continue; // Skip header
            }

            String[] fields = line.split(",");
            if (fields.length < 4) continue; // Skip malformed lines

            String name = fields[0];
            String alias = fields[1];
            int threatLevel = Integer.parseInt(fields[2]);
            String lastKnownLocation = fields[3];
            criminals.add(new Criminal(name, alias, threatLevel, lastKnownLocation));
        }
        return criminals;
    }

}