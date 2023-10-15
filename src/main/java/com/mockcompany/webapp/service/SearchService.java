package com.mockcompany.webapp.service;

import com.mockcompany.webapp.data.ProductItemRepository;
import com.mockcompany.webapp.model.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private final ProductItemRepository productItemRepository;

    @Autowired
    public SearchService(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    public List<ProductItem> searchInItem(String query) {

        Iterable<ProductItem> allItems = this.productItemRepository.findAll();
        List<ProductItem> itemList = new ArrayList<>();

        for (ProductItem item : allItems) {
            if(query.startsWith("\"") && query.endsWith("\"")) {
                query = query.substring(1,query.length()-1);
                if(item.getName().equals(query)) {
                    itemList.add(item);
                    continue;
                }
                else if(item.getDescription().equals(query)){
                    itemList.add(item);
                    continue;
                }
                else {
                    continue;
                }
            }
            query = query.toLowerCase();
            if(item.getName().toLowerCase().contains(query)) {
                itemList.add(item);
            }
            else if(item.getDescription().toLowerCase().contains(query)) {
                itemList.add(item);
            }
        }
        return new ArrayList<>(itemList);
    }
}
