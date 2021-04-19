package org.elcar.app.view;

import org.elcar.app.client.NonceRequest;
import org.elcar.app.domain.RegionSales;
import org.elcar.app.dto.NonceDTO;
import org.elcar.app.service.RegionSalesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Component
@RequestScope
public class RegionSaleAction implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(RegionSaleAction.class);

    private final RegionSaleForm regionSaleForm;
    private final RegionSalesService regionSalesService;

    private DataModel<RegionSales> regionSales;
    private List<RegionSales> regionSalesList;

    public RegionSaleAction(RegionSaleForm regionSaleForm,
                            RegionSalesService regionSalesService) {
        this.regionSaleForm = regionSaleForm;
        this.regionSalesService = regionSalesService;
        this.refreshRegionList();
    }

    /**
     * Save or update the region
     */
    public void save() {
        regionSalesService.save(this.toDomain(this.regionSaleForm));
        this.refreshRegionList();
    }

    /**
     * Needed to list all regions from the database.
     * @return
     */
    public List<RegionSales> getRegionSaleList() {
        return this.regionSalesList;
    }

    /**
     * Needed for special case in jsf tabledata component - to get row index.
     * @return
     */
    public DataModel<RegionSales> getRegionSales() {
        return this.regionSales;
    }

    /**
     * Get requested region.
     * @param event
     */
    public void findRegion(AjaxBehaviorEvent event) {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String regionId = params.get("region-id");
        RegionSales regionSales = this.regionSalesService.findOne(regionId).orElse(new RegionSales());
        this.toForm(regionSales);
    }

    /**
     * Delete region
     * @param event
     */
    public void deleteRegion(AjaxBehaviorEvent event) {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String regionId = params.get("region-id");
        this.regionSalesService.delete(regionId);
        this.refreshRegionList();
        log.info("Deleted region: " + regionId);
    }

    /**
     * Needed to test some funny rest service
     */
    public void setSomeNonce() {
        this.regionSaleForm.setNonce(this.regionSalesService.getSomeNonce());
    }

    private void toForm(RegionSales regionSales) {
        this.regionSaleForm.setId(regionSales.getId() == null ? "" : regionSales.getId());
        this.regionSaleForm.setCountry(regionSales.getRegion() == null ? "" : regionSales.getRegion());
        this.regionSaleForm.setSale(regionSales.getSale() == null ? 0 : regionSales.getSale());
        this.regionSaleForm.setYear(regionSales.getYear() == null ? 2020 : regionSales.getYear());
        this.regionSaleForm.setCarType(CarType.valueOf(regionSales.getCarType() == null ? "NONE" : regionSales.getCarType()));
        this.regionSaleForm.setNonce(regionSales.getComment() == null ? "" : regionSales.getComment());
    }

    private RegionSales toDomain(RegionSaleForm regionSaleForm) {
        RegionSales region = new RegionSales();
        region.setId(regionSaleForm.getId());
        region.setRegion(regionSaleForm.getCountry());
        region.setSale(regionSaleForm.getSale());
        region.setYear(regionSaleForm.getYear());
        region.setCarType(regionSaleForm.getCarType().name());
        region.setComment(regionSaleForm.getNonce());
        return region;
    }

    private void refreshRegionList() {
        this.regionSalesList = this.regionSalesService.findAll();
        RegionSales[] regionSaleArray = new RegionSales[this.regionSalesList.size()];
        this.getRegionSaleList().toArray(regionSaleArray);
        this.regionSales = new ArrayDataModel<>(regionSaleArray);
    }
}
