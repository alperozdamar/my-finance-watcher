package com.alper.finance.dao;

import com.alper.finance.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Integer> {

    // that's it ... no need to write any code LOL!

    //  @Query("FROM Asset a where a.month = :month")
    //  public List<Asset> findByMonth(@Param("month") int month);

    @Query("SELECT a FROM Asset a order by a.date DESC")
    public List<Asset> findAll();

    public List<Asset> findById(int id);

    @Query("SELECT Max(a.id) FROM Asset a ")
    public Integer getMaxId();

    @Query("SELECT a FROM Asset a WHERE a.date < :date order by a.date DESC ")
    public List<Asset> getPreviousAsset(@Param("date") Date date);
    
}
