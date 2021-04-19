package org.elcar.app.service;

import org.elcar.app.client.NonceRequest;
import org.elcar.app.domain.RegionSales;
import org.elcar.app.dto.NonceDTO;
import org.elcar.app.dto.SeriesDTO;
import org.elcar.app.dto.SimpleBarChartDTO;
import org.elcar.app.repository.RegionSalesRepository;
import org.elcar.app.view.CarType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link RegionSales}.
 */
@Service
public class RegionSalesService {

    private final Logger log = LoggerFactory.getLogger(RegionSalesService.class);

    private final RegionSalesRepository regionSalesRepository;
    private final NonceRequest nonceClient;

    public RegionSalesService(RegionSalesRepository regionSalesRepository,
                              NonceRequest nonceClient) {
        this.regionSalesRepository = regionSalesRepository;
        this.nonceClient = nonceClient;
    }

    /**
     * Save a regionSales.
     *
     * @param regionSales the entity to save.
     * @return the persisted entity.
     */
    public RegionSales save(RegionSales regionSales) {
        log.info("Request to save RegionSales : {}", regionSales);
        return regionSalesRepository.save(regionSales);
    }

    /**
     * Partially update a regionSales.
     *
     * @param regionSales the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RegionSales> partialUpdate(RegionSales regionSales) {
        log.debug("Request to partially update RegionSales : {}", regionSales);

        return regionSalesRepository
            .findById(regionSales.getId())
            .map(
                existingRegionSales -> {
                    if (regionSales.getRegion() != null) {
                        existingRegionSales.setRegion(regionSales.getRegion());
                    }
                    if (regionSales.getSale() != null) {
                        existingRegionSales.setSale(regionSales.getSale());
                    }
                    if (regionSales.getYear() != null) {
                        existingRegionSales.setYear(regionSales.getYear());
                    }
                    if (regionSales.getCarType() != null) {
                        existingRegionSales.setCarType(regionSales.getCarType());
                    }
                    if (regionSales.getComment() != null) {
                        existingRegionSales.setComment(regionSales.getComment());
                    }

                    return existingRegionSales;
                }
            )
            .map(regionSalesRepository::save);
    }

    public String getSomeNonce() {
        List<NonceDTO> nonceList = nonceClient.getSomeNonce();
        if (nonceList != null && nonceList.size() > 0) {
            return nonceList.get(0).getBody();
        }
        return "Couldn't get any nonce. Try again.";
    }

    /**
     * Get all the regionSales.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<RegionSales> findAll(Pageable pageable) {
        log.debug("Request to get all RegionSales");
        return regionSalesRepository.findAll(pageable);
    }

    /**
     * Get all the regionSales.
     *
     * @return the list of entities.
     */
    public List<RegionSales> findAll() {
        log.debug("Request to get all RegionSales");
        return regionSalesRepository.findAll();
    }

    /**
     * Get one regionSales by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<RegionSales> findOne(String id) {
        log.debug("Request to get RegionSales : {}", id);
        return regionSalesRepository.findById(id);
    }

    /**
     * Delete the regionSales by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete RegionSales : {}", id);
        regionSalesRepository.deleteById(id);
    }

    public SimpleBarChartDTO getDataForSimpleBarChart(CarType carType) {
        SimpleBarChartDTO simpleBarChartDTO = new SimpleBarChartDTO();


        // filter carType - We can show this chart only for one carType BATTERY_ELECTRIC
        List<RegionSales> regionSalesList = regionSalesRepository.findAll().stream()
                .filter(r -> r.getCarType().equals(carType.name())).collect(Collectors.toList());

        // Add region names as categories
        Set<String> categories = regionSalesList.stream().map(regionSales -> regionSales.getRegion()).collect(Collectors.toSet());
        simpleBarChartDTO.setCategories(new TreeSet<>(categories));

        // Add years to the series
        // First add year
        regionSalesList.forEach(regionSales -> simpleBarChartDTO.getSeries()
                .add(new SeriesDTO(String.valueOf(regionSales.getYear()))));

        // Now add sales in sorted region order for each year
        simpleBarChartDTO.getSeries().forEach(
                seriesDTO -> simpleBarChartDTO.getCategories().forEach(
                        region -> {
                            List<Long> data = regionSalesList.stream()
                                    .filter(regionSales ->
                                            {
                                                boolean equal = regionSales.getRegion().equals(region)
                                                        && String.valueOf(regionSales.getYear()).equals(seriesDTO.getName());
                                                return equal;
                                            }
                                    ).map(regionSales -> regionSales.getSale()).collect(Collectors.toList());
                            seriesDTO.getData().addAll(data);
                        })
                );
        return simpleBarChartDTO;
    }
}
