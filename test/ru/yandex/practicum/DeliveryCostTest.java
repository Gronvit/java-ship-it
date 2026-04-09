package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.*;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCostTest {

    @Test
    public void testStandardParcelCost() {
        // Стандартный сценарий: клавиатура (не хрупкая)
        StandardParcel parcel = new StandardParcel("Клавиатура", 5, "Москва", 1);
        assertEquals(10, parcel.calculateDeliveryCost()); // 5 * 2 = 10

        // Граничный случай: вес 0
        StandardParcel zeroWeight = new StandardParcel("Пустая коробка", 0, "Москва", 1);
        assertEquals(0, zeroWeight.calculateDeliveryCost());
    }

    @Test
    public void testFragileParcelCost() {
        // Стандартный сценарий: монитор (хрупкий)
        FragileParcel parcel = new FragileParcel("Монитор", 3, "Санкт-Петербург", 1);
        assertEquals(12, parcel.calculateDeliveryCost()); // 3 * 4 = 12

        // Большой вес: телевизор
        FragileParcel heavy = new FragileParcel("Телевизор", 25, "Казань", 1);
        assertEquals(100, heavy.calculateDeliveryCost()); // 25 * 4 = 100
    }

    @Test
    public void testPerishableParcelCost() {
        // Стандартный сценарий: молоко
        PerishableParcel parcel = new PerishableParcel("Молоко", 2, "Екатеринбург", 1, 3);
        assertEquals(6, parcel.calculateDeliveryCost()); // 2 * 3 = 6
    }

    @Test
    public void testIsExpired() {
        // Свежая посылка: кефир (срок не истёк)
        PerishableParcel fresh = new PerishableParcel("Кефир", 1, "Новосибирск", 1, 5);
        assertFalse(fresh.isExpired(5)); // 1 + 5 >= 5

        // Просроченная посылка: курица
        PerishableParcel expired = new PerishableParcel("Курица", 2, "Омск", 1, 2);
        assertTrue(expired.isExpired(5)); // 1 + 2 < 5

        // Граничный случай: срок истекает сегодня (творог)
        PerishableParcel today = new PerishableParcel("Творог", 1, "Тюмень", 10, 3);
        assertFalse(today.isExpired(13)); // 10 + 3 >= 13
    }

    @Test
    public void testParcelBoxAdd() {
        // Стандартный сценарий: добавление в пределах веса (коробка для стандартных посылок)
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        StandardParcel parcel1 = new StandardParcel("Клавиатура", 3, "Москва", 1);
        StandardParcel parcel2 = new StandardParcel("Мышь", 5, "СПб", 1);

        assertTrue(box.addParcel(parcel1));
        assertTrue(box.addParcel(parcel2));
        assertEquals(2, box.getAllParcels().size());

        // Превышение веса
        StandardParcel parcel3 = new StandardParcel("Наушники", 3, "Казань", 1);
        assertFalse(box.addParcel(parcel3)); // Общий вес будет 11 > 10
        assertEquals(2, box.getAllParcels().size());

        // Граничный случай: точное соответствие весу
        ParcelBox<StandardParcel> exactBox = new ParcelBox<>(8);
        assertTrue(exactBox.addParcel(parcel1)); // 3 кг
        assertTrue(exactBox.addParcel(parcel2)); // 5 кг, итого 8 = макс
        assertEquals(2, exactBox.getAllParcels().size());
    }

}
