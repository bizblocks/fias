package com.groupstp.fias.web.fiasentity;

import com.groupstp.fias.entity.FiasEntity;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.HierarchicalDatasource;
import io.jmix.ui.component.Filter;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.RadioButtonGroup;
import io.jmix.ui.component.TreeTable;
import io.jmix.ui.screen.Subscribe;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class FiasEntityBrowse extends AbstractLookup {
    @Inject
    private RadioButtonGroup hierarchySwitch;
    @Inject
    private TreeTable<FiasEntity> fiasEntitiesTable;
    @Inject
    private HierarchicalDatasource<FiasEntity, UUID> fiasEntitiesAdmDs;
    @Inject
    private HierarchicalDatasource<FiasEntity, UUID> fiasEntitiesMunDs;
    @Inject
    private HierarchicalDatasource<FiasEntity, UUID> fiasEntitiesDs;
    @Inject
    private Filter filter;

    @Subscribe
    public void onInit(InitEvent event) {
        List<String> hList = new ArrayList<>(Arrays.asList("Административное", "Муниципальное", "Без иерархии"));
        hierarchySwitch.setOptionsList(hList);
        hierarchySwitch.setValue("Административное");
        ((EditAction) fiasEntitiesTable.getAction("edit")).setWindowId("fias_FiasEntity.edit");
    }

    @Subscribe("hierarchySwitch")
    public void onHierarchySwitchValueChange(HasValue.ValueChangeEvent event) {
//TDOD rewrite to jmix
        //        if (event.getValue().equals("Административное")) {
//            fiasEntitiesTable.setDatasource(fiasEntitiesAdmDs);
//        } else if (event.getValue().equals("Муниципальное")) {
//            fiasEntitiesTable.setDatasource(fiasEntitiesMunDs);
//        } else {
//            fiasEntitiesTable.setDatasource(fiasEntitiesDs);
//        }
//        fiasEntitiesTable.getDatasource().refresh();
//        filter.setDatasource(fiasEntitiesTable.getDatasource());
    }


}