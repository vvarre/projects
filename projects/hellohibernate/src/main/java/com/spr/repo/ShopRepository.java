package com.spr.repo;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spr.model.Shop;

public interface ShopRepository extends CrudRepository<Shop, Long> {

    List<Shop> findByName(String name);
    
}