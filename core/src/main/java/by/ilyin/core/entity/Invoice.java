package by.ilyin.core.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Column(name = "verification_date")
    private LocalDate verificationDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @OneToMany(mappedBy = "invoice", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Product> products;
    @ManyToOne
    @JoinColumn(name = "storage_id", referencedColumnName = "id")
    private Storage storage;
    @ManyToOne
    @JoinColumn(name = "product_owner_id", referencedColumnName = "id")
    private ProductOwner productOwner;
    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private CustomUser creator;
    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private CustomUser driver;
    @JsonIgnore
    @OneToOne(mappedBy = "invoice")
    private Waybill waybill;

    public enum Status {
        MADE_OUT,
        DELIVERED,
        VERIFICATION_COMPLETE
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        if (!Objects.equals(id, invoice.id)) return false;
        if (!Objects.equals(number, invoice.number)) return false;
        if (!Objects.equals(creationDate, invoice.creationDate))
            return false;
        if (!Objects.equals(verificationDate, invoice.verificationDate))
            return false;
        if (status != invoice.status) return false;
        if (!Objects.equals(products, invoice.products)) return false;
        if (!Objects.equals(storage, invoice.storage)) return false;
        if (!Objects.equals(productOwner, invoice.productOwner))
            return false;
        if (!Objects.equals(creator, invoice.creator)) return false;
        return Objects.equals(driver, invoice.driver);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (verificationDate != null ? verificationDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (products != null ? products.hashCode() : 0);
        result = 31 * result + (storage != null ? storage.hashCode() : 0);
        result = 31 * result + (productOwner != null ? productOwner.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (driver != null ? driver.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Invoice{");
        sb.append("id=").append(id);
        sb.append(", number='").append(number).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append(", verificationDate=").append(verificationDate);
        sb.append(", status=").append(status);
        sb.append(", products=").append(products);
        sb.append(", storage=").append(storage);
        sb.append(", productOwner=").append(productOwner);
        sb.append(", creator=").append(creator);
        sb.append(", driver=").append(driver);
        sb.append('}');
        return sb.toString();
    }

}
