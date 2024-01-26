package kn.interview.quotation;

import java.util.List;
import kn.interview.quotation.model.Quotation;
import kn.interview.quotation.model.QuotationOrderedByGroupProduct;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuotationRepository extends JpaRepository<Quotation, Long> {

    @Query("""
        SELECT q
        FROM Quotation q
        LEFT JOIN FETCH q.products p
        LEFT JOIN FETCH p.productGroup pg
        """)
    List<Quotation> findAllQuotations(Sort sort);

    @Query("""
        SELECT new kn.interview.quotation.model.QuotationOrderedByGroupProduct(
            q.id,
            pg.code,
            LISTAGG(p.code, ',') WITHIN GROUP (ORDER BY p.code) as pcodes
        )
        FROM Quotation q
        LEFT JOIN q.products p
        LEFT JOIN p.productGroup pg
        GROUP BY q.id
        """)
    List<QuotationOrderedByGroupProduct> findAllProductGroupQuotation(Sort sort);
}
