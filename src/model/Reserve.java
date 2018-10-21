package model;

import container.ItemStack;
import enums.ItemType;
import expetions.ReserveException;
import model.Item.Asset;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

public class Reserve {
    private final List<Asset> reserve;
    private final ItemStack<Asset> assets;

    public Reserve(ItemStack<Asset> assets){
        reserve= new ArrayList<>(4);
        this.assets = assets;

    }

    public void init(){
        reserve.clear();
        for(int i = 0 ; i< 4;i++){
            reserve.add(assets.draw());
        }
    }

    public List<Asset> getReserve(){
        return reserve;
    }
    public  List<Asset> getReserve(List<ItemType> itemTypes){
        List<Asset> filteredReserve = new ArrayList<>();
        for(Asset asset : reserve){
            for(ItemType itemType:itemTypes) {
                if (asset.getSubType().equalsWithParts(itemType)) {
                    filteredReserve.add(asset);
                }
            }
        }
        return filteredReserve;
    }
    public Asset get(Asset asset){
        reserve.remove(asset);
        reserve.add(asset.draw());
        return asset;
    }

    public void remove(Asset asset){
        if(reserve.contains(asset)){
            reserve.remove(asset);
            reserve.add(assets.draw());
        }
    }
    public List<Asset> buy(List<Asset> choosen, int success) throws ReserveException{
        List<Asset> bought = new ArrayList<>();
        int priceSum=choosen.stream().mapToInt(Asset::getPrice).sum();
        if(priceSum>success){
            throw new ReserveException(ResourceUtil.get("${reserve_success_to_low}","exception",success+"",priceSum+""));
        }
        for( Asset asset : choosen ) {
            bought.add(asset);
            reserve.remove(asset);
            reserve.add(assets.draw());
        }
        return bought;
    }
}
