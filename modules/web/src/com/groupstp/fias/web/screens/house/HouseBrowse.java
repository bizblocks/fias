package com.groupstp.fias.web.screens.house;

import com.groupstp.fias.entity.House;
import com.haulmont.cuba.gui.screen.*;

@UiController("fias_House.browse")
@UiDescriptor("house-browse.xml")
@LookupComponent("housesTable")
@LoadDataBeforeShow
public class HouseBrowse extends StandardLookup<House> {
}