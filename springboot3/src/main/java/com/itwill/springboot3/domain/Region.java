package com.itwill.springboot3.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor @Getter @ToString @EqualsAndHashCode
@Entity
@Table(name = "REGIONS")
public class Region {
	
	@Id
	@Column(name = "REGION_ID")
	private Integer id;
	
	private String regionName; // 컬럼 이름: region_name
	
//	@ToString.Exclude
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
//	private List<Country> countries;
}
