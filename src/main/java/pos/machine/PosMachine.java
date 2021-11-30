package pos.machine;

import java.util.ArrayList;
import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodesList) {
        return null;
    }

    public int getQuantity(List<String> barcodesList, String barcode){
        List<ItemInfo> allItem = ItemDataLoader.loadAllItemInfos();
        int quantity = 0;
        for(String barcodeL: barcodesList){
            if(barcodeL.equals(barcode)){
                quantity++;
            }
        }
        return quantity;
    }

    public String formatRow(String barcode, List<String>originalBarcodeList) {
        String row = "";
        List<ItemInfo> allItemList = ItemDataLoader.loadAllItemInfos();
        //get distinct list

        for(ItemInfo allItem: allItemList){
            int quantity = getQuantity(originalBarcodeList, barcode);
            int price = allItem.getPrice();
            if(barcode.equals(allItem.getBarcode())){
                row = "Name: " + allItem.getName() + ", Quantity: " + quantity
                        + ", Unit price: " + price
                        + " (yuan), Subtotal: " + calculateSubtotal(quantity,price) + " (yuan)";
            }
        }

        return row;
    }

    public String formatTable(List<String> barcodesList) {
        String table = "";
        List<String> distinctBarcodeList = getDistinctBarcodeList(barcodesList);
        for(String barcode: distinctBarcodeList){
            table += formatRow(barcode, barcodesList) + "\n";
        }
        return table;
    }

    public List<String>getDistinctBarcodeList(List<String> barcodesList) {
        List<String> barcodeDistinct = new ArrayList<>();
        for(String barcodeL: barcodesList){
            if(!(barcodeDistinct.contains(barcodeL))){
                barcodeDistinct.add(barcodeL);
            }
        }
        return barcodeDistinct;
    }

    public int calculateOverallTotal(List<String> barcodesList) {
        List<ItemInfo> allItemList = ItemDataLoader.loadAllItemInfos();
        int sum = 0;
        for(String barcode: barcodesList){
            for(ItemInfo allItem: allItemList){
                if(barcode.equals(allItem.getBarcode())){
                    sum += allItem.getPrice();
                }
            }
        }
        return sum;
    }

    public String calculateSubtotal(int quantity, int unitPrice) {
        return null;
    }
}
