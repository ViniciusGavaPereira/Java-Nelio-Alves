package services;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import entities.Circle;
import entities.Courses;
import entities.Customer;
import entities.Person;
import entities.Product;
import entities.Rectangle;
import entities.Register;
import entities.Shape;
import entities.Teacher;

public class Exercices {
    
    public static void generics(){
        Scanner sc = new Scanner(System.in);

        PrintService<Integer> ps = new PrintService<>();

        System.out.print("How many values? ");
        int n = sc.nextInt();

        for(int i = 0 ; i < n ; i++){
            int value = sc.nextInt();
            ps.addValue(value);
        }

        ps.print();

        System.out.println("First: " + ps.first());
        sc.close();

    }

    public static void gerericoDelimitado(){

        List<Product> list = new ArrayList<>();

        String path = "C:\\Users\\vini-\\Desktop\\Projetos\\Java-Nelio-Alves\\Capitulo18\\txt\\produtos.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            
            String line = br.readLine();

            while(line != null){
                String[] fields = line.split(",");
                list.add(new Product(fields[0],Double.parseDouble(fields[1])));
                line = br.readLine();


            }

            Product x = CalculationService.max(list);
            System.out.println("Max: ");
            System.out.println(x); 
        }catch(IOException io ){
            System.out.println("Error: " + io.getMessage());

        }
    }

    public void tiposCuringa(){

        // O '?' é um coringa, que permite fazer o uppcasting de tipos comuns para tipos genéricos
        //List<?> myObjs = new ArrayList<Object>();
       // List<Integer> myNumbers = new ArrayList<Integer>();
       // myObjs = myNumbers;

    }


    public static void tiposCuringaExemplo(){

        // O '?' é um coringa, que permite fazer o uppcasting de tipos comuns para tipos genéricos
        List<String> myInts = Arrays.asList("Maria", "Alex", "Bob");
        printList(myInts);

    }

    public static void printList(List<?> list){
        for(Object obj : list){
            System.out.println(obj);
        }
    }

    public static void genericosDelimitados(){
        	
		List<Shape> myShapes = new ArrayList<>();
		myShapes.add(new Rectangle(3.0, 2.0));
		myShapes.add(new Circle(2.0));
		
		List<Circle> myCircles = new ArrayList<>();
		myCircles.add(new Circle(2.0));
		myCircles.add(new Circle(3.0));
		
		System.out.println("Total area: " + totalArea(myCircles));
    }

    public static double totalArea(List<? extends Shape> list) {
		double sum = 0.0;
		for (Shape s : list) {
			sum += s.area();
		}
		return sum;
	}

    public static void validadorHashCode(){
        Person p1 = new Person("Maria", "maria@gmail.com");
        Person p2 = new Person("Maria", "maria@gmail.com");
        //Person p3 = new Person("Pedro", "maria@gmail.com");

        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
        System.out.println(p1.equals(p2));
    }

    public static void testeHashSet(){

        Set<String> set = new HashSet<>();

        set.add("Tv");
        set.add("Tablet");
        set.add("Notebook");

        set.removeIf(x -> x.length() >= 3);

        System.out.println(set.contains("Notebook"));

        for(String p : set){
            System.out.println(p);
        }
    }


    public static void testeHashSet2(){

        Set<Integer> a = new TreeSet<>(Arrays.asList(0,2,4,5,6,8,10));
        Set<Integer> b = new TreeSet<>(Arrays.asList(5,6,7,8,9,10));

        System.out.println("A: " + a);
        System.out.println("B: " + b);
        //Cria um TreeSet novo com as informações de A e acrescenta B sem repetições 
        Set<Integer> c = new TreeSet<>(a);
        c.addAll(b);
        System.out.println(c);

        //Vai remover de A, tudo que não tem em B
        Set<Integer> d = new TreeSet<>(a);
        d.retainAll(b);
        System.out.println(d);


        //Remove tudo que de A, que contem em B
        Set<Integer> e = new TreeSet<>(a);
        e.removeAll(b);
        System.out.println(e);



    }

    public static void exercicioResolvido1(){
        String fullpath = "C:\\Users\\vini-\\Desktop\\Projetos\\Java-Nelio-Alves\\Capitulo19\\src\\txt\\acessos.txt";

       
        try(BufferedReader br = new BufferedReader(new FileReader(fullpath));){

            Set<Register> set = new HashSet<>();

            //Lógica de leitura de arquivos .txt
            String line = br.readLine();
            while(line != null){
                String[] fields = line.split(" ");
                set.add(new Register(fields[0], fields[1]));
                line = br.readLine();
            }

            //Lista de usuários registrados
            for(Register rg : set){
                System.out.println(rg.toString());
            }

            //Lista de total de acessos com nomes DIFERENTES, para diferenciar por e-mail, é necessário alterar o hascode da classe 'Register' para separar por e-mail também
            System.out.println("Quantidade de acessos: " + set.size());

        }catch(FileNotFoundException fnf){
            System.out.println("Error: " + fnf.getMessage());
        }catch(IOException io){
            io.getMessage();
        }
       
    }


    public static void exercicioResolvido2(){
        Teacher t1 = new Teacher("Alex");
        Customer c1 = new Customer(1, "Gabriel");
        Customer c2 = new Customer(2, "Rafael");


        t1.setCoursesTeacher("Curso A");
        t1.setCoursesTeacher("Curso B");

        t1.setStudentToCourse("Curso A", c1);
        t1.setStudentToCourse("Curso B", c1);
        t1.setStudentToCourse("Curso B", c2);

        System.out.println(t1.getCourse("Curso B"));

    }
}
