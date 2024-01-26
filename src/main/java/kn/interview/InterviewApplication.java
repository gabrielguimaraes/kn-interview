package kn.interview;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import kn.interview.product.ProductRepository;
import kn.interview.product.model.Product;
import kn.interview.product.model.ProductGroup;
import kn.interview.quotation.QuotationRepository;
import kn.interview.quotation.model.Quotation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InterviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewApplication.class, args);
    }

    @Bean
    public CommandLineRunner createRecords(ProductRepository productRepository,
        QuotationRepository quotationRepository) {
        return args -> {
            Product productA_A = Product.builder()
                .code("A_A")
                .productGroup(createGroup("AA"))
                .build();
            productA_A = productRepository.save(productA_A);

            Product productB_A = Product.builder()
                .code("B_A")
                .productGroup(createGroup("AA"))
                .build();
            productB_A = productRepository.save(productB_A);

            Product productC_A = Product.builder()
                .code("C_A")
                .productGroup(createGroup("AA"))
                .build();
            productC_A = productRepository.save(productC_A);

            Product productA_B = Product.builder()
                .code("A_B")
                .productGroup(createGroup("BB"))
                .build();
            productA_B = productRepository.save(productA_B);
            Product productB_B = Product.builder()
                .code("B_B")
                .productGroup(createGroup("BB"))
                .build();
            productB_B = productRepository.save(productB_B);

            Product productC_B = Product.builder()
                .code("C_B")
                .productGroup(createGroup("BB"))
                .build();
            productC_B = productRepository.save(productC_B);

            Quotation quotation_1 = Quotation.builder()
                .name("quote 1")
                .quoteValue(new BigDecimal("100.20"))
                .products(Set.of(productA_A, productB_A, productC_A))
                .build();
            Quotation quotation_1_2 = Quotation.builder()
                .name("quote 1 - version 2")
                .quoteValue(new BigDecimal("230.99"))
                .products(Set.of(productB_A, productC_A))
                .build();

            Quotation quotation_1_3 = Quotation.builder()
                .name("quote 1 - v 3")
                .quoteValue(new BigDecimal("89.99"))
                .products(Set.of(productA_A, productC_A))
                .build();

            Quotation quotation_1_4 = Quotation.builder()
                .name("quote 1 - v4")
                .quoteValue(new BigDecimal("148.00"))
                .products(Set.of(productA_A, productB_A, productC_A))
                .build();

            Quotation quotation_1_5 = Quotation.builder()
                .name("quote 1 - v5")
                .quoteValue(new BigDecimal("148.00"))
                .build();

            Quotation quotation_1_6 = Quotation.builder()
                .name("quote 1 - v6")
                .quoteValue(new BigDecimal("7.50"))
                .products(Set.of(productA_A, productB_A, productC_A))
                .build();

            Quotation quotation_2 = Quotation.builder()
                .name("quote 2")
                .quoteValue(new BigDecimal("242.23"))
                .products(Set.of(productA_B, productC_B))
                .build();
            Quotation quotation_3 = Quotation.builder()
                .name("quote 3")
                .quoteValue(new BigDecimal("39.99"))
                .products(Set.of(productB_B))
                .build();

            Quotation quotation_4 = Quotation.builder()
                .name("quote 4")
                .quoteValue(new BigDecimal("99.99"))
                .build();

            quotationRepository.saveAll(
                List.of(quotation_3, quotation_1, quotation_1_3, quotation_1_5, quotation_1_6,
                    quotation_1_4, quotation_4, quotation_1_2, quotation_2));
        };
    }

    private static ProductGroup createGroup(String code) {
        return ProductGroup.builder()
            .code(code)
            .build();
    }

}

/**
 * SELECT * FROM PRODUCT; SELECT * FROM PRODUCT_GROUP; SELECT * FROM QUOTATION_PRODUCT; SELECT *
 * FROM QUOTATION;
 * <p>
 * SELECT Q.NAME ,G.CODE AS G_CODE, P.CODE AS P_CODE FROM QUOTATION Q INNER JOIN QUOTATION_PRODUCT
 * QP ON QP.QUOTATION_ID = Q.ID LEFT JOIN PRODUCT P ON P.ID = QP.PRODUCT_ID LEFT JOIN PRODUCT_GROUP
 * G ON G.ID = P.GROUP_ID ORDER BY G.CODE ASC, P.CODE ASC;
 * <p>
 * <p>
 * SELECT qp.quotation_id, code FROM product p INNER JOIN quotation_product qp ON qp.product_id =
 * p.id
 */