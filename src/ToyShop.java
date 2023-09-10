import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyShop {
    private List<Toy> toys;
    public  ToyShop(){
        toys = new ArrayList<>();
    }
    public  void addToy(Toy toy){
        toys.add(toy);
    }
    public void setToyWeight(int id, double weight){
        for (Toy toy : toys){
            if(toy.getId() == id){
                toy.setWeight(weight);
                break;
            }
        }
    }
    public void drawToys(){
        String filePath = "toys.txt";
        if(toys.isEmpty()){
            System.out.println("В магазине нет игрушек");
            return;
        }
        double totalWeight = 0;
        for (Toy toy : toys){
            totalWeight += toy.getWeight();
        }
        Random random = new Random();
        double randomNumber = random.nextDouble() * totalWeight;
        double currentWeight = 0;
        Toy chosenToy = null;
        for (Toy toy : toys){
            currentWeight += toy.getWeight();
            if (randomNumber <= currentWeight){
                chosenToy = toy;
                break;
            }
        }
        if(chosenToy != null){
            System.out.println("Ваш выигрыш: " + chosenToy.getName());
            saveToyToFile(chosenToy, filePath);
            if (chosenToy.getQuantity() > 0){
                chosenToy.setQuantity(chosenToy.getQuantity() - 1);
            }else {
                System.out.println("Очень жаль, такой игрушки больше нет");
                }
        }else {
            System.out.println("Упс, ничего не выиграли.");
        }
    }
    public void saveToyToFile(Toy chosenToy, String filePath){
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(chosenToy.getName() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл игрушки");
        }
    }
}
