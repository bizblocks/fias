package com.groupstp.fias.web.screens.address;

import com.groupstp.fias.entity.FiasEntity;
import com.groupstp.fias.entity.House;
import com.groupstp.fias.service.NormService;
import com.haulmont.cuba.gui.screen.*;
import com.groupstp.fias.entity.Address;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.BaseAction;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.MasterDetailScreen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("fias_Address.browse")
@UiDescriptor("address-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class AddressBrowse extends MasterDetailScreen<Address> {
    @Inject
    private GroupTable<Address> table;
    @Inject
    private NormService normService;
    @Inject
    private Table<House> houseTable;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private Table<FiasEntity> fiasEntityTable;

    @Subscribe("normalizeBtn")
    public void onNormalizeBtnClick(Button.ClickEvent event) {
        table.getSelected().forEach(a -> normService.normalize(a.getSrcAddress()));
    }

    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        houseTable.setItemClickAction(new BaseAction("houseClickAction")
                .withHandler(e -> screenBuilders.editor(houseTable).build().show()));
        fiasEntityTable.setItemClickAction(new BaseAction("fiasClickAction")
                .withHandler(e -> screenBuilders.editor(fiasEntityTable).withScreenId("fias_FiasEntity.edit").build().show()));
    }

}