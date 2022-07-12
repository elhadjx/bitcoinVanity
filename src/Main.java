import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;


public class Main {
    public static boolean found;
    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());
        found = false;
        System.out.println("\n");
        String toFind = args[0];
        boolean caseSensitive = Boolean.parseBoolean(args[1]);
        int threads = Integer.parseInt(args[2]);

        for (int i = 0; i < threads; i++) {
            MThread mThread = new MThread(toFind, caseSensitive);
            mThread.start();
        }

    }
}