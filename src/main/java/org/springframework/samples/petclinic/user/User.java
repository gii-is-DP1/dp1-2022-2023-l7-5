package org.springframework.samples.petclinic.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.samples.petclinic.profile.Profile;
import org.springframework.samples.petclinic.tile.Tile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User{
	@Id
	@NotNull
	@NotBlank
	String username;
	
	@NotNull
	@Email
	@NotBlank
	private String email;
	
	@NotNull
	@NotBlank
	String password;
	
	boolean enabled;
	
	@OneToMany
	private List<Tile> tiles;
	
	@OneToOne
	private Profile profile;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;
	
	@CreatedBy
	private String creator;
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	@LastModifiedBy
	private String modifier;
	
	@LastModifiedDate
	private LocalDateTime lastModifiedDate;
}
