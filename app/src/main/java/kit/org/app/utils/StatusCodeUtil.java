package kit.org.app.utils;

import kong.unirest.HttpStatus;

public class StatusCodeUtil {

    public static String getStatusCodeByErrorMessage(Exception e) {
        String message = e.getMessage();
        if (message.contains("Connection reset")) {
            return String.valueOf(HttpStatus.REQUEST_TIMEOUT);
        } else if (message.contains("Name or service not known")) {
            return String.valueOf(HttpStatus.NOT_FOUND);
        } else {
            return String.valueOf(HttpStatus.BAD_REQUEST);
        }
    }
}
