package com.mockcompany.webapp.controller;

import com.mockcompany.webapp.api.SearchReportResponse;
import com.mockcompany.webapp.data.ProductItemRepository;
import com.mockcompany.webapp.model.ProductItem;
import com.mockcompany.webapp.service.SearchService;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Management decided it is super important that we have lots of products that match the following terms.
 * So much so, that they would like a daily report of the number of products for each term along with the total
 * product count.
 */
@RestController
public class ReportController {

    /**
     * The people that wrote this code didn't know about JPA Spring Repository interfaces!
     */
    private final ProductItemRepository productItemRepository;
    private SearchService searchService;

    @Autowired
    public ReportController(ProductItemRepository productItemRepository, SearchService searchService) {
        this.productItemRepository = productItemRepository;
        this.searchService = searchService;
    }

    @GetMapping("/api/products/report")
    public SearchReportResponse runReport() {
        Iterable<ProductItem> allItems = this.productItemRepository.findAll();
        SearchReportResponse response = new SearchReportResponse();
        Map<String, Integer> searchTermHits = new HashMap<>();

        response.setProductCount((int)productItemRepository.count());

        for(ProductItem item: allItems) {

        }


        return response;
    }
}
