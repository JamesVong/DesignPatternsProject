package guild.criminal;

import guild.iterator.GuildCollection;
import guild.iterator.GuildIterator;

import java.util.ArrayList;
import java.util.List;

public class CriminalCollection implements GuildCollection<Criminal> {
    private final List<Criminal> criminals = new ArrayList<>();

    public void addCriminal(Criminal criminal) {
        criminals.add(criminal);
    }

    public void removeCriminal(Criminal criminal) {
        criminals.remove(criminal);
    }

    @Override
    public GuildIterator<Criminal> createIterator() {
        return new CriminalIterator(criminals);
    }
}