package ku.cs.models.reports;

public interface Filterer<T>{
    boolean filter(T o);
}
