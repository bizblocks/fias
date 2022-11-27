package com.groupstp.fias.web.screens;

import com.groupstp.fias.entity.Address;
import com.groupstp.fias.service.NormService;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("fias_NormalizeAddress")
@UiDescriptor("normalize-address.xml")
public class NormalizeAddress extends Screen {
    @Inject
    private NormService normService;
    @Inject
    private TextField<String> srcAddress;
    @Inject
    private TextField<String> normAddress;

    @Subscribe("normalize")
    public void onNormalizeClick(Button.ClickEvent event) {
        Address address = normService.normalize(srcAddress.getRawValue());
        normAddress.setValue(address.getNormAddress());
    }
}