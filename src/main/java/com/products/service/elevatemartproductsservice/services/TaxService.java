package com.products.service.elevatemartproductsservice.services;

import com.products.service.elevatemartproductsservice.domain.Tax;
import com.products.service.elevatemartproductsservice.dto.TaxDto;

import java.util.List;

public sealed interface TaxService permits TaxServiceImpl {
    void create(Tax tax);
    void updateTaxPercentage(Long taxId, Double percentage);
    void updateTaxCode(Long taxId, String code);
    void updateTax(Tax updateTax,Long taxId);

    TaxDto getTaxById(Long taxId);

    List<TaxDto> getAllTax();

    void deleteTaxById(Long taxId);

}
