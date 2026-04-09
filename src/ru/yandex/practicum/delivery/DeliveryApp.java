package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableList = new ArrayList<>();


    private static ParcelBox<StandardParcel> standartParcel = new ParcelBox<>(100);
    private static ParcelBox<FragileParcel> fragileParcels = new ParcelBox<>(90);
    private static ParcelBox<PerishableParcel> perishableParcels = new ParcelBox<>(80);


    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportStatus();
                    break;
                case 5:
                    showBoxContents();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
                    break;
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Показать трек посылки");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels

        System.out.println("Выберете тип посылки, указав цифру: \n" +
                "1 - Стандартная\n" +
                "2 - Хрупкая\n" +
                "3 - Скоропортящаяся");

        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите краткое описание посылки:");
        String description = scanner.nextLine();

        System.out.println("Введите вес в кг, округлив его до целого числа:");
        int weight = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Укажите адрес доставки:");
        String deliveryAddress = scanner.nextLine();

        System.out.println("Укажите день доставки (от 1 до 31):");
        int sendDay = scanner.nextInt();
        scanner.nextLine();

        Parcel parcel = null;

        switch (type) {
            case 1:
                parcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                standartParcel.addParcel((StandardParcel) parcel);
                break;
            case 2:
                parcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                fragileParcels.addParcel((FragileParcel) parcel);
                trackableList.add((Trackable) parcel);
                break;
            case 3:
                System.out.println("Укажите срок годности дней:");
                int timeToLive = scanner.nextInt();
                scanner.nextLine();
                parcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                perishableParcels.addParcel((PerishableParcel) parcel);
                break;
            default:
                System.out.println("Указан неверный тип посылки.\n");
                return;
        }
        allParcels.add(parcel);
        System.out.println("Посылка добавлена!\n");
    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()

        if (allParcels.isEmpty()) {
            System.out.println("Нет посылок для отправки.\n");
            return;
        }

        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }

        System.out.println("Посылки отправлены.\n");

    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран

        if (allParcels.isEmpty()){
            System.out.println("Нет посылок для отправки.\n");
            return;
        }

        int totalCost = 0;
        for (Parcel parcel : allParcels) {
            int cost = parcel.calculateDeliveryCost();
            totalCost += cost;
        }

        System.out.println("Общая стоимость доставки: " + totalCost);
    }

    private static void reportStatus() {
        if (trackableList.isEmpty()) {
            System.out.println("Нет посылок для отслеживания.");
            return;
        }

        System.out.println("Введите новое местоположения");
        String newLocation = scanner.nextLine();
        for (Trackable trackableParcel : trackableList) {
            trackableParcel.reportStatus(newLocation);
        }
    }

    private static void showBoxContents() {
        System.out.println("Выберете тип посылки, указав цифру: \n" +
                "1 - Стандартная\n" +
                "2 - Хрупкая\n" +
                "3 - Скоропортящаяся");

        int type = scanner.nextInt();
        scanner.nextLine();

        switch (type) {
            case 1:
                System.out.println(standartParcel);
                for (StandardParcel parcel : standartParcel.getAllParcels()) {
                    System.out.println(parcel);
                }
                break;
            case 2:
                System.out.println(fragileParcels);
                for (FragileParcel parcel : fragileParcels.getAllParcels()) {
                    System.out.println(parcel);
                }
                break;
            case 3:
                System.out.println(perishableParcels);
                for (PerishableParcel parcel : perishableParcels.getAllParcels()) {
                    System.out.println(parcel);
                }
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }

}

