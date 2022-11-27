package com.groupstp.fias.org.meridor.fias.enums;

public enum AddressLevel {

    REGION("1"),
    ADMINISTRATIVE_REGION("2"),
    MUNICIPAL_REGION("3"),
    SETTLEMENT("4"),
    CITY("5"),
    LOCALITY("6"),
    PLANNING_STRUCTURE("7"),
    STREET("8"),
    STEAD("9"),
    HOUSE("10"),
    SUPPLEMENTARY_TERRITORY("90"),
    CHILD_SUPPLEMENTARY_TERRITORY("91");

    private final String addressLevel;

    AddressLevel(String addressLevel) {
        this.addressLevel = addressLevel;
    }

    public String getAddressLevel() {
        return addressLevel;
    }
}
