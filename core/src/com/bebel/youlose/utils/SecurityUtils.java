package com.bebel.youlose.utils;

import com.badlogic.gdx.Gdx;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Outils pour la securité
 */
public class SecurityUtils {
    private static String key = "*5444423GeZuisUnEléfantBlanMalRazé2134541*";

    public synchronized static String encrypt(final String text) {
        final Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            return new String(encrypted);
        } catch (Exception e) {
            Gdx.app.error("ERREUR", "Erreur lors du cryptage");
        }
        return null;
    }

    public synchronized static String decrypt(final String text) {
        final Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] decrypted = cipher.doFinal(text.getBytes());
            return new String(decrypted);
        } catch (Exception e) {
            Gdx.app.error("ERREUR", "Erreur lors du decryptage");
        }
        return null;
    }
}
