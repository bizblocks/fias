package dev.smartdata.gar;

import org.meridor.fias.loader.XMLLoader;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class GarClient implements GarAware {
    
    private final XMLLoader xmlLoader;
    
    public GarClient(Path xmlDirectory) throws FileNotFoundException {
        if (!Files.exists(xmlDirectory)) {
            throw new FileNotFoundException(String.format(
                    "Specified path [%s] does not exist",
                    xmlDirectory
            ));
        }
        xmlLoader = new XMLLoader(xmlDirectory);
    }

    @Override
    public List<ADDRESSOBJECTS.OBJECT> load(Predicate<ADDRESSOBJECTS.OBJECT> predicate) {
        return xmlLoader.loadRaw(predicate);
    }
}
