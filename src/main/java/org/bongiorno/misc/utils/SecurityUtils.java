package org.bongiorno.misc.utils;

import org.bongiorno.misc.utils.functions.BytesToHex;
import org.springframework.security.crypto.codec.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class SecurityUtils {


    /**
     * Returns a BigInteger representation of a hash of 'val'. The strategy is as per java MessageDigest
     *
     * @param val      the value to hash
     * @param strategy currently supported a MD5 and SHA-256
     * @return a BigInteger whose toString will give a non-signed hex representation of this hash
     * @throws UnsupportedOperationException if there was a problem performing hashing
     */
    public static String hashTo(String val, String strategy) throws UnsupportedOperationException {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance(strategy);
            m.reset();
            m.update(val.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }


        return new String(Hex.encode(m.digest()));
    }

    /**
     * Returns a BigInteger representation of a hash of 'val'. The strategy is as per java MessageDigest
     *
     * @param val the value to hash
     * @return a String rep of the hashed value
     * @throws UnsupportedOperationException if there was a problem performing hashing
     *                                       strategy defaults to MD5 hashing
     */
    public static String hashToMD5(String val) throws UnsupportedOperationException {
        return hashTo(val, "MD5");
    }

    /**
     * Returns a BigInteger representation of a hash of 'val'. The strategy is as per java MessageDigest
     *
     * @param val the value to hash
     * @return a BigInteger whose toString will give a non-signed hex representation of this hash
     * @throws UnsupportedOperationException if there was a problem performing hashing
     *                                       strategy defaults to SHA256 hashing
     */
    public static String hashToSHA256(String val) throws UnsupportedOperationException {
        return hashTo(val, "SHA-256");
    }

    public static String hashToSHA1(String val) throws UnsupportedOperationException {
        return hashTo(val, "SHA-1");
    }

}
