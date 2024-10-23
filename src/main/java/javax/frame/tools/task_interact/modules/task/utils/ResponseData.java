package javax.frame.tools.task_interact.modules.task.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.frame.tools.task_interact.modules.task.constants.WebConstants;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ResponseData<T> implements Serializable {

    private String code;

    private T data;

    private String msg;

    public ResponseData() {
    }

    public static <T> ResponseData<T> success(T t) {
        ResponseData<T> response = new ResponseData<>();
        response.setData(t);
        response.setMsg(WebConstants.SUCCESS);
        response.setCode(WebConstants.SUCCESS_CODE);
        return response;
    }

    public static <T> ResponseData<T> fail(T t) {
        ResponseData<T> response = new ResponseData<>();
        response.setData(t);
        response.setMsg(WebConstants.FAIL);
        response.setCode(WebConstants.FAIL_CODE);
        return response;
    }

}
