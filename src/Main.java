import java.math.BigInteger;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int p = prime_number_giver();
      //  System.out.println("Alice input a");
      //  int a = sc.nextInt();
      //  System.out.println("Bob input b");
        // int b = sc.nextInt();

       // key_generator(g, p, a, b);

    }




    private static void key_generator(int g, int p, int a, int b){

        int A = (int) (Math.pow(g, a) % p);
        int B = (int) (Math.pow(g, b) % p);

        int S_A = (int) (Math.pow(B, a) % p);
        int S_B = (int) (Math.pow(A, b) % p);

        int key = (int) (Math.pow(a, b) % p);
        System.out.println("Key: " + key);

        if (S_A == S_B){
            System.out.println("Connection secured");
        }
        else {
            System.out.println("Connection not secured");
        }
    }

    private static Integer prime_number_giver(){

        Random random = new Random();
        boolean probablePrime = false;
        int prime_number = 0;
        while (!probablePrime){
            Integer integer = random.nextInt(65537);
            BigInteger bigInteger = BigInteger.valueOf(integer);
            probablePrime = bigInteger.isProbablePrime((int) Math.log(integer));
            prime_number = integer;
        }

        return prime_number;
    }

    private static int quick_exponentiation() {
        int k = 2;
        for (int i = 0; i < 100; i++){
            k = (int) (k * Math.pow(k, i) % i);
        }
    }
}


