package strategy;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class SaveLog implements Save{

    @Override
    public void save(Object object, File file) {
        JTextArea log = (JTextArea) object;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
            log.write(writer);
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
}
}
