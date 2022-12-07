package by.ilyin.core.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_writeoffs")
public class ProductWriteOff extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "amount")
    private Integer amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @OneToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private CustomUser creator;

    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    private Invoice invoice;

    public enum Status {
        LOST,
        STOLEN,
        SPOILED
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductWriteOff that = (ProductWriteOff) o;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(amount, that.amount)) return false;
        if (status != that.status) return false;
        if (!Objects.equals(product, that.product)) return false;
        return Objects.equals(creator, that.creator);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductWriteOff{");
        sb.append("id=").append(id);
        sb.append(", amount=").append(amount);
        sb.append(", status=").append(status);
        sb.append(", product=").append(product);
        sb.append(", creator=").append(creator);
        sb.append('}');
        return sb.toString();
    }

}
