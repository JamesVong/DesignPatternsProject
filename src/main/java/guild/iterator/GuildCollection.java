package guild.iterator;

public interface GuildCollection<T> {
    GuildIterator<T> createIterator();
}