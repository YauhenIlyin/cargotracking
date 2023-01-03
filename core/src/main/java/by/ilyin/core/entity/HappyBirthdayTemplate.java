package by.ilyin.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "happy_birthday_template")
@AllArgsConstructor
@NoArgsConstructor
public class HappyBirthdayTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "template")
    private String template;
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HappyBirthdayTemplate that = (HappyBirthdayTemplate) o;
        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(template, that.template);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (template != null ? template.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HappyBirthdayTemplate{");
        sb.append("id=").append(id);
        sb.append(", template='").append(template).append('\'');
        sb.append(", clientId=").append(client != null ? client.getId() : null);
        sb.append('}');
        return sb.toString();
    }

}
