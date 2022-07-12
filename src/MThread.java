


public class MThread extends Thread {

    String toFind = "";
    boolean caseSensitive = false;

    public MThread(String toFind, boolean caseSensitive) {
        this.toFind = toFind;
        this.caseSensitive = caseSensitive;
    }

    public void run(){

        try {
            Wallet wallet = new Wallet();
            String address = wallet.getWalletAddress();

            if (!caseSensitive) {
                toFind = toFind.toLowerCase();
                address = address.toLowerCase();
            }
            while (!address.toLowerCase().startsWith(toFind, 1) && !Main.found) {

                wallet = new Wallet();

                if (wallet.getWalletAddress() != null) {
                    address = wallet.getWalletAddress();

                }
                if (!Main.found) {
                    System.out.print(address + "\r");
                }
            }
            if (address.toLowerCase().startsWith(toFind, 1)) {
                Main.found = true;
                System.out.println("**************");
                System.out.println("Private Key: " + wallet.getWalletPrivateKey());
                System.out.println("Public Key: " + wallet.getWalletPublicKey());
                System.out.println("Address: " + address);
            }


        } catch (Exception ignored){

        }

    }
}
