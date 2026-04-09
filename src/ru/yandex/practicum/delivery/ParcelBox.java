package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {

private List<T> parcels;
private int maxWeight;
private int currentWeight;

    public ParcelBox(int maxWeight) {
        this.parcels = new ArrayList<>();
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
    }

    public boolean addParcel(T parcel) {
        if (currentWeight + parcel.getWeight() <= maxWeight) {
            parcels.add(parcel);
            currentWeight += parcel.getWeight();
            return true;
        } else {
            System.out.println("Достигнут максимальный вес посылок.");
            return false;
        }
    }

    public List<T> getAllParcels() {
        return new ArrayList<>(parcels);
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getRemainingCapacity() {
        return maxWeight - currentWeight;
    }

    @Override
    public String toString() {
        return "Максимальный вес: " + maxWeight +
                "\n, Текущий вес: " + currentWeight +
                "\n";
    }
}
