package com.groupstp.fias.web.fiasentity;

import com.groupstp.fias.entity.FiasEntity;
import com.groupstp.fias.entity.House;
import com.groupstp.fias.web.screens.house.HouseEdit;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.screen.StandardEditor;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@UiController("fias_FiasEntity.edit")
@UiDescriptor("fias-entity-edit.xml")
public class FiasEntityEdit extends StandardEditor<FiasEntity> {
    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        parentAdmField.addOpenAction().setEditScreen("fias$FiasEntity.edit");
        parentMunField.addOpenAction().setEditScreen("fias$FiasEntity.edit");
        childrenTable.setItemClickAction(new BaseAction("itemClickAction")
                .withHandler(e -> screenBuilders.editor(childrenTable).withScreenId(this.getId()).build().show()));
        childrenTable.setEnterPressAction(childrenTable.getItemClickAction());
        housesTable.setItemClickAction(new BaseAction("houseClickAction")
                .withHandler(e -> screenBuilders.editor(housesTable).build().show()));
        housesTable.setEnterPressAction(housesTable.getItemClickAction());
    }

    @Named("fieldGroup.parentAdm")
    private PickerField<FiasEntity> parentAdmField;
    @Named("fieldGroup.parentMun")
    private PickerField<FiasEntity> parentMunField;
    @Inject
    private Table<FiasEntity> childrenTable;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private Table<House> housesTable;


}