package org.springframework.samples.petclinic.profile;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer>{
	List<Profile> findAll();
	@Query("SELECT p FROM Profile p WHERE p.user.username = :username")
	Profile getProfileByUser(@Param("username") String username);
}
