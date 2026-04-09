package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel { // скоропортящаяся посылка

    private static final int BASE_COST = 3;
    private final int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    protected int getBaseCost() {
        return BASE_COST;
    }

    public boolean isExpired(int currentDay) { // посылка испортилась
        if ((sendDay + timeToLive) >= currentDay) {
            return false;
        } else {
            return true;
        }
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    @Override
    public String toString() {
        return "Скоропортящаяся посылка:\n" +
                "Описание: " + description +
                ", Вес: " + weight +
                ", Срок годности: " + timeToLive + "\n";
    }
}
