package ControlWorkJava;

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {

        Toys toy1 = new Toys();
        toy1.setIdToy("1");
        toy1.setNameToy("Машинка");
        toy1.setQuantityToy(6);
        toy1.setDropToy(20);

        Toys toy2 = new Toys();
        toy2.setIdToy("2");
        toy2.setNameToy("Мишка");
        toy2.setQuantityToy(0);
        toy2.setDropToy(50);

        Toys toy3 = new Toys();
        toy3.setIdToy("3");
        toy3.setNameToy("Кукла");
        toy3.setQuantityToy(3);
        toy3.setDropToy(10);

        Toys toy4 = new Toys();
        toy4.setIdToy("4");
        toy4.setNameToy("Динозавр");
        toy4.setQuantityToy(1);
        toy4.setDropToy(5);

        List<Toys> uniqueToy = new ArrayList<>();
        uniqueToy.add(toy1);
        uniqueToy.add(toy2);
        uniqueToy.add(toy3);
        uniqueToy.add(toy4);

        int count = 0; // счетчик пользователей которые участвовали в розыгрыше призов

        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Выберите действие: ");
            System.out.println("1 добавить игрушку: ");
            System.out.println("2 Розыгрыш игрушки: ");
            System.out.println("3 изменить вес игрушки: ");
            System.out.println("4 Просмотреть все игрушки участвующие в розыгрыше: ");
            System.out.println("0 завершить программу: ");

            String strScanner = scanner.next();
            if (    strScanner.equals("1") ||
                    strScanner.equals("2") ||
                    strScanner.equals("3") ||
                    strScanner.equals("4") ||
                    strScanner.equals("0")) {
                if (strScanner.equals("1")) {
                    uniqueToy.add(createToy());
                }
                if (strScanner.equals("2")) {
                    count++;
                    Giveaway(uniqueToy, count);
                }
                if (strScanner.equals("3")) {
                    ChangeDropToy(uniqueToy);
                }
                if (strScanner.equals("4")) {
                    for (Toys toys : uniqueToy) {
                        System.out.printf("ID игрушки: %s%n", toys.getIdToy());
                        System.out.printf("Название игрушки: %s%n", toys.getNameToy());
                        System.out.printf("Количество игрушек: %d%n", toys.getQuantityToy());
                        System.out.printf("Шанс выпадения игрушки: %d%n%n", toys.getDropToy());
                    }
                }
                if (strScanner.equals("0")) {
                    System.out.println("До свидания!");
                    break;
                }
            }else {
                System.out.println("Введены не корректные данные!");
            }
        }
    }

    public static Toys createToy() {
//        Этот метод создает новую запись игрушки
        Toys toys = new Toys();
        Scanner scannerIdToy = new Scanner(System.in);
        System.out.println("Введите id игрушка: ");
        toys.setIdToy(scannerIdToy.next());
        Scanner scannerNameToy = new Scanner(System.in);
        System.out.println("Введите название игрушки: ");
        toys.setNameToy(scannerNameToy.next());
        Scanner scannerQuantityToy = new Scanner(System.in);
        System.out.println("Введите количество игрушек: ");
        toys.setQuantityToy(scannerQuantityToy.nextInt());
        Scanner scannerDropToy = new Scanner(System.in);
        System.out.println("Введите шанс выпадения игрушки: ");
        toys.setDropToy(scannerDropToy.nextInt());
        return toys;
    }

    public static void Giveaway(List<Toys> uniqueToy, int count) {
//        Этот метод разыгрывает призы
        int quantity = 0;
        for (Toys toys : uniqueToy) {
            int random = new Random().nextInt(1, 101);
            if (random <= toys.getDropToy() && toys.getQuantityToy() != 0){
                System.out.println("Wine");
                quantity = toys.getQuantityToy();
                toys.setQuantityToy(quantity - 1);
                WritingToFile(toys, count);
                break;
            }
        }
    }
    public static void ChangeDropToy(List<Toys> uniqueToy) {
//        Этот метод заменяет шанс выпадения у определенной игрушки
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id игрушки чей шанс выпадения хотите изменить: ");
        String scannerIdToy = scanner.next();
        for (Toys toy : uniqueToy) {
            if (toy.getIdToy().equals(scannerIdToy)) {
                System.out.printf("Шанс выпадения: %s", toy.getDropToy());
                Scanner scannerDropToy = new Scanner(System.in);
                toy.setDropToy(scannerDropToy.nextInt());
            }
        }
    }
    public static void WritingToFile(Toys toys, int count) {
//      Этот метод записывает в файл игрушки которые выиграли и id победителя
        File file = new File("Work2Java/src/main/java/ControlWorkJava/Win.txt");
        StringBuilder input = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                input.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
            PrintWriter pw = new PrintWriter(file);
            pw.printf("%s Id участника: %d Название выигрыша: %s ", input, count, toys.getNameToy());
            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}