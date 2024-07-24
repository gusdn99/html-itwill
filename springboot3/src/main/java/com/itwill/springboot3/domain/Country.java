package com.itwill.springboot3.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor @Getter @ToString @EqualsAndHashCode
@Entity
@Table(name = "COUNTRIES")
public class Country {
	
	@Id
	@Column(name = "COUNTRY_ID")
	private String id;
	
	private String countryName; // 컬럼 이름: country_name
	
//	private Integer regionId;
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REGION_ID") // COUNTRIES 테이블에서 REGIONS 테이블과 join하는 컬럼 이름.
	private Region region;
	
//	@ToString.Exclude
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
//	private List<Location> locations;
	
}
