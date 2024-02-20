package de.schulte.smartbar.backoffice.tables;

import de.schulte.smartbar.backoffice.BaseEntity;
import jakarta.persistence.Entity;

@Entity
@jakarta.persistence.Table(name = "Sbo_Table")
public class Table extends BaseEntity {

    private String name;
    private Integer seatCount;
    private Boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
