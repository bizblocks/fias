package com.groupstp.fias.web.controller;


import com.groupstp.fias.service.NormService;
import groovy.util.logging.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController()
@RequestMapping("/v3/getting")
@Api(tags = "Получение информации")
@RequiredArgsConstructor
@Slf4j
public class GettingController {

    private final NormService normService;

    @PostMapping(value = "/normalize", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Нормализация полученных адресов")
    public ResponseEntity<?> normalize(@RequestBody() String[] addresses) {
        if (addresses.length > 1000) {
            return ResponseEntity.badRequest().body("Превышен лимит адресов в параметре");
        }
        Map<String, String> resultMap = new HashMap<>();

        for (String address : addresses) {
            try {
                resultMap.put(address, normService.normalize(address).getNormAddress());
            } catch (Exception ignored) {
                resultMap.put(address, null);
            }
        }

        return ResponseEntity.ok(resultMap);
    }
}
