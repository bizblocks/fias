package com.groupstp.fias.web.screens.address;

import com.groupstp.fias.service.NormService;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import com.groupstp.fias.entity.Address;

import javax.inject.Inject;

@UiController("fias_Address.browse")
@UiDescriptor("address-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class AddressBrowse extends MasterDetailScreen<Address> {
    @Inject
    private CollectionContainer<Address> addressesDc;
    @Inject
    private GroupTable<Address> table;
    @Inject
    private NormService normService;

    @Subscribe("normalizeBtn")
    public void onNormalizeBtnClick(Button.ClickEvent event) {
        table.getSelected().forEach(a->normService.normalize(a.getSrcAddress()));
    }
    
}