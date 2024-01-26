package kn.interview.quotation;

import java.util.Collection;
import kn.interview.quotation.dto.QuotationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuotationController {

    private final QuotationService quotationService;

    @Autowired
    public QuotationController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @GetMapping("/quotations")
    public Collection<QuotationDto> getQuotations() {
        return quotationService.getAllQuotation();
    }

}
