package kn.interview.quotation.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QuotationDto {

    private Long id;
    private String name;
    private BigDecimal value;
    private String product;
}
