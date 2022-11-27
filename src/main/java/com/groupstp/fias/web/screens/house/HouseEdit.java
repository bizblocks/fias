package com.groupstp.fias.web.screens.house;

import com.groupstp.fias.entity.House;
import com.haulmont.cuba.gui.screen.*;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.StandardEditor;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("fias_House.edit")
@UiDescriptor("house-edit.xml")
@EditedEntityContainer("houseDc")
@LoadDataBeforeShow
public class HouseEdit extends StandardEditor<House> {
}