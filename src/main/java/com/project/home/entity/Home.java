package com.project.home.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Home  implements Serializable {

	@Id
	@GeneratedValue
	public Long id;
	
	@NotBlank(message = "عنوان نباید خالی باشد")
	public String title;
	
	@ManyToOne
	@JsonIgnore
	public Province province;
	
	@ManyToOne
	@JsonIgnore
	public City city;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonIgnore
	public User user;
	
	@ManyToMany
	@JoinTable(name = "home_category")
	public List<Category> category;
	
	public String zone;
	
	public String address;
	
	public String description;
	
	@OneToMany(mappedBy = "home")
	public List<HomePictures> homePictures;
	
	@Transient
	@JsonIgnore
	public List<MultipartFile> file;
	
	public Long totalPrice;
	
	@Transient
	public Long totalPriceFrom;
	
	@Transient
	public Long totalPriceTo;
	
	public Long pricePerMeter;
	
	public Long totalArea;
	
	@Transient
	public Long totalAreaFrom;
	
	@Transient
	public Long totalAreaTo;

	@NotNull(message = " متراژ خانه نباید خالی باشد")
	public Long houseArea;
	
	public Boolean interchangeable=false;
	
	public Long numberOfBathrooms;
	
	public Long numberOfToilets;
	
	public Long numberOfBedrooms;
	
	public String lighting;

	public String view;

	@Range(max = 6,min = 1,message = "تعداد سهم برای فروش حداقل 1 و حداکثر 6 سهم می باشد")
	public Long countOfPortions;
	
	public Long countOfParking;

	@NotNull(message = "سال ساخت خانه نباید خالی باشد")
	public Long yearOfConstruction;
	
	@Transient
	public Long yearOfConstructionFrom;
	
	@Transient
	public Long yearOfConstructionTo;
	
	public boolean enabled;

	public String facilities;
	
	@CreationTimestamp
	@Column(name = "created_at",updatable = false)
	public LocalDateTime createdAt;
	

	@UpdateTimestamp
	@Column(name = "updated_at")
	public LocalDateTime updatedAt;


	public Home(Long id, @NotBlank String title, Province province, City city, User user,
			@NotEmpty List<Category> category, String zone, String address, String description, List<HomePictures> homePictures,
			List<MultipartFile> file, Long totalPrice, Long pricePerMeter, Long totalArea, Long houseArea,
			Boolean interchangeable, Long numberOfBathrooms, Long numberOfToilets, Long numberOfBedrooms,
			String lighting, String view, @Max(6) @Min(1) Long countOfPortions, Long countOfParking,
				Long yearOfConstruction, String facilities, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.title = title;
		this.province = province;
		this.city = city;
		this.user = user;
		this.category = category;
		this.zone = zone;
		this.address = address;
		this.description = description;
		this.homePictures = homePictures;
		this.file = file;
		this.totalPrice = totalPrice;
		this.pricePerMeter = pricePerMeter;
		this.totalArea = totalArea;
		this.houseArea = houseArea;
		this.interchangeable = interchangeable;
		this.numberOfBathrooms = numberOfBathrooms;
		this.numberOfToilets = numberOfToilets;
		this.numberOfBedrooms = numberOfBedrooms;
		this.lighting = lighting;
		this.view = view;
		this.countOfPortions = countOfPortions;
		this.countOfParking = countOfParking;
		this.yearOfConstruction = yearOfConstruction;
		this.facilities = facilities;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


	public Home(@NotBlank String title, Province province, City city, User user, @NotEmpty List<Category> category,
			String zone, String address, String description, List<HomePictures> homePictures, List<MultipartFile> file, Long totalPrice,
			Long pricePerMeter, Long totalArea, Long houseArea, Boolean interchangeable, Long numberOfBathrooms,
				Long numberOfToilets, Long numberOfBedrooms, String lighting, String view,
			@Max(6) @Min(1) Long countOfPortions, Long countOfParking, Long yearOfConstruction, String facilities,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.title = title;
		this.province = province;
		this.city = city;
		this.user = user;
		this.category = category;
		this.zone = zone;
		this.address = address;
		this.description = description;
		this.homePictures = homePictures;
		this.file = file;
		this.totalPrice = totalPrice;
		this.pricePerMeter = pricePerMeter;
		this.totalArea = totalArea;
		this.houseArea = houseArea;
		this.interchangeable = interchangeable;
		this.numberOfBathrooms = numberOfBathrooms;
		this.numberOfToilets = numberOfToilets;
		this.numberOfBedrooms = numberOfBedrooms;
		this.lighting = lighting;
		this.view = view;
		this.countOfPortions = countOfPortions;
		this.countOfParking = countOfParking;
		this.yearOfConstruction = yearOfConstruction;
		this.facilities = facilities;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	
	
	

	public Home(@NotBlank String title, Province province, City city, User user, String zone, String address,
			String description, Long totalPrice, Long pricePerMeter, Long totalArea, Long houseArea,
			Boolean interchangeable, Long numberOfBathrooms, Long numberOfToilets, Long numberOfBedrooms,
			String lighting, String view, @Max(6) @Min(1) Long countOfPortions, Long countOfParking,
				Long yearOfConstruction, String facilities,@NotEmpty List<Category> category) {
		super();
		this.title = title;
		this.category = category;
		this.province = province;
		this.city = city;
		this.user = user;
		this.zone = zone;
		this.address = address;
		this.description = description;
		this.totalPrice = totalPrice;
		this.pricePerMeter = pricePerMeter;
		this.totalArea = totalArea;
		this.houseArea = houseArea;
		this.interchangeable = interchangeable;
		this.numberOfBathrooms = numberOfBathrooms;
		this.numberOfToilets = numberOfToilets;
		this.numberOfBedrooms = numberOfBedrooms;
		this.lighting = lighting;
		this.view = view;
		this.countOfPortions = countOfPortions;
		this.countOfParking = countOfParking;
		this.yearOfConstruction = yearOfConstruction;
		this.facilities = facilities;
	}


	public Home() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Province getProvince() {
		return province;
	}


	public void setProvince(Province province) {
		this.province = province;
	}


	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<Category> getCategory() {
		return category;
	}


	public void setCategory(List<Category> category) {
		this.category = category;
	}


	public String getZone() {
		return zone;
	}


	public void setZone(String zone) {
		this.zone = zone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<HomePictures>  getHomePictures() {
		return homePictures;
	}


	public void setHomePictures(List<HomePictures> homePictures) {
		this.homePictures = homePictures;
	}


	public List<MultipartFile> getFile() {
		return file;
	}


	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}


	public Long getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Long getPricePerMeter() {
		return pricePerMeter;
	}


	public void setPricePerMeter(Long pricePerMeter) {
		this.pricePerMeter = pricePerMeter;
	}


	public Long getTotalArea() {
		return totalArea;
	}


	public void setTotalArea(Long totalArea) {
		this.totalArea = totalArea;
	}


	public Long getHouseArea() {
		return houseArea;
	}


	public void setHouseArea(Long houseArea) {
		this.houseArea = houseArea;
	}


	public Boolean getInterchangeable() {
		return interchangeable;
	}


	public void setInterchangeable(Boolean interchangeable) {
		this.interchangeable = interchangeable;
	}


	public Long getNumberOfBathrooms() {
		return numberOfBathrooms;
	}


	public void setNumberOfBathrooms(Long numberOfBathrooms) {
		this.numberOfBathrooms = numberOfBathrooms;
	}


	public Long getNumberOfToilets() {
		return numberOfToilets;
	}


	public void setNumberOfToilets(Long numberOfToilets) {
		this.numberOfToilets = numberOfToilets;
	}


	public Long getNumberOfBedrooms() {
		return numberOfBedrooms;
	}


	public void setNumberOfBedrooms(Long numberOfBedrooms) {
		this.numberOfBedrooms = numberOfBedrooms;
	}


	public String getLighting() {
		return lighting;
	}


	public void setLighting(String lighting) {
		this.lighting = lighting;
	}


	public String getView() {
		return view;
	}


	public void setView(String view) {
		this.view = view;
	}


	public Long getCountOfPortions() {
		return countOfPortions;
	}


	public void setCountOfPortions(Long countOfPortions) {
		this.countOfPortions = countOfPortions;
	}


	public Long getCountOfParking() {
		return countOfParking;
	}


	public void setCountOfParking(Long countOfParking) {
		this.countOfParking = countOfParking;
	}


	public Long getYearOfConstruction() {
		return yearOfConstruction;
	}


	public void setYearOfConstruction(Long yearOfConstruction) {
		this.yearOfConstruction = yearOfConstruction;
	}


	public String getFacilities() {
		return facilities;
	}


	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	public Long getTotalPriceFrom() {
		return totalPriceFrom;
	}


	public void setTotalPriceFrom(Long totalPriceFrom) {
		this.totalPriceFrom = totalPriceFrom;
	}


	public Long getTotalPriceTo() {
		return totalPriceTo;
	}


	public void setTotalPriceTo(Long totalPriceTo) {
		this.totalPriceTo = totalPriceTo;
	}


	public Long getTotalAreaFrom() {
		return totalAreaFrom;
	}


	public void setTotalAreaFrom(Long totalAreaFrom) {
		this.totalAreaFrom = totalAreaFrom;
	}


	public Long getTotalAreaTo() {
		return totalAreaTo;
	}


	public void setTotalAreaTo(Long totalAreaTo) {
		this.totalAreaTo = totalAreaTo;
	}


	public Long getYearOfConstructionFrom() {
		return yearOfConstructionFrom;
	}


	public void setYearOfConstructionFrom(Long yearOfConstructionFrom) {
		this.yearOfConstructionFrom = yearOfConstructionFrom;
	}


	public Long getYearOfConstructionTo() {
		return yearOfConstructionTo;
	}


	public void setYearOfConstructionTo(Long yearOfConstructionTo) {
		this.yearOfConstructionTo = yearOfConstructionTo;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
