package com.FlyNova.location_service.Repository;

import com.FlyNova.location_service.Model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City,Long> {
    boolean existsByCityCode(String cityCode);

    boolean existsByCityCodeAndIdNot(String cityCode,Long id);
    Page<City> findByCountryCodeIgnoreCase(String countryCode, Pageable pageable);
    @Query("""  
        select c from City c
        where lower(c.name) like lower(concat('%',:keyword,'%'))
        or lower(c.cityCode) like lower(concat('%',:keyword,'%'))
        or lower(c.CountryCode) like lower(concat('%',:keyword,'%'))
         or lower(c.regionCode) like lower(concat('%',:keyword,'%'))
         or lower(c.timeZoneId) like lower(concat('%',:keyword,'%'))
""")
    Page<City> searchByKeyword(String keyword,Pageable pageable);

}
