package kn.interview.quotation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import kn.interview.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Formula;

@Builder
@Getter
@ToString(exclude = {"products"})
@EqualsAndHashCode(exclude = {"products"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal quoteValue;

    @ManyToMany
    @JoinTable(name = "QUOTATION_PRODUCT",
        joinColumns = @JoinColumn(name = "QUOTATION_ID"),
        inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    @Default
    private Set<Product> products = new HashSet<>();
}
