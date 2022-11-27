package com.groupstp.fias.screen.fiasentity;

import com.groupstp.fias.entity.FiasEntity;
import com.groupstp.fias.entity.House;
import com.haulmont.cuba.gui.screen.*;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.StandardEditor;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("fias_FiasEntity.edit")
@UiDescriptor("fias-entity-edit.xml")
@EditedEntityContainer("fiasEntityDc")
@LoadDataBeforeShow
public class FiasEntityEdit extends StandardEditor<FiasEntity> {
    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
//TODO rewrite to jmix
//        parentAdmField.addOpenAction().setEditScreen("fias$FiasEntity.edit");
//        parentMunField.addOpenAction().setEditScreen("fias$FiasEntity.edit");
//        childrenTable.setItemClickAction(new BaseAction("itemClickAction")
//                .withHandler(e -> screenBuilders.editor(childrenTable).withScreenId(this.getId()).build().show()));
//        childrenTable.setEnterPressAction(childrenTable.getItemClickAction());
//        housesTable.setItemClickAction(new BaseAction("houseClickAction")
//                .withHandler(e -> screenBuilders.editor(housesTable).build().show()));
//        housesTable.setEnterPressAction(housesTable.getItemClickAction());
    }

    @Inject
    private EntityPicker<FiasEntity> parentMunField;
    @Inject
    private EntityPicker<FiasEntity> parentAdmField;
    @Inject
    private Table<FiasEntity> childrenTable;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private Table<House> housesTable;


}