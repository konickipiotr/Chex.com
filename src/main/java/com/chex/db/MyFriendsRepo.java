package com.chex.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chex.model.MyFriends;

@Repository
public interface MyFriendsRepo extends JpaRepository<MyFriends, Long> {

	@Query(value = "select new java.lang.Boolean(count(*) > 0 ) from MyFriends where userid=:userid and userFriendsId=:userFriendsId")
	Boolean isMyFriend(@Param("userid") Long user_id, @Param("userFriendsId") String friendId);
			
}
