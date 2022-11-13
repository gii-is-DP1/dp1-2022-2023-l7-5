package org.springframework.samples.petclinic.profile;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ProfileRepository extends CrudRepository<Profile, Integer>{
	List<Profile> findAll();
}
