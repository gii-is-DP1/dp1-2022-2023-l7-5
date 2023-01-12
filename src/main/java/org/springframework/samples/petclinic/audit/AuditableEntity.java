package org.springframework.samples.petclinic.audit;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity extends BaseEntity {
	@CreatedBy
	public String creator;
	@CreatedDate
	public LocalDateTime createdDate;
	@LastModifiedBy
	public String modifier;
	@LastModifiedDate
	public LocalDateTime lastModifiedDate;
}
