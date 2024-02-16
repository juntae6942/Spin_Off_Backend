package Trendithon.SpinOff.global.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseResult<T> {
    private final boolean success;
    private final String message;
    private final T data;

    public static <T> ResponseResult<T> success(String message, T data) {
        return new ResponseResult<>(true, message, data);
    }

    public static <T> ResponseResult<T> failure(String message) {
        return new ResponseResult<>(false, message, null);
    }
}
