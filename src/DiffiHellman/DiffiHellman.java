package DiffiHellman;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DiffiHellman {
    public static void main(String[] args) {

        Integer p = primeNumberGiver();
        int g = (int) GetPRoot(p);

        Alice alice = new Alice(p, g);
    }

        public static void keyGenerator ( int g, int p, int a, int b){

            int A = quickExponentiation(g, a, p);
            int B = quickExponentiation(g, b, p);

            int S_A = quickExponentiation(B, a, p);
            int S_B = quickExponentiation(A, b, p);

            int key = quickExponentiation(a, b, p);
            System.out.println("Key: " + key);

            if (S_A == S_B) {
                System.out.println("Connection secured");
            } else {
                System.out.println("Connection not secured");
            }
        }

        public static Integer primeNumberGiver () {

            Random random = new Random();
            boolean probablePrime = false;
            int prime_number = 0;
            while (!probablePrime) {
                Integer integer = random.nextInt(65537);
                BigInteger bigInteger = BigInteger.valueOf(integer);
                probablePrime = bigInteger.isProbablePrime((int) Math.log(integer));
                prime_number = integer;
            }

            return prime_number;
        }

        public static long GetPRoot ( long p){
            for (long i = 2; i < p; i++)
                if (IsPRoot(p, i))
                    return i;
            return 0;
        }

        public static boolean IsPRoot ( long p, long a){
            long last = 1;
            Set<Long> set = new HashSet<>();
            for (long i = 0; i < p - 1; i++) {
                last = (last * a) % p;
                if (set.contains(last))
                    return false;
                set.add(last);
            }
            return true;
        }

        public static int secretValue ( int p){
            int a = (int) (p * Math.random());
            return a;
        }

        public static int quickExponentiation ( int g, int k, int p){
            int res = 1;
            for (long r = g; k > 0; k >>= 1, r = (r * r) % p) {
                if ((k & 1) != 0) {
                    res = (int) (res * r % p);
                }
            }
            return res;
        }
    }

