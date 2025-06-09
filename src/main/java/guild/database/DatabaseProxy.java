package guild.database;

import java.util.List;

public class DatabaseProxy implements Database {
    private final RealDatabase realDatabase;
    private List<String> cache;

    public DatabaseProxy(String filePath) {
        this.realDatabase = new RealDatabase(filePath);
        this.cache = null; // Cache is initially empty
    }

    @Override
    public List<String> readData() {
        if (cache == null) {
            System.out.println("Loading data from the real database...");
            cache = realDatabase.readData();
        } else {
            System.out.println("Returning cached data...");
        }
        return cache;
    }

    @Override
    public void writeData(List<String> data) {
        System.out.println("Writing data to the real database...");
        realDatabase.writeData(data);
        cache = data; // Update the cache
    }
}