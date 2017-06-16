package ru.agorbunov.restaurant.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import ru.agorbunov.restaurant.model.BaseEntity;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.model.Role;
import ru.agorbunov.restaurant.model.Status;
import ru.agorbunov.restaurant.util.exception.ArraysIncompatibilityException;
import ru.agorbunov.restaurant.util.exception.NotFoundException;
import ru.agorbunov.restaurant.util.exception.RefuseToUpdateException;
import ru.agorbunov.restaurant.web.AuthorizedUser;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Class for different validation methods
 */
public class ValidationUtil {

    private ValidationUtil() {
    }

    /*check that object was found in repository class
    * use if from repository returns false in case of not found*/
    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    /*check that object was found in repository class
    * use if from repository returns null in case of not found*/
    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    /*check that object was found or throw NotFoundException*/
    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    /*throws NotFoundException in case of not found object*/
    private static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    // TODO: 18.05.2017 remove if not use
    public static ResponseEntity<String> getErrorResponse(BindingResult result) {
        StringBuilder sb = new StringBuilder();
        result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
        return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /*check that entity is new*/
    public static void checkNew(BaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    /*check that String not equals null or not equals empty string*/
    public static void checkEmpty(String string, String description){
        if ((string==null)||(string.trim().equals(""))){
            throw new IllegalArgumentException(description + " must be not empty");
        }
    }

    /*check that Double bigger than zero */
    public static void checkEmpty(Double value, String description){
        if (value<=0.0){
            throw new IllegalArgumentException(description + " must be positive and more than zero");
        }
    }

    /*check that LocalDateTime not equals null */
    public static void checkEmpty(LocalDateTime dateTime, String description){
        if (dateTime==null){
            throw new IllegalArgumentException(description + " must be not empty");
        }
    }

    /*check that two arrays have equal size*/
    public static void checkArrCompatibility(int[] arr1,int[]arr2){
        if (arr1.length!=arr2.length){
            throw new ArraysIncompatibilityException("arrays must have equals length");
        }
    }

    public static void checkAcceptableUpdate(Order order){
        if (order.getStatus() != Status.ACCEPTED){
            Set<Role> roleSet = AuthorizedUser.get().getRoles();
            if (!roleSet.contains(Role.ROLE_ADMIN)){
                throw new RefuseToUpdateException("Updates refuse");
            }
        }
    }


}
