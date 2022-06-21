package com.groupstp.fias.web.fiasentity;

import com.groupstp.fias.entity.FiasEntity;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.PickerField;

import javax.inject.Named;
import java.util.Map;

public class FiasEntityEdit extends AbstractEditor<FiasEntity> {
    @Named("fieldGroup.parentAdm")
    private PickerField<FiasEntity> parentAdmField;
    @Named("fieldGroup.parentMun")
    private PickerField<FiasEntity> parentMunField;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        parentAdmField.addOpenAction().setEditScreen("fias$FiasEntity.edit");
        parentMunField.addOpenAction().setEditScreen("fias$FiasEntity.edit");
    }
}