package com.groupstp.fias.web.fiasentity;

import com.groupstp.fias.entity.FiasEntity;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.screen.UiController;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@UiController("fias_FiasEntity.edit")
public class FiasEntityEdit extends AbstractEditor<FiasEntity> {
    @Named("fieldGroup.parentAdm")
    private PickerField<FiasEntity> parentAdmField;
    @Named("fieldGroup.parentMun")
    private PickerField<FiasEntity> parentMunField;
    @Inject
    private Table<FiasEntity> childrenTable;
    @Inject
    private ScreenBuilders screenBuilders;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        parentAdmField.addOpenAction().setEditScreen("fias$FiasEntity.edit");
        parentMunField.addOpenAction().setEditScreen("fias$FiasEntity.edit");
        childrenTable.setItemClickAction(new BaseAction("itemClickAction")
                .withHandler(event -> screenBuilders.editor(childrenTable).withScreenId(this.getId()).build().show()));
        childrenTable.setEnterPressAction(childrenTable.getItemClickAction());
    }


}