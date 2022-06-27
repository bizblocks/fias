package com.groupstp.fias.utils.client;


import dev.smartdata.gar.ADDRESSOBJECTS;
import org.meridor.fias.enums.AddressLevel;
import org.meridor.fias.enums.FiasFile;
import org.meridor.fias.loader.PartialUnmarshaller;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class GarClientFork {
    private final XMLLoaderFork xmlLoaderFork;

    public GarClientFork(Path xmlDirectory) throws FileNotFoundException {
        if (!Files.exists(xmlDirectory)) {
            throw new FileNotFoundException(String.format(
                    "Specified path [%s] does not exist",
                    xmlDirectory
            ));
        }
        xmlLoaderFork = new XMLLoaderFork(xmlDirectory);
    }

    public <T> PartialUnmarshaller<T> getUnmarshaller(Class<T> clazz, FiasFile pattern) {
        return xmlLoaderFork.getUnmarshaller(clazz, pattern);
    }

    public <T> PartialUnmarshallerFork<T> getUnmarshallerFork(Class<T> clazz, FiasFile pattern, long offset) {
        return xmlLoaderFork.getUnmarshallerFork(clazz, pattern, offset);
    }

    @Deprecated
    public List<ADDRESSOBJECTS.OBJECT> load(Predicate<ADDRESSOBJECTS.OBJECT> predicate) {
        return xmlLoaderFork.loadRaw(predicate);
    }

    public AddressObjectFork load(Predicate<ADDRESSOBJECTS.OBJECT> predicate, Path filePath, long offset) {
        return xmlLoaderFork.loadObject(predicate, filePath, offset);
    }

    public AddressObjectFork load(Predicate<ADDRESSOBJECTS.OBJECT> predicate, ProgressCounterFilterInputStream inputStream, long offset) {
        return xmlLoaderFork.loadObject(predicate, inputStream, offset);
    }

    public List<AddressObjectFork> loadList(Predicate<ADDRESSOBJECTS.OBJECT> predicate, Path filePath, long offset, int batchSize) throws FileNotFoundException {
        return xmlLoaderFork.loadObjects(predicate, filePath, offset, batchSize);
    }
}
