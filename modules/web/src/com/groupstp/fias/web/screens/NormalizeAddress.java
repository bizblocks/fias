package com.groupstp.fias.web.screens;

import com.groupstp.fias.service.NormService;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("fias_NormalizeAddress")
@UiDescriptor("normalize-address.xml")
public class NormalizeAddress extends Screen {
    @Inject
    private NormService normService;
    @Inject
    private TextField<String> srcAddress;

    @Subscribe("normalize")
    public void onNormalizeClick(Button.ClickEvent event) {
        normService.normalize(srcAddress.getRawValue());
    }
}