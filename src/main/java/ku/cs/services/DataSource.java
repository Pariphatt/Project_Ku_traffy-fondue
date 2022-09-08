package ku.cs.services;

public interface DataSource<T>{
    T readData();
    T getData();
    void writeData(T t);
}
