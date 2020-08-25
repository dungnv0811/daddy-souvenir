package com.dvnguyen.daddysouvenir.domain;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "SALES")
@Builder
public class Sale {
	@Id
	@Column(name = "ID")
	private long id;
	@Column(name = "ITEM")
	private String item;
	@Column(name = "QUANTITY")
	private int quantity;
	@Column(name = "AMOUNT")
	private float amount;
}