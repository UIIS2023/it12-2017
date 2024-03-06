package strategy;

import java.io.File;

public class SaveManager implements Save{
    private Save save;

    public SaveManager(Save save) {
        this.save = save;
    }

    public void setSave(Save save){
        this.save = save;
    }

    public Save getSave(){
        return this.save;
    }

    @Override
    public void save(Object object, File file) {
        save.save(object, file);
    }
}
