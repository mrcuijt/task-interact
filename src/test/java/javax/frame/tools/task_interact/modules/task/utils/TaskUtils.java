package javax.frame.tools.task_interact.modules.task.utils;

import com.sun.corba.se.impl.activation.CommandHandler;
import org.junit.Test;

import javax.frame.tools.task_interact.modules.commons.enums.CommandEnum;
import javax.frame.tools.task_interact.modules.commons.operator.CommandOperator;
import javax.frame.tools.task_interact.modules.commons.operator.OperatorHolder;
import javax.frame.tools.task_interact.modules.task.model.TaskItemObj;
import java.io.File;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskUtils {

    public static String fix() {
        String basePath = "D:/tools/upload/upload";
        Optional<File[]> optional = Optional.of(new File(basePath).listFiles());
        if (optional.isPresent()) {
            Stream<String> stream = Arrays.stream(optional.get())
                    .map(f -> f.getName())
                    .map(f -> f.substring(0, f.lastIndexOf(".")));
            List<String> collect = stream.collect(Collectors.toList());
            collect.sort(Comparator.reverseOrder());
            String max = collect.get(0);
            int i = Integer.parseInt(max);

            List<TaskItemObj> list = Tasks.generateTaskObj(null, i).getDatas();
            List<String> collect1 = list.stream()
                    .map(f -> f.getSerial())
                    .filter(f -> !collect.contains(f))
                    .collect(Collectors.toList());

            System.out.println(collect);

            System.out.println(collect1);

            List<TaskItemObj> collect2 = list.stream()
                    .filter((f) -> collect1.contains(f.getSerial()))
                    .collect(Collectors.toList());

            System.out.println(collect2);

            System.out.println(Tasks.toJsJsonObj(collect2));
        }
        return null;
    }

    public static void main(String[] args) {
        fix();
    }

    @Test
    public void demo() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        String timestamp = "1720686724000";
        LocalDateTime localDateTime = LocalDateTimeUtil.getLocalDateTime(timestamp);
        System.out.println(localDateTime);
    }

    @Test
    public void demo4(){
        CommandOperator operator = OperatorHolder.getOperator(CommandEnum.AUTH);
        System.out.println(operator.command());
        System.out.println(operator.process("input"));
    }

    @Test
    public void demo3(){
        String perfix = "2024-07-";
        List<String> collect = Stream.iterate(1, n -> n + 1).limit(17).map(n -> (100 + n + "").substring(1)).map(n -> perfix + n).collect(Collectors.toList());
        System.out.println(collect);

//        String str = "SELECT '@Date' AS CUR_DATE,\n" +
//                "       T2.CUST_ID AS CUST_ID,\n" +
//                "       T2.USER_NAME AS NAME,\n" +
//                "       T2.CERT_TYPE AS CERT_TYPE,\n" +
//                "       '' AS APP_NO,\n" +
//                "       T2.CERT_NO AS CERT_NO,\n" +
//                "       T1.BANK_CONT_NO  AS DUE_BILL_NO,\n" +
//                "       T1.APPL_SEQ AS TXN_SEQ_NO,\n" +
//                "       T1.LOAN_PRCP AS AMT\n" +
//                "  FROM BIZ_HIE_LOAN_INFO T1\n" +
//                "  LEFT JOIN CUS_INFO_HIE T2 ON T2.USER_ID  = T1.CUST_NO\n" +
//                " WHERE T1.DP_BATCH_DATE = '@Date'\n" +
//                " AND NOT EXISTS (\n" +
//                "    SELECT *\n" +
//                "      FROM BIZ_HIE_LOAN_CASH T3\n" +
//                "     WHERE T3.PAY_STATUS = 'FINI'\n" +
//                "        AND SUBSTR(T3.PAYMENT_TIME, 1, 10) = '@Date'\n" +
//                "       AND  T3.APP_NO= T1.BANK_CONT_NO\n" +
//                " )";

        String str = "SELECT '@Date' AS CUR_DATE,\n" +
                "       T2.CUST_ID AS CUST_ID,\n" +
                "       T2.USER_NAME AS NAME,\n" +
                "       T2.CERT_TYPE AS CERT_TYPE,\n" +
                "       T2.CERT_NO AS CERT_NO,\n" +
                "       T1.APP_NO AS APP_NO,\n" +
                "       T1.APP_NO AS DUE_BILL_NO,\n" +
                "       T1.APPLY_SEQ AS TXN_SEQ_NO,\n" +
                "       T1.LOAN_AMT AS AMT\n" +
                "  FROM BIZ_HIE_LOAN_CASH T1\n" +
                "  LEFT JOIN CUS_INFO_HIE T2 ON T2.CERT_NO = T1.CERT_NO\n" +
                " WHERE SUBSTR(T1.PAYMENT_TIME, 1, 10) = '@Date'\n" +
                "   AND T1.PAY_STATUS = 'FINI'\n" +
                "   AND NOT EXISTS (\n" +
                "        SELECT *\n" +
                "          FROM BIZ_HIE_LOAN_INFO T3\n" +
                "         WHERE T3.DP_BATCH_DATE = '@Date' \n" +
                "         AND T3.BANK_CONT_NO = T1.APP_NO\n" +
                "   )";

        StringBuffer strb = new StringBuffer();

        collect.stream().forEach(n -> strb.append(str.replace("@Date", n)).append("\nUNION\n"));

        System.out.println(strb);
    }
}
