package com.xavier.mozprops_api.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.xavier.mozprops_api.models.enums.PropertyStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "properties")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "property_type_id")
    private PropertyType propertyType;

    private Double price;

    @Enumerated(EnumType.STRING)
    private PropertyStatus propertyStatus;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PropertyImages> images = new ArrayList<>();

    @Embedded
    private Address address;

    
    @Embedded
    private PropertyDetails details;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    /*
     * Add an image to the property.
     * @param image The image to add.
     */
    public void addImage(PropertyImages image) {
        images.add(image);
        image.setProperty(this);
    }

    /*
     * Remove an image from the property.
     * @param image The image to remove.
     */
    public void removeImage(PropertyImages image) {
        images.remove(image);
        image.setProperty(null);
    }


    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
