package com.alper.finance.service;

import java.util.List;

import com.alper.finance.entity.Asset;

public interface AssetService {

    public List<Asset> findAll();

    public Asset findById(int theId);

    public void save(Asset theAsset);

    public void deleteById(int theId);

    public int calculateTotal(Asset asset);

    public int calculateDifference(Asset theAsset);
}
