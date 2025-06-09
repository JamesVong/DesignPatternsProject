package guild.iterator;

public interface GuildIterator<T> {
    boolean hasNext();
    T next();
}