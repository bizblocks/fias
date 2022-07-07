package com.groupstp.fias.web.screens.house;

import com.groupstp.fias.entity.House;
import com.haulmont.cuba.gui.screen.*;

@UiController("fias_House.edit")
@UiDescriptor("house-edit.xml")
@EditedEntityContainer("houseDc")
@LoadDataBeforeShow
public class HouseEdit extends StandardEditor<House> {
}