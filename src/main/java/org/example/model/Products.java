package org.example.model;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Override
    public String toString() {
        return  "productName='" + productName + '\'' +
                ", productGroup='" + productGroup + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate;
    }

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_group", nullable = false)
    private String productGroup;

    @Column(name = "description")
    private String description;

    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parameters> parameters;

    // Default constructor
    public Products() {}

    // Constructor with parameters
    public Products(String productName, String productGroup, String description, Date releaseDate) {
        this.productName = productName;
        this.productGroup = productGroup;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    // Getters and setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Parameters> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameters> parameters) {
        this.parameters = parameters;
    }
}
