package adis.group5.boardingHouse.model.response;

import java.io.Serializable;

public class DataResponse<T> implements Serializable {
    boolean success;
    String message;
    T result;

    public DataResponse(boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }
}