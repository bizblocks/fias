package com.groupstp.fias.web.fiasentity;

import com.groupstp.fias.entity.FiasEntity;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.RadioButtonGroup;
import com.haulmont.cuba.gui.components.TreeTable;
import com.haulmont.cuba.gui.data.GroupDatasource;
import com.haulmont.cuba.gui.data.HierarchicalDatasource;
import com.haulmont.cuba.gui.screen.Subscribe;

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

    @Subscribe
    public void onInit(InitEvent event) {
        List<String> hList = new ArrayList<>(Arrays.asList("Административное", "Муниципальное", "Без иерархии"));
        hierarchySwitch.setOptionsList(hList);
        hierarchySwitch.setValue("Административное");
    }

    @Subscribe("hierarchySwitch")
    public void onHierarchySwitchValueChange(HasValue.ValueChangeEvent event) {
        if(event.getValue().equals("Административное")) {
            fiasEntitiesTable.setDatasource(fiasEntitiesAdmDs);
        } else if(event.getValue().equals("Муниципальное")) {
            fiasEntitiesTable.setDatasource(fiasEntitiesMunDs);
        } else {
            fiasEntitiesTable.setDatasource(fiasEntitiesDs);
        }
        fiasEntitiesTable.getDatasource().refresh();
    }
    
    
}