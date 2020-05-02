package com.chex.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chex.model.Invitation;

@Repository
public interface InvitationRepo extends JpaRepository<Invitation, Long> {

	List<Invitation> findByUserid(Long user_id);
	
	@Query(value = "select new java.lang.Boolean(count(*) > 0 ) from Invitation where userid=:userid and inviters=:inviters")
	Boolean isUserIdAndInviters(@Param("userid") Long user_id, @Param("inviters") Long inviters);
	
	@Query(value = "from Invitation where userid=:userid and inviters=:inviters")
	Invitation findByUserIdAndInviters(@Param("userid") Long user_id, @Param("inviters") Long inviters);
}
