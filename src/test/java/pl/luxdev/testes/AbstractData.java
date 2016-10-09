package pl.luxdev.testes;

import java.io.File;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractData<T> {

    public void loadAll() {

    }

    public void saveAll() {
        this.getBasicList().parallelStream().forEach(this::save);
    }

    public List<T> getFilteredList(Predicate<T>... predicates) {
        List<T> result = this.getBasicList();
        for (Predicate<T> predicate : predicates) {
            result = result.stream().filter(predicate::test).collect(Collectors.toList());
        }
        return result;
    }

    protected abstract void load(File file);

    protected abstract void save(T object);

    public abstract List<T> getBasicList();

    public abstract File getDirectory();

}


