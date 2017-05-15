package ru.agorbunov.restaurant.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import ru.agorbunov.restaurant.model.BaseEntity;
import ru.agorbunov.restaurant.util.exception.ArraysIncompatibilityException;
import ru.agorbunov.restaurant.util.exception.NotFoundException;

import java.time.LocalDateTime;

/**
 * Class for different validation methods
 */
public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static ResponseEntity<String> getErrorResponse(BindingResult result) {
        StringBuilder sb = new StringBuilder();
        result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
        return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public static void checkNew(BaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void checkEmpty(String string, String description){
        if ((string==null)||(string.trim().equals(""))){
            throw new IllegalArgumentException(description + " must be not empty");
        }
    }

    public static void checkEmpty(Double value, String description){
        if (value<=0.0){
            throw new IllegalArgumentException(description + " must be positive and more than zero");
        }
    }

    public static void checkEmpty(LocalDateTime dateTime, String description){
        if (dateTime==null){
            throw new IllegalArgumentException(description + " must be not empty");
        }
    }

    public static void checkArrCompatibility(int[] arr1,int[]arr2){
        if (arr1.length!=arr2.length){
            throw new ArraysIncompatibilityException("arrays must have equals length");
        }
    }


}
