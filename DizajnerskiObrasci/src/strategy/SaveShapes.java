package strategy;

import mvc.DrawingModel;

import java.io.*;

public class SaveShapes implements Save{
    @Override
    public void save(Object object, File file) {
        DrawingModel model = (DrawingModel) object;
        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
            writer.writeObject(model.getShapes());
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
