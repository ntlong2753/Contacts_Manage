package file;

import model.Contacts;

import java.util.List;

public interface FileHandler {
    public abstract List<Contacts> read (String path);
    public abstract void write (String path, Contacts contacts);
    public abstract void save (String path, List<Contacts> contacts);
}
