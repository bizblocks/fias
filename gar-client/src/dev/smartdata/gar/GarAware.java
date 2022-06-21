package dev.smartdata.gar;

import java.util.List;
import java.util.function.Predicate;

import dev.smartdata.gar.ADDRESSOBJECTS;

public interface GarAware {
    
    List<ADDRESSOBJECTS.OBJECT> load(Predicate<ADDRESSOBJECTS.OBJECT> predicate);
    
}
