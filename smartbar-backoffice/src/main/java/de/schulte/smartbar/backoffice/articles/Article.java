package de.schulte.smartbar.backoffice.articles;

import de.schulte.smartbar.backoffice.categories.Category;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "category_id"})
})
public class Article extends PanacheEntity {

    @NotNull
    public String name;

    @NotNull
    @Positive
    public BigDecimal price;

    @NotNull
    public String description;

    @NotNull
    public String pictureBase64;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    public Category category;

}
