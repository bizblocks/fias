package com.groupstp.fias.utils.client;

import dev.smartdata.gar.ADDRESSOBJECTS;

//Класс-обертка для хранения абсолютной позиции текущего найденного объекта в файле
public class AddressObjectFork {
    private ADDRESSOBJECTS.OBJECT object;
    private long offset;

    public ADDRESSOBJECTS.OBJECT getObject() {
        return object;
    }

    public long getOffset() {
        return offset;
    }

    public AddressObjectFork(ADDRESSOBJECTS.OBJECT object, long offset) {
        this.object = object;
        this.offset = offset;
    }
}
