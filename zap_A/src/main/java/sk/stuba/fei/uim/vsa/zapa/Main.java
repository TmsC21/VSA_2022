package sk.stuba.fei.uim.vsa.zapa;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Projekt p = new Projekt();
        p.create();
        System.out.println(p.publicationUpdate("1", 22.0, "First_"));
        Map<String, Double> priceList = new HashMap<>();
        priceList.put("1", 15.0);
        priceList.put("2", 33.0);
        priceList.put("4", 45.0);
        p.priceListUpdate(priceList);
    }
}
