package dev.sharanggupta.batch_data_migration.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class DestinationRestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String building;
    private String street;
    private String zipcode;
    private String borough;
    private String cuisine;
    private String name;
    private String restaurantId;
    private Double averageScore; // Average score from grades
    @Temporal(TemporalType.DATE)
    private Date lastGradeDate; // Date of the most recent grade

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public Date getLastGradeDate() {
        return lastGradeDate;
    }

    public void setLastGradeDate(Date lastGradeDate) {
        this.lastGradeDate = lastGradeDate;
    }
}
