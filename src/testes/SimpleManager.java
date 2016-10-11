package testes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SimpleManager extends AbstractData<SimpleObject> {

    private final List<SimpleObject> simpleObjectList = new ArrayList<>();

    public SimpleManager() {
        this.simpleObjectList.add(new SimpleObject("CocaCola", 1));
        this.simpleObjectList.add(new SimpleObject("Cookies", 2));
        this.simpleObjectList.add(new SimpleObject("Pepsi", 3));
    }

    @Override
    protected void load(File file) {
        // TODO
    }

    @Override
    protected void save(SimpleObject object) {
        // TODO
    }

    @Override
    public List<SimpleObject> getBasicList() {
        return this.simpleObjectList;
    }

    @Override
    public File getDirectory() {
        return new File("simpleDirectory");
    }
}
