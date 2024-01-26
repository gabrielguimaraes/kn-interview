package kn.interview.quotation;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import kn.interview.product.model.Product;
import kn.interview.product.model.ProductGroup;
import kn.interview.quotation.dto.QuotationDto;
import kn.interview.quotation.model.Quotation;
import kn.interview.quotation.model.QuotationOrderedByGroupProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class QuotationService {

    private final QuotationRepository quotationRepository;

    @Autowired
    public QuotationService(QuotationRepository quotationRepository) {
        this.quotationRepository = quotationRepository;
    }

    public List<QuotationDto> getAllQuotation() {
        List<QuotationOrderedByGroupProduct> allProductGroupQuotation = this.quotationRepository.findAllProductGroupQuotation(
            Sort.by(Order.asc("pg.code"), Order.asc("pcodes")));
        List<Long> quotationIds = getOrderedQuotationIds(allProductGroupQuotation);
        Map<Long, Quotation> allQuotations = mapQuotationByIds(
            this.quotationRepository.findAllQuotations(
                Sort.by(Order.asc("pg.code"), Order.asc("p.code"))));
        return quotationIds.stream()
            .map(allQuotations::get)
            .map(quotation -> QuotationDto.builder()
                .id(quotation.getId())
                .name(quotation.getName())
                .value(quotation.getQuoteValue())
                .product(convertToName(quotation))
                .build()
            )
            .toList();
    }

    private List<Long> getOrderedQuotationIds(
        List<QuotationOrderedByGroupProduct> allProductGroupQuotation) {
        return allProductGroupQuotation.stream().map(QuotationOrderedByGroupProduct::quotationId)
            .toList();
    }

    private Map<Long, Quotation> mapQuotationByIds(List<Quotation> allQuotations) {
        return allQuotations.stream()
            .collect(Collectors.toMap(Quotation::getId, Function.identity()));
    }

    private String convertToName(Quotation quotation) {
        String group = quotation.getProducts().stream().map(Product::getProductGroup)
            .map(ProductGroup::getCode)
            .distinct()
            .collect(Collectors.joining());
        String productNames = quotation.getProducts().stream().map(Product::getCode)
            .collect(Collectors.joining(", "));
        return group + "-" + productNames;
    }
}
