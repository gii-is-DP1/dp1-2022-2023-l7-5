package org.springframework.samples.petclinic.marker;

import javax.persistence.Entity;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Marker extends BaseEntity{
	
	private String name;
	private Integer score;

}
