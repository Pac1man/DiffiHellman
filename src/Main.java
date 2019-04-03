public class Main {
    public static void main(String[] args) {

        int p = (int) DiffiHellman.primeNumberGiver();
        int g = (int) DiffiHellman.GetPRoot(p);
        int a = DiffiHellman.secretValue(p);
        int b = DiffiHellman.secretValue(p);

        DiffiHellman.keyGenerator(g, p, a, b);

    }

}


