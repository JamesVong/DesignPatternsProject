package guild.criminal;

import guild.iterator.GuildIterator;

import java.util.List;

public class CriminalIterator implements GuildIterator<Criminal> {
    private final List<Criminal> criminals;
    private int position = 0;

    public CriminalIterator(List<Criminal> criminals) {
        this.criminals = criminals;
    }

    @Override
    public boolean hasNext() {
        return position < criminals.size();
    }

    @Override
    public Criminal next() {
        return criminals.get(position++);
    }
}