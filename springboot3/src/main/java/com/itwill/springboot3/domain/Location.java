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
@Table(name = "LOCATIONS")
public class Location {
	
	@Id
	@Column(name = "LOCATION_ID")
	private Integer id;
	
	private String streetAddress; // 컬럼 이름: street_address
	
	private String postalCode; // 컬럼 이름: postal_code
	
	private String city;
	
	private String stateProvince; // 컬럼 이름: state_province
	
//	private String countryId;
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTRY_ID") // LOCATIONS 테이블에서 COUNTRIES 테이블과 join하는 컬럼 이름.
	private Country country;
	
//	@ToString.Exclude
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
//	private List<Department> departments;
	
}
