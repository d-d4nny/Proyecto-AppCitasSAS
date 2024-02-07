package appCitas.AppCitasSASv2.utils;

import java.util.Base64;

public class ImageUtils {

	public static String convertToBase64(byte[] data) {
        if (data != null && data.length > 0) {
            return Base64.getEncoder().encodeToString(data);
        }
        return null;
    }

    public static byte[]  convertToByteArray(String base64String) {
        if (base64String != null && !base64String.isEmpty()) {
            return Base64.getDecoder().decode(base64String);
        }
        return null;
    }
}
