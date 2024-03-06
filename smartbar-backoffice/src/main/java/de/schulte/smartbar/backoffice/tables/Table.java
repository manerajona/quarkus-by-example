package de.schulte.smartbar.backoffice.tables;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@jakarta.persistence.Table(name = "Sbo_Table", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Table extends PanacheEntity {

    @NotNull
    public String name;

    @NotNull
    public Integer seatCount;

    @NotNull
    public Boolean active;

}
