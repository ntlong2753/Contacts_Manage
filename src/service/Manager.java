package service;

import java.util.List;

public interface Manager<Contacts> {
    void add(Contacts t);
    void update();
    void delete (String phoneNumber);
    Contacts finByPhongNumber(String phoneNumber);
    List<Contacts> findAll();
}
