package ru.yandex.practicum.delivery;

public class StandardParcel extends Parcel { // стандартная посылка

    private static final int BASE_COST = 2;

    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    protected int getBaseCost() {
        return BASE_COST;
    }

    @Override
    public String toString() {
        return "Стандартная посылка:\n" +
                "Описание: " + description +
                ", Вес: " + weight + "\n";
    }
}
