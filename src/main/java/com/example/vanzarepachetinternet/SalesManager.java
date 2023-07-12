package com.example.vanzarepachetinternet;
import java.util.ArrayList;
import java.util.List;

public class SalesManager {
    private List<InternetPackage> soldPackages;

    public SalesManager() {
        soldPackages = new ArrayList<>();
    }

    public List<InternetPackage> getSoldPackages() {
        return soldPackages;
    }

    public void addSale(InternetPackage internetPackage) {
        soldPackages.add(internetPackage);
    }

    public void deleteSale(InternetPackage internetPackage) {
        soldPackages.remove(internetPackage);
    }
}