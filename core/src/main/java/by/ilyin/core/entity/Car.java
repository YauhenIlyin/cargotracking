package by.ilyin.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "fuel_consumption")
    private Float fuelConsumption;
    @Column(name = "load_capacity")
    private Float loadCapacity;
    @Column(name = "car_type")
    private Car.CarType carType;
    @Column(name = "client_id")
    private Long clientId;
    @JsonIgnore
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Client client;

    public enum CarType {
        REFRIGERATOR,
        CISTERN,
        COVERED_BODY
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        if (!Objects.equals(id, car.id)) return false;
        if (!number.equals(car.number)) return false;
        if (!fuelConsumption.equals(car.fuelConsumption)) return false;
        if (!loadCapacity.equals(car.loadCapacity)) return false;
        if (carType != car.carType) return false;
        return Objects.equals(clientId, car.clientId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + number.hashCode();
        result = 31 * result + fuelConsumption.hashCode();
        result = 31 * result + loadCapacity.hashCode();
        result = 31 * result + carType.hashCode();
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("id=").append(id);
        sb.append(", number='").append(number).append('\'');
        sb.append(", fuelConsumption=").append(fuelConsumption);
        sb.append(", loadCapacity=").append(loadCapacity);
        sb.append(", carType=").append(carType);
        sb.append(", clientId=").append(clientId);
        sb.append('}');
        return sb.toString();
    }
}
