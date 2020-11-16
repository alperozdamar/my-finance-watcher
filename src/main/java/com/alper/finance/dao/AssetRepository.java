package com.alper.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alper.finance.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, Integer> {

	// that's it ... no need to write any code LOL!


}
