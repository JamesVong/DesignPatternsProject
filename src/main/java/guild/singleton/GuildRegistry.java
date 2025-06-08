package guild.singleton;

import java.util.HashMap;
import java.util.Map;

public class GuildRegistry {
    private static GuildRegistry instance;
    private Map<String, String> registeredHunters;

    private GuildRegistry() {
        registeredHunters = new HashMap<>();
    }

    public static GuildRegistry getInstance() {
        if (instance == null) {
            instance = new GuildRegistry();
        }
        return instance;
    }

    public void registerHunter(String id, String name) {
        registeredHunters.put(id, name);
        System.out.println("Hunter registered: " + name);
    }

    public String getHunter(String id) {
        return registeredHunters.get(id);
    }

    public void printAllHunters() {
        System.out.println("=== Registered Bounty Hunters ===");
        for (Map.Entry<String, String> entry : registeredHunters.entrySet()) {
            System.out.println("ID: " + entry.getKey() + ", Name: " + entry.getValue());
        }
    }
}
