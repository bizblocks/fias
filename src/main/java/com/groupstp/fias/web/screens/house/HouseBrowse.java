package com.groupstp.fias.web.screens.house;

import com.groupstp.fias.entity.House;
import com.haulmont.cuba.gui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.StandardLookup;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("fias_House.browse")
@UiDescriptor("house-browse.xml")
@LookupComponent("housesTable")
@LoadDataBeforeShow
public class HouseBrowse extends StandardLookup<House> {
}