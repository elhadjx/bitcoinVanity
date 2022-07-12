
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;

public class Wallet {
    private String WalletPrivateKey;
    private String WalletPublicKey;
    private String WalletAddress;

    public Wallet() {
        try {
            generate();
        } catch (Exception e ){

        }
        //System.out.println(this);

    }

    public void generate() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchProviderException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        keyGen.initialize(ecSpec);
        KeyPair keyPair = keyGen.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        ECPrivateKey ecPrivateKey = (ECPrivateKey) privateKey;
        String sepvt = adjustTo64(ecPrivateKey.getS().toString(16)).toUpperCase();
        //System.out.println("s[" + sepvt.length() + "]: " + sepvt);

        WalletPrivateKey = sepvt;

        ECPublicKey ecPublicKey = (ECPublicKey)publicKey;
        ECPoint pt = ecPublicKey.getW();
        String sx = adjustTo64(pt.getAffineX().toString(16)).toUpperCase();
        String sy = adjustTo64(pt.getAffineY().toString(16)).toUpperCase();
        String bcPub = "04" + sx + sy;

        WalletPublicKey = bcPub;

        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] s1 = sha.digest(bcPub.getBytes("UTF-8"));

        MessageDigest rmd = MessageDigest.getInstance("RipeMD160", "BC");
        byte[] r1 = rmd.digest(s1);

        byte[] r2 = new byte[r1.length + 1];
        r2[0] = 0;
        for (int i = 0 ; i < r1.length ; i++) r2[i+1] = r1[i];
        byte[] s2 = sha.digest(r2);
        byte[] s3 = sha.digest(s2);

        byte[] a1 = new byte[25];
        for (int i = 0 ; i < r2.length ; i++) a1[i] = r2[i];
        for (int i = 0 ; i < 5 ; i++) a1[20 + i] = s3[i];


        WalletAddress = Base58.encode(a1);

    }

    public String getWalletAddress() {
        return WalletAddress;
    }

    public String getWalletPrivateKey() {
        return WalletPrivateKey;
    }

    public String getWalletPublicKey() {
        return WalletPublicKey;
    }

    private String adjustTo64(String s) {
        switch(s.length()) {
            case 62: return "00" + s;
            case 63: return "0" + s;
            case 64: return s;
            default:
                throw new IllegalArgumentException("not a valid key: " + s);
        }
    }

    public String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02x", aByte));
            // upper case
            // result.append(String.format("%02X", aByte));
        }
        return result.toString();
    }

    @Override
    public String toString() {
        String toReturn = new String("");
        toReturn = toReturn.concat("Private Key:" + WalletPrivateKey);
        toReturn = toReturn.concat("\nPublic Key: " + WalletPublicKey);
        toReturn = toReturn.concat("\nAddress: " + WalletAddress);
        return toReturn;
    }
}
