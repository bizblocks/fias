package com.groupstp.fias.web.screens.address;

import com.groupstp.fias.entity.House;
import com.groupstp.fias.service.NormService;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.screen.*;
import com.groupstp.fias.entity.Address;

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

    @Subscribe("normalizeBtn")
    public void onNormalizeBtnClick(Button.ClickEvent event) {
        table.getSelected().forEach(a->normService.normalize(a.getSrcAddress()));
    }

    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        houseTable.setItemClickAction(new BaseAction("houseClickAction")
                .withHandler(e -> screenBuilders.editor(houseTable).build().show()));
    }
    
}