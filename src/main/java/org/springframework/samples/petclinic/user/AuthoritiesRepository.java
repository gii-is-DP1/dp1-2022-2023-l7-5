package org.springframework.samples.petclinic.user;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface AuthoritiesRepository extends  CrudRepository<Authorities, String>{
	@Query("SELECT auth FROM Authorities auth WHERE auth.user = :username")
    Authorities getAuthByUsername(@Param("username") String username);
}
