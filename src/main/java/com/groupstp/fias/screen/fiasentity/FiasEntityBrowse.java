package com.groupstp.fias.screen.fiasentity;

import com.groupstp.fias.entity.FiasEntity;
import io.jmix.ui.action.list.EditAction;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.RadioButtonGroup;
import io.jmix.ui.component.TreeTable;
import io.jmix.ui.component.data.table.ContainerTreeTableItems;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UiController("fias_FiasEntity.browse")
@UiDescriptor("fias-entity-browse.xml")
@LookupComponent("fiasEntitiesTable")
public class FiasEntityBrowse extends StandardLookup<FiasEntity> {
    @Inject
    private RadioButtonGroup hierarchySwitch;
    @Inject
    private TreeTable<FiasEntity> fiasEntitiesTable;

    @Subscribe
    public void onInit(InitEvent event) {
        List<String> hList = new ArrayList<>(Arrays.asList("Административное", "Муниципальное", "Без иерархии"));
        hierarchySwitch.setOptionsList(hList);
        hierarchySwitch.setValue("Административное");
        ((EditAction)fiasEntitiesTable.getAction("edit")).setScreenId("fias_FiasEntity.edit");
    }

    @Subscribe("hierarchySwitch")
    public void onHierarchySwitchValueChange(HasValue.ValueChangeEvent event) {
        ContainerTreeTableItems<FiasEntity> items = (ContainerTreeTableItems<FiasEntity>)fiasEntitiesTable.getItems();
        ContainerTreeTableItems<FiasEntity> newItems;
        if (event.getValue().equals("Административное")) {
            newItems = new ContainerTreeTableItems<>(items.getContainer(), "parentAdm", true);
        } else if (event.getValue().equals("Муниципальное")) {
            newItems = new ContainerTreeTableItems<>(items.getContainer(), "parentMun", true);
        } else {
            newItems = new ContainerTreeTableItems<>(items.getContainer(), null, true);
        }
        fiasEntitiesTable.setItems(newItems);
    }


}