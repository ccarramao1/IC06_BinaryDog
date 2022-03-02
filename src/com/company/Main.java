package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Dog[] dogPound = new Dog[10];
        String name, breed;
        int age, count = 0;

        File binaryFile = new File("Dogs.dat");
        System.out.println("Previously saved dogs from binary file");
        if(binaryFile.exists() && binaryFile.length() > 1l){
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                dogPound = (Dog[]) fileReader.readObject();
                while(dogPound[count] != null)
                    System.out.println(dogPound[count++]);
                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
        else
            System.out.println("[None, please enter new dog data]");

        do{
            System.out.print("\nPlease enter the dogs name: ");
            name = keyboard.nextLine();
            if (name.equalsIgnoreCase("quit"))
                break;
            System.out.print("Please enter dog's breed: ");
            breed =  keyboard.nextLine();
            System.out.print("Please enter dog's age: ");
            age = keyboard.nextInt();

            dogPound[count++] = new Dog(name, breed, age);

            keyboard.nextLine();
        }
        while (true);

        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            fileWriter.writeObject(dogPound);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }


    }
}
