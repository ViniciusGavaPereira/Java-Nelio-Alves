package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Account;
import entities.BusinessAccount;
import entities.Employee;
import entities.ImportedProduct;
import entities.OutsourcedEmployee;
import entities.Product;
import entities.SavingAccount;
import entities.Shape;
import entities.UsedProduct;
import entities.enums.Color;
import entities.Rectangle;
import entities.Circle;

public class Exercicio {

    public static void Explicacao(){
         System.out.println("Hello, World!");


        //UPCASTING
        Account acc2 = new BusinessAccount(3, "Bob", 0.0,450.0);
        Account acc3 = new SavingAccount(4, "Ana", 0.0,450.0);


        //DOWNCASTING
        BusinessAccount acc4 = (BusinessAccount)acc2;
        acc4.loan(100);

        //BusinessAccount acc5 = (BusinessAccount)acc3; <--- Não pode fazer o empréstimo, pois acc5 é uma conta Saving, não Bussines
        if(acc3 instanceof BusinessAccount){ // <--- Para fazer funcionar precisa testar neste formato
            System.out.println();
            System.out.println("Loan!");

        
        }

          if(acc3 instanceof SavingAccount){ 
            SavingAccount acc5 = (SavingAccount)acc3;
            acc5.updateBalance();
            System.out.println("Update!");
        
        }
    }

    public static void exercicio1(){
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        List<Employee> list = new ArrayList<>();


        System.out.println("Digite a quantidade de funcionários");
        int n = sc.nextInt();


        for(int i = 1; i <= n ; i++){
            
            System.out.println("Dados do # " + i +  "° funcionário ");
            System.out.println("O funcionário é terceirizado? (S/N)");
            char ch = sc.next().charAt(0);

            System.out.print("Nome: ");
            sc.nextLine();
            String name = sc.nextLine();

            System.out.print("Hour: ");
            int hours = sc.nextInt();

            System.out.print("Value per hour: ");
            double valuePerHour = sc.nextDouble();

            if(ch == 's'){
                System.out.print("Additional charge: ");
                double additionalCharge = sc.nextDouble();
                Employee emp = new OutsourcedEmployee(name, hours, valuePerHour, additionalCharge);
                list.add(emp);
                
            }else{
                Employee emp = new Employee(name, hours, valuePerHour);
                list.add(emp);
            }

        }

        System.out.println("\nPAGAMENTOS");
        for(Employee emp : list){
            System.out.println(emp.getName() + " - $ " + String.format("%.2f", emp.payment()));
        }

        sc.close();
    }

    public static void exercicio2(){

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        List<Shape> list  = new ArrayList<>();

        System.out.println("Enter the number of shapes: ");
        int n = sc.nextInt();

        for(int i = 1 ; i<=n ; i++){
            System.out.println("Shape # " + i + " data:");
            System.out.print("Rectangle or circle (R/C)? ");
            char ch = sc.next().charAt(0);
            System.out.println("Color (BLACK/BLUE/RED): ");
            Color color = Color.valueOf(sc.next());

            if(ch == 'r'){
                System.out.print("Width: ");
                double width = sc.nextDouble();
                System.out.print("Height: ");
                double height = sc.nextDouble();
                list.add(new Rectangle(color, width, height));
            }else{
                 System.out.print("Radius: ");
                double radius = sc.nextDouble();
                list.add(new Circle(color, radius));
            }

        }
        System.out.println("\nSHAPE AREAS");
        for(Shape shape : list){
            System.out.println(String.format("%.2f",shape.area()));
        }

        sc.close();

    }



    public static void exercicioFixacao1(){
        Scanner sc = new Scanner(System.in);
        List<Product> list = new ArrayList<>();

        System.out.print("Enter the number of products: ");
        int n = sc.nextInt(); 

        for(int i = 1 ; i <= n ; i++){
            System.out.println("Product #" + i + " data:");
            System.out.print("Common, used or imported (c/u/i)? ");
            char str = sc.next().charAt(0);

            System.out.print("Name: ");
            String name = sc.next();

            System.out.print("Price: ");
            double price = sc.nextInt();

            if(str == 'c'){
                list.add(new Product(name, price));
            }else if(str == 'u'){

                System.out.print("Manufacture date (DD/MM/YYYY): ");
                LocalDate date = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                list.add(new UsedProduct(name,price, date));

            }else if(str == 'i'){

                System.out.print("CustomsFee: ");
                double custom = sc.nextDouble();
                list.add(new ImportedProduct(name, price, custom));
                
            }
        }

        System.out.println();
            System.out.println("Price tag:");
            for (Product prod : list) {
                System.out.println(prod.priceTag());
            }
            
            sc.close();
    }

}
