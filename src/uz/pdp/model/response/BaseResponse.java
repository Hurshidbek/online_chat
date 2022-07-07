package uz.pdp.model.response;

public interface BaseResponse {
    String SUCCESS = "completed successfully";

    String ERROR_USER_IS_EXIST = "user is already exist";
    String ERROR_USER_NOT_FOUND = "user not found";

    String ERROR_GROUP_NAME_EXIST = "group name is not available";
    String ERROR_USER_IN_GROUP = "user is exist in group";
}
