package guild.adapter;

import guild.bountyhunter.BountyHunter;
import guild.database.Database;

import java.util.ArrayList;
import java.util.List;

public class BountyHunterAdapter {
    private final Database database;

    public BountyHunterAdapter(Database database) {
        this.database = database;
    }

    public List<BountyHunter> getBountyHunters() {
        List<String> rawData = database.readData();
        List<BountyHunter> bountyHunters = new ArrayList<>();
        for (String line : rawData) {
            String[] fields = line.split(",");
            String name = fields[0];
            String faction = fields[1];
            String rank = fields[2];
            bountyHunters.add(new BountyHunter(name, faction, rank));
        }
        return bountyHunters;
    }
}