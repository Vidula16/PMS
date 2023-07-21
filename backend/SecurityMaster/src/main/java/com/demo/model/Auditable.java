package com.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {
	@CreatedBy
	@Column(updatable=false)
protected String createdBy;
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(updatable=false)
protected Date createdDate;
	@LastModifiedBy
protected String lastModifiedBy;
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
protected Date lastModifiedDate;
}
