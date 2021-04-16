package banking.api.service;

import java.util.List;

public interface Action<T, idT> {
    List<T> getAll();
    T getById(idT id);
    boolean create(T obj);
    boolean update(T obj);
    boolean delete(idT id);
}
