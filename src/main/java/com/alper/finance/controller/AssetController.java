package com.alper.finance.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alper.finance.entity.Asset;
import com.alper.finance.service.AssetService;


@Controller
@RequestMapping("/assets")
public class AssetController {

	private AssetService assetService;
	
	public AssetController(AssetService theAssetService) {
		assetService = theAssetService;
	}
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listAssets(Model theModel) {
		
		// get assets from db
		List<Asset> theAssets = assetService.findAll();
		
		// add to the spring model
		theModel.addAttribute("assets", theAssets);
		
		return "/assets/list-assets";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Asset theAsset = new Asset();
		
		theModel.addAttribute("asset", theAsset);
		
		return "/assets/asset-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("assetId") int theId,
									Model theModel) {
		
		// get the asset from the service
		Asset theAsset = assetService.findById(theId);
		
		// set asset as a model attribute to pre-populate the form
		theModel.addAttribute("asset", theAsset);
		
		// send over to our form
		return "/assets/asset-form";			
	}
	
	
	@PostMapping("/save")
	public String saveAsset(@ModelAttribute("asset") Asset theAsset) {
		
		// save the asset
		assetService.save(theAsset);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/assets/list";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("assetId") int theId) {
		
		// delete the asset
		assetService.deleteById(theId);
		
		// redirect to /assets/list
		return "redirect:/assets/list";
		
	}
	

	
}


















