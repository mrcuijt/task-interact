package javax.frame.tools.task_interact.modules.task.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.frame.tools.task_interact.modules.RootApplication;
import javax.frame.tools.task_interact.modules.task.enums.TaskGeneraterEnum;
import javax.frame.tools.task_interact.modules.task.model.TmTaskConfig;
import javax.frame.tools.task_interact.modules.task.utils.JacksonUtil;
import javax.frame.tools.task_interact.modules.task.utils.LocalDateTimeUtil;
import javax.frame.tools.task_interact.modules.task.utils.ResponseData;

import java.util.Map;
import java.util.TimeZone;

import static java.lang.System.setProperty;

//@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = RootApplication.class)

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TmTaskServiceTest {

    @BeforeClass
    public static void beforeClass() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        setProperty("spring.config.additional-location", "classpath:/config/*/");
    }

    @Resource
    TmTaskService tmTaskService;

    @Test
    public void add() throws Exception {
        TmTaskConfig temp = new TmTaskConfig();
        temp.setTno("101");
        temp.setExpectedNum("20");
        temp.setIncrement("20");
        temp.setActualNum("");
        temp.setSuffixRemote("webp");
        temp.setType(TaskGeneraterEnum.TASK_INIT.name());
        temp.setInputDate(LocalDateTimeUtil.getLocalDate());
        temp.setInputTime(LocalDateTimeUtil.getLocalDateTime());
        temp.setUpdateDate(LocalDateTimeUtil.getLocalDate());
        temp.setUpdateTime(LocalDateTimeUtil.getLocalDateTime());

        String json = JacksonUtil.jsonString(temp);
        Map map = JacksonUtil.parser(temp, Map.class);
        ResponseData add = tmTaskService.add(map);
        System.out.println(add);
    }
}
