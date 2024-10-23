package javax.frame.tools.task_interact.modules.websocket.commons;

import org.junit.Test;

import javax.frame.tools.task_interact.modules.commons.enums.CommandEnum;
import javax.frame.tools.task_interact.modules.task.model.TmTaskConfig;
import javax.frame.tools.task_interact.modules.task.utils.JacksonUtil;

public class CmdReqDataTest {

    @Test
    public void demo() throws Exception {

        TmTaskConfig tmTaskConfig = new TmTaskConfig();
        tmTaskConfig.setTno("001");
        tmTaskConfig.setType("init");
        tmTaskConfig.setExpectedNum("200");
        tmTaskConfig.setActualNum("");
        tmTaskConfig.setIncrement("20");

        CmdReqData<TmTaskConfig> reqData = new CmdReqData<>();

        reqData.setCmd(CommandEnum.AUTH.name());

        reqData.setData(tmTaskConfig);

        String string = JacksonUtil.jsonString(reqData);

        System.out.println(string);

        //CmdReqData parser = JacksonUtil.parser(string, new CmdReqData<TmTaskConfig>().getClass());

        CmdReqData parser = JacksonUtil.parser(string, new CmdReqData().getClass());

        Object data = parser.getData();

        System.out.println(parser.getCmd());

        System.out.println(parser.getData());

    }

}
