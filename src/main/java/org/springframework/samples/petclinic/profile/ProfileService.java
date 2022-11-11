package org.springframework.samples.petclinic.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
	ProfileRepository repo;
	
	@Autowired ProfileService(ProfileRepository repo){
		this.repo= repo;
	}
	
	List<Profile> getProfiles(){
		
		return repo.findAll();
	}
	
	public Profile getProfileById(Integer id) {
		
		return repo.findById(id).get();
	}
	
	public void deleteProfileById(Integer id) {
		
		repo.deleteById(id);
	}
	
	public void save(Profile profile) {
		
		repo.save(profile);
	}
}
