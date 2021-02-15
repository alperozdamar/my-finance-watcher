package com.alper.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alper.finance.entity.Asset;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AssetRepository extends JpaRepository<Asset, Integer> {

    // that's it ... no need to write any code LOL!

    //  @Query("FROM Asset a where a.month = :month")
    //  public List<Asset> findByMonth(@Param("month") int month);

    @Query("SELECT a FROM Asset a order by a.date")
    public List<Asset> findAll();

    public List<Asset> findById(int id);

    @Query("SELECT Max(a.id) FROM Asset a ")
    public Integer getMaxId();
}
