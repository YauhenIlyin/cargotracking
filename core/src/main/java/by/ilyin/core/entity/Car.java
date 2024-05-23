package by.ilyin.core.entity;

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
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
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
        if (!Objects.equals(number, car.number)) return false;
        if (!Objects.equals(fuelConsumption, car.fuelConsumption))
            return false;
        if (!Objects.equals(loadCapacity, car.loadCapacity)) return false;
        if (carType != car.carType) return false;
        return Objects.equals(client, car.client);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (fuelConsumption != null ? fuelConsumption.hashCode() : 0);
        result = 31 * result + (loadCapacity != null ? loadCapacity.hashCode() : 0);
        result = 31 * result + (carType != null ? carType.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
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
        sb.append(", client=").append(client);
        sb.append('}');
        return sb.toString();
    }

}
