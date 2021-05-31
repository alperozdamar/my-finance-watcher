package com.alper.finance.controller;

import com.alper.finance.entity.Asset;
import com.alper.finance.entity.Statistic;
import com.alper.finance.service.AssetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
        
        for (Asset asset:theAssets){
            asset.setDifference(assetService.calculateDifference(asset));
        }
        
        Statistic statistic = new Statistic();
        if (theAssets.size() > 0) {
            Asset latestAsset = theAssets.get(theAssets.size() - 1);

            
            statistic.setTotalAsset(latestAsset.getTotal());
            statistic.setReadyMoney(latestAsset.getTotal() - latestAsset.getRet401k() - latestAsset.getRetTur());

            Asset firstAsset = theAssets.get(0);

            latestAsset.getDate();
            long diffInMillies = Math.abs(latestAsset.getDate().getTime() - firstAsset.getDate().getTime());
            long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            long diff = days / 30;
            if(diff>0) {
                statistic.setMonthlySavingAverage((latestAsset.getTotal() - firstAsset.getTotal()) / diff);
            }

        }
        // add to the spring model
        theModel.addAttribute("statistic", statistic);
        theModel.addAttribute("assets", theAssets);


        return "assets/list-assets";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Asset theAsset = new Asset();

        theModel.addAttribute("asset", theAsset);

        return "assets/asset-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("assetId") int theId,
                                    Model theModel) {

        // get the asset from the service
        Asset theAsset = assetService.findById(theId);

        // set asset as a model attribute to pre-populate the form
        theModel.addAttribute("asset", theAsset);

        // send over to our form
        return "assets/asset-form";
    }


    @PostMapping("/save")
    public String saveAsset(@ModelAttribute("asset") Asset theAsset) {

        //Calculate the total
        theAsset.setTotal(assetService.calculateTotal(theAsset));
        theAsset.setDifference(assetService.calculateDifference(theAsset));

        // save the asset
        assetService.save(theAsset);

        // use a redirect to prevent duplicate submissions
        //return "redirect:/assets/list"; http://localhost:8089/assets/assets/list
        //return "@{/assets/list}"; FAILED
        //return "redirect:assets/list"; FAILED
        //return "assets/list"; 500
        //return "@{/list}"; FAILED
        //return "list"; 500
        return "redirect:list";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("assetId") int theId) {

        // delete the asset
        assetService.deleteById(theId);

        // redirect to /assets/list
        //return "redirect:/assets/list"; FAILED
        //return "@{assets/list}"; FAILED
        //return "/assets/list"; 500
        return "redirect:list";
    }


}


















