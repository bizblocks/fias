package com.groupstp.fias.utils.client;

import com.haulmont.cuba.core.global.Configuration;
import dev.smartdata.gar.ADDRESSOBJECTS;
import org.meridor.fias.enums.AddressLevel;
import org.meridor.fias.enums.FiasFile;
import org.meridor.fias.loader.PartialUnmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.meridor.fias.enums.FiasFile.ADDRESS_OBJECTS;
import static org.meridor.fias.enums.FiasFile.HOUSE;

public class XMLLoaderFork {
    private static final Logger log = LoggerFactory.getLogger("FiasClient");

    private final Path xmlDirectory;

    @Inject
    private Configuration configuration;

    public XMLLoaderFork(Path xmlDirectory) {
        this.xmlDirectory = xmlDirectory;
    }

    private Path getPathByPattern(String startsWith) throws IOException {
        Optional<Path> filePath = Files.list(xmlDirectory)
                .map(xmlDirectory::relativize)
                .filter(path -> path.toString().startsWith(startsWith) && path.toString().toLowerCase().endsWith("xml"))
                .findFirst();
        if (!filePath.isPresent()) {
            throw new FileNotFoundException(String.format("Can't find XML file with name starting with [%s]", startsWith));
        }
        return xmlDirectory.resolve(filePath.get());
    }

    public <T> PartialUnmarshaller<T> getUnmarshaller(Class<T> clazz, FiasFile level) {
        try {
            Path filePath = getPathByPattern(level.getName());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath.toFile()));
            return new PartialUnmarshaller<>(inputStream, clazz);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public <T> PartialUnmarshallerFork<T> getUnmarshallerFork(Class<T> clazz, FiasFile level, long offset) {
        try {
            Path filePath = getPathByPattern(level.getName());
            ProgressCounterFilterInputStream inputStream = new ProgressCounterFilterInputStream(new BufferedInputStream(new FileInputStream(filePath.toFile())));
            return new PartialUnmarshallerFork<T>(inputStream, clazz, offset);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Deprecated
    public List<ADDRESSOBJECTS.OBJECT> loadRaw(Predicate<ADDRESSOBJECTS.OBJECT> predicate) {
        if (predicate == null) {
            return Collections.emptyList();
        }
        try {
            Path filePath = getPathByPattern(ADDRESS_OBJECTS.getName());
            //InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath.toFile()));
            ProgressCounterFilterInputStream inputStream = new ProgressCounterFilterInputStream(new BufferedInputStream(new FileInputStream(filePath.toFile())));
            log.info("Searching objects in file {}", filePath);
            try (PartialUnmarshallerFork<ADDRESSOBJECTS.OBJECT> partialUnmarshaller = new PartialUnmarshallerFork<>(inputStream, ADDRESSOBJECTS.OBJECT.class)) {
                List<ADDRESSOBJECTS.OBJECT> results = new ArrayList<>();
                while (partialUnmarshaller.hasNext()) {
                    ADDRESSOBJECTS.OBJECT addressObject = partialUnmarshaller.next();
                    if (predicate.test(addressObject)) {
                        results.add(addressObject);
                        log.info("Founded {} object(s) in file, readed {} % of file",
                                results.indexOf(addressObject),
                                //(int) (Math.abs((double) partialUnmarshaller.getReader().getLocation().getCharacterOffset()) / (double) Files.size(filePath) * 100));
                                (int) (Math.abs((double) partialUnmarshaller.getInputStream().getProgress() / (double) Files.size(filePath) * 100)));
                    }
                }
                return results;
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Nullable
    public AddressObjectFork loadObject(Predicate<ADDRESSOBJECTS.OBJECT> predicate, Path filePath, long offset) {
        try {
            ProgressCounterFilterInputStream inputStream = new ProgressCounterFilterInputStream(new BufferedInputStream(new FileInputStream(filePath.toFile())));
            try (PartialUnmarshallerFork<ADDRESSOBJECTS.OBJECT> partialUnmarshaller = new PartialUnmarshallerFork<>(inputStream, ADDRESSOBJECTS.OBJECT.class, offset)) {
                while (partialUnmarshaller.hasNext()) {
                    ADDRESSOBJECTS.OBJECT addressObject = partialUnmarshaller.next();
                    if (predicate.test(addressObject)) {
                        return new AddressObjectFork(addressObject, partialUnmarshaller.getInputStream().getProgress());
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return null;
    }

    @Nullable
    public AddressObjectFork loadObject(Predicate<ADDRESSOBJECTS.OBJECT> predicate, ProgressCounterFilterInputStream inputStream, long offset) {
        try {
            try (PartialUnmarshallerFork<ADDRESSOBJECTS.OBJECT> partialUnmarshaller = new PartialUnmarshallerFork<>(inputStream, ADDRESSOBJECTS.OBJECT.class, offset)) {
                while (partialUnmarshaller.hasNext()) {
                    ADDRESSOBJECTS.OBJECT addressObject = partialUnmarshaller.next();
                    if (predicate.test(addressObject)) {
                        return new AddressObjectFork(addressObject, partialUnmarshaller.getInputStream().getProgress());
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return null;
    }

    @Nullable
    public List<AddressObjectFork> loadObjects(Predicate<ADDRESSOBJECTS.OBJECT> predicate, Path filePath, long offset, int batchSize) throws FileNotFoundException {
        List<AddressObjectFork> results = new ArrayList<>();
        ProgressCounterFilterInputStream inputStream = new ProgressCounterFilterInputStream(new BufferedInputStream(new FileInputStream(filePath.toFile())));
        log.info("Searching objects in file {}", filePath);
        try {
            try (PartialUnmarshallerFork<ADDRESSOBJECTS.OBJECT> partialUnmarshaller = new PartialUnmarshallerFork<>(inputStream, ADDRESSOBJECTS.OBJECT.class, offset)) {
                while (partialUnmarshaller.hasNext()) {
                    ADDRESSOBJECTS.OBJECT addressObject = partialUnmarshaller.next();
                    if (predicate.test(addressObject)) {
                        AddressObjectFork addressObjectFork = new AddressObjectFork(addressObject, partialUnmarshaller.getInputStream().getProgress());
                        results.add(addressObjectFork);
                        log.info("Founded {} object(s) in file, reached {}% of file",
                                results.indexOf(addressObjectFork) + 1,
                                (int) (Math.abs((double) partialUnmarshaller.getInputStream().getProgress() / (double) Files.size(filePath) * 100)));
                        //если достигнут размер пакета для записи в бд
                        if (results.size() == batchSize)
                            break;
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return results;
    }
}
