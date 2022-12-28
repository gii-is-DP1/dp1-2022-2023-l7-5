package org.springframework.samples.petclinic.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends  CrudRepository<User, String>{
	@Query("Select u FROM User u")
    Page<User> getAllPaginated(Pageable page);
}
