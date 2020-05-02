package com.chex.db.place;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chex.model.place.Place;

public interface PlaceRepo extends JpaRepository<Place, Long> {


	Place findByIdplace(String idplace);
	
	@Query(value = "From Place where (latitude between :Slatitude and :Nlatitude)  and (longitude between :Wlongitude and :Elongitude)")
	List<Place> filterCoords(@Param("Nlatitude")double Nlatitude,
							@Param("Slatitude")double Slatitude,
							@Param("Wlongitude")double Wlongitude,
							@Param("Elongitude")double Elongitude
							);
	
	@Query(value = "From Place where idplace not like '%000'")
	List<Place> returnAllByLastLevelPlace();
											
	@Query(value = "From Place where idplace like '%0000000000000'")
	List<Place> getContinents();
	
	
	@Query(value = "from Place where (idplace like CONCAT(:continentid,'___0000000000')) and (idplace not like CONCAT(:continentid,'0000000000000'))")
	List<Place> getCountries(@Param("continentid") String continentid);
	
	@Query(value = "from Place where (idplace like CONCAT(:countryid,'___0000000')) and (idplace not like CONCAT(:countryid,'0000000000')) ")
	List<Place> getRegions(@Param("countryid") String countryid);
	
	@Query(value = "from Place where (idplace like CONCAT(:regionid,'___0000')) and (idplace not like CONCAT(:regionid,'0000000')) ")
	List<Place> getRegion(@Param("regionid") String regionid);
	
	Boolean existsByIdplace(String idplace);
}
