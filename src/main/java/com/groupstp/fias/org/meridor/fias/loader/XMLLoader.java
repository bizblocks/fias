package com.groupstp.fias.org.meridor.fias.loader;

import dev.smartdata.gar.ADDRESSOBJECTS;
import org.meridor.fias.enums.FiasFile;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.meridor.fias.enums.FiasFile.ADDRESS_OBJECTS;

public class XMLLoader {

    private final Path xmlDirectory;

    public XMLLoader(Path xmlDirectory) {
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

    public <T> T loadReferenceTable(FiasFile fiasFile, Class<T> someClass) throws JAXBException, IOException {
        String fileName = fiasFile.getName();
        Path filePath = getPathByPattern(fileName);
        return JAXB.unmarshal(filePath.toFile(), someClass);
    }

    public List<ADDRESSOBJECTS.OBJECT> loadRaw(Predicate<ADDRESSOBJECTS.OBJECT> predicate) {
        if (predicate == null) {
            return Collections.emptyList();
        }
        try {
            Path filePath = getPathByPattern(ADDRESS_OBJECTS.getName());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath.toFile()));
            try (PartialUnmarshaller<ADDRESSOBJECTS.OBJECT> partialUnmarshaller = new PartialUnmarshaller<>(inputStream, ADDRESSOBJECTS.OBJECT.class)) {
                List<ADDRESSOBJECTS.OBJECT> results = new ArrayList<>();
                while (partialUnmarshaller.hasNext()) {
                    ADDRESSOBJECTS.OBJECT addressObject = partialUnmarshaller.next();
                    if (predicate.test(addressObject)) {
                        results.add(addressObject);
                    }
                }
                return results;
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


}
