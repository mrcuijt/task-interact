package javax.frame.tools.task_interact.modules.task.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@CrossOrigin
@RequestMapping("sms")
public class SmsController {

    // 返回参数
    public static final String COMMIT_SUCCESS = "00";
    public static final String COMMIT_FAIL = "99";

    @GetMapping("o1")
    public void o1(HttpServletResponse response,
                    @RequestParam("phone") String phone,
                    @RequestParam("message") String message) throws Exception {
        log.info("phone:" + phone);
        log.info("message:" + message);
        response.getOutputStream().write(COMMIT_SUCCESS.getBytes("UTF-8"));
    }

    @GetMapping("o2")
    public void o2(HttpServletResponse response,
                   @RequestParam("phone") String phone,
                   @RequestParam("message") String message) throws Exception {
        log.info("phone:" + phone);
        log.info("message:" + message);
        response.getOutputStream().write(COMMIT_FAIL.getBytes("UTF-8"));
    }

    @GetMapping("o3")
    public void o3(HttpServletResponse response,
                   @RequestParam("phone") String phone,
                   @RequestParam("message") String message) throws Exception {
        log.info("phone:" + phone);
        log.info("message:" + message);
        Thread.sleep(31 * 1000);
        response.getOutputStream().write(COMMIT_FAIL.getBytes("UTF-8"));
    }

}
