package org.elcar.app.repository;

import org.elcar.app.domain.RegionSales;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the RegionSales entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegionSalesRepository extends MongoRepository<RegionSales, String> {}
