package com.alper.finance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alper.finance.dao.AssetRepository;
import com.alper.finance.entity.Asset;

@Service
public class AssetServiceImpl implements AssetService {

    private AssetRepository assetRepository;

    @Autowired
    public AssetServiceImpl(AssetRepository theAssetRepository) {
        assetRepository = theAssetRepository;
    }

    @Override
    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    @Override
    public Asset findById(int theId) {
        List<Asset> result = assetRepository.findById(theId);

        Asset theAsset = null;

        if (result.size() != 0) {
            theAsset = result.get(0);
        } else {
            // we didn't find the asset
            throw new RuntimeException("Did not find asset id - " + theId);
        }

        return theAsset;
    }

    @Override
    public void save(Asset theAsset) {
        assetRepository.save(theAsset);
    }

    @Override
    public void deleteById(int theId) {
        assetRepository.deleteById(theId);
    }

    @Override
    public int calculateTotal(Asset asset) {
        return asset.getBoa() + asset.getChase() + asset.getHsbcTr() + asset.getStock() + asset.getRet401k() + asset
                .getRetTur();
    }

    @Override
    public int calculateDifference(Asset theAsset) {

        if (assetRepository.getMaxId() == null) {
            return 0;
        }

        Optional<Asset> prevAssetList = assetRepository.findById(assetRepository.getMaxId());

        if (!prevAssetList.isPresent()) {
            return 0;
        } else {
            return theAsset.getTotal() - ((Asset) prevAssetList.get()).getTotal();
        }
    }


}






