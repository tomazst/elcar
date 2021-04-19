package org.elcar.app.rest;

import org.elcar.app.dto.SimpleBarChartDTO;
import org.elcar.app.service.RegionSalesService;
import org.elcar.app.view.CarType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChartResource {
    private final Logger log = LoggerFactory.getLogger(ChartResource.class);
    private final RegionSalesService regionSalesService;

    public ChartResource(RegionSalesService regionSalesService) {
        this.regionSalesService = regionSalesService;
    }

    @GetMapping("/bar-chart")
    public ResponseEntity<SimpleBarChartDTO> getBarChartData() {
        log.debug("Get request to getBarChartData");
        return ResponseEntity.ok().body(this.regionSalesService.getDataForSimpleBarChart(CarType.BATTERY_ELECTRIC));
    }
}
