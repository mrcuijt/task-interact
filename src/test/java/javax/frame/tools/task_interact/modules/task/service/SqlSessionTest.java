package javax.frame.tools.task_interact.modules.task.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.frame.tools.task_interact.modules.RootApplication;
import javax.frame.tools.task_interact.modules.task.model.TableIndexView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Stream;

import static java.lang.System.setProperty;

//@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = RootApplication.class)

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SqlSessionTest {

    @BeforeClass
    public static void beforeClass() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        setProperty("spring.config.additional-location", "classpath:/config/*/");
    }

    @Resource
    SqlSession sqlSession;

    @Test
    public void demo() {
        String a = "javax.frame.tools.task_interact.modules.task.mapper.TableIndexViewMapper";
        TableIndexView view = new TableIndexView();
        view.setId(1L);
        view.setTbName("name");
        view.setTbCreate("create");
        sqlSession.insert(a + ".insert", view);
        TableIndexView o = sqlSession.selectOne(a + ".selectByPrimaryKey", 1L);
        System.out.println(o.getTbName());
        System.out.println(o.getTbCreate());
    }

    @Test
    public void demo3() {
        String a = "javax.frame.tools.task_interact.modules.task.mapper.TableIndexViewMapper";
        long count = 10000 + 1;
        String br = "\n";
        StringBuffer strb = new StringBuffer();
        Stream.iterate(count, n -> n + 1)
                .limit(155)
                .map(n -> (TableIndexView) sqlSession.selectOne(a + ".selectByPrimaryKey", n))
                .filter(n -> n.getTbIndex()!=null && !n.getTbIndex().trim().isEmpty())
                .forEach(n -> {
                    strb.append(n.getId());
                    strb.append(br);
                    strb.append(n.getTbName());
                    strb.append(br);
//                    strb.append(n.getTbCreate());
//                    strb.append(br);
                    strb.append(n.getTbIndex());
                    strb.append(br);
                });
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("D:\\workspace-hanya2\\韩亚银行客户文档\\00 数据字典\\index.txt")))) {
            bw.write(strb.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void demo2() throws Exception {
        String[] tables = {"BAT_JD_ACCOUNTING_TEMP",
                "BAT_JD_ACCOUNTING_HIS",
                "BIZ_JD_ACCOUNTING",
                "BAT_JD_CANLACCOUNTING_TEMP",
                "BAT_JD_CANLACCOUNTING_HIS",
                "BIZ_JD_CANLACCOUNTING",
                "BAT_JD_NCANLACCOUNTING_TEMP",
                "BAT_JD_NCANLACCOUNTING_HIS",
                "BIZ_JD_NCANLACCOUNTING",
                "BAT_JD_CUS_INFO_TEMP",
                "BAT_JD_CUS_INFO_HIS",
                "BIZ_JD_CUS_INFO",
                "BAT_JD_CUS_CREDIT_TEMP",
                "BAT_JD_CUS_CREDIT_HIS",
                "BIZ_JD_CUS_CREDIT",
                "BAT_JD_QUOTA_ADJUST_TEMP",
                "BAT_JD_QUOTA_ADJUST_HIS",
                "BIZ_JD_QUOTA_ADJUST",
                "BAT_JD_LOAN_INFO_TEMP",
                "BAT_JD_LOAN_INFO_HIS",
                "BIZ_JD_LOAN_INFO",
                "BAT_JD_LOAN_PLAN_TEMP",
                "BAT_JD_LOAN_PLAN_HIS",
                "BIZ_JD_LOAN_PLAN",
                "BAT_JD_LOAN_FLOW_TEMP",
                "BAT_JD_LOAN_FLOW_HIS",
                "BIZ_JD_LOAN_FLOW",
                "BAT_JD_CUS_CHANGE_TEMP",
                "BAT_JD_CUS_CHANGE_HIS",
                "BIZ_JD_CUS_CHANGE",
                "BAT_JD_FEE_ADJUST_TEMP",
                "BAT_JD_FEE_ADJUST_HIS",
                "BIZ_JD_FEE_ADJUST",
                "BAT_JD_LOAN_REPAYMENT_TEMP",
                "BAT_JD_LOAN_REPAYMENT_HIS",
                "BIZ_JD_LOAN_REPAYMENT",
                "BAT_JD_COM_FLOW_TEMP",
                "BAT_JD_COM_FLOW_HIS",
                "BIZ_JD_COM_FLOW",
                "BAT_HX_CUS_CHANGEINFO_HIS",
                "BIZ_HX_CUS_CHANGEINFO",
                "BAT_PPD_ACCOUNTING_TEMP",
                "BAT_PPD_ACCOUNTING_HIS",
                "BIZ_PPD_ACCOUNTING",
                "BAT_PPD_PLAN_LOAN_TEMP",
                "BAT_PPD_PLAN_LOAN_HIS",
                "BIZ_PPD_PLAN_LOAN",
                "BAT_PPD_PLAN_REPAY_TEMP",
                "BAT_PPD_PLAN_REPAY_HIS",
                "BIZ_PPD_PLAN_REPAY",
                "BAT_PPD_REPAYBATCH_TEMP",
                "BAT_PPD_REPAYBATCH_HIS",
                "BIZ_PPD_REPAYBATCH",
                "BAT_PPD_LOAN_TEMP",
                "BAT_PPD_LOAN_HIS",
                "BIZ_PPD_LOAN",
                "BAT_PPD_LOAN_CHECKFILE",
                "BAT_LX_PAYMENT_TEMP",
                "BAT_LX_PAYMENT_HIS",
                "BIZ_LX_PAYMENT",
                "BAT_LX_REPAY_PLAN_TEMP",
                "BAT_LX_REPAY_PLAN_HIS",
                "BIZ_LX_REPAY_PLAN",
                "BAT_LX_LOAN_TEMP",
                "BAT_LX_LOAN_HIS",
                "BIZ_LX_LOAN",
                "BAT_LX_REPAY_TEMP",
                "BAT_LX_REPAY_HIS",
                "BIZ_LX_REPAY",
                "BAT_LX_REPAY_ITEM_TEMP",
                "BAT_LX_REPAY_ITEM_HIS",
                "BIZ_LX_REPAY_ITEM",
                "BAT_LX_ACCOUNTING_TEMP",
                "BAT_LX_ACCOUNTING_HIS",
                "BIZ_LX_ACCOUNTING",
                "BAT_LX_PAYMENT_ACCOUNT_TEMP",
                "BAT_LX_PAYMENT_ACCOUNT_HIS",
                "BIZ_LX_PAYMENT_ACCOUNT",
                "BIZ_CUST_CREDITCHECK",
                "BIZ_JANLIMIT_INFO",
                "BAT_XM_LOAN_RATE_TEMP",
                "BAT_XM_LOAN_RATE_HIS",
                "BIZ_XM_LOAN_RATE",
                "BAT_XM_FUND_DETAIL_TEMP",
                "BAT_XM_FUND_DETAIL_HIS",
                "BIZ_XM_FUND_DETAIL",
                "BAT_XM_LOAN_REPAY_TEMP",
                "BAT_XM_LOAN_REPAY_HIS",
                "BIZ_XM_LOAN_REPAYITEM",
                "BAT_XM_REPAYMENT_TEMP",
                "BAT_XM_REPAYMENT_HIS",
                "BIZ_XM_REPAYMENT",
                "BAT_XM_COMPENSATORYDETAIL_TEMP",
                "BAT_XM_COMPENSATORYDETAIL_HIS",
                "BIZ_XM_COMPENSATORYDETAIL",
                "BAT_XM_REPAY_PLAN_TEMP",
                "BAT_XM_REPAY_PLAN_HIS",
                "BIZ_XM_REPAY_PLAN",
                "BAT_XM_LOAN_DETAIL_TEMP",
                "BAT_XM_LOAN_DETAIL_HIS",
                "BIZ_XM_LOAN_DETAIL",
                "BIZ_XM_PAYACCOUNT_INFO",
                "BAT_XY_ACCOUNTING_TEMP",
                "BAT_XY_ACCOUNTING_HIS",
                "BIZ_XY_ACCOUNTING",
                "BAT_XY_REPAY_PLAN_TEMP",
                "BAT_XY_REPAY_PLAN_HIS",
                "BIZ_XY_REPAY_PLAN",
                "BAT_XY_LOAN_DETAIL_TEMP",
                "BAT_XY_LOAN_DETAIL_HIS",
                "BIZ_XY_LOAN_DETAIL",
                "BAT_XY_LOAN_INFO_TEMP",
                "BAT_XY_LOAN_INFO_HIS",
                "BIZ_XY_LOAN_INFO",
                "BAT_XY_REPAY_DETAIL_TEMP",
                "BAT_XY_REPAY_DETAIL_HIS",
                "BIZ_XY_REPAY_DETAIL",
                "BAT_JD_LOAN_CHECKFILE",
                "BIZ_JD_PAYACCOUNT_INFO",
                "BIZ_JD_PAYCHECK",
                "BAT_HIE_LOAN_INFO_TEMP",
                "BAT_HIE_LOAN_INFO_HIS",
                "BIZ_HIE_LOAN_INFO",
                "BAT_HIE_REPAY_PLAN_TEMP",
                "BAT_HIE_REPAY_PLAN_HIS",
                "BIZ_HIE_REPAY_PLAN",
                "BAT_HIE_REPAY_DETAIL_TEMP",
                "BAT_HIE_REPAY_DETAIL_HIS",
                "BIZ_HIE_REPAY_DETAIL",
                "BAT_HIE_LOAN_DETAIL_TEMP",
                "BAT_HIE_LOAN_DETAIL_HIS",
                "BIZ_HIE_LOAN_DETAIL",
                "BIZ_HIE_REPAY_CUSREMAINAMT",
                "BAT_YXI_FUND_DETAIL_TEMP",
                "BAT_YXI_FUND_DETAIL_HIS",
                "BIZ_YXI_FUND_DETAIL",
                "BAT_YXI_LOAN_REPAY_TEMP",
                "BAT_YXI_LOAN_REPAY_HIS",
                "BIZ_YXI_LOAN_REPAYITEM",
                "BAT_YXI_REPAYMENT_TEMP",
                "BAT_YXI_REPAYMENT_HIS",
                "BIZ_YXI_REPAYMENT",
                "BAT_YXI_LOAN_DETAIL_TEMP",
                "BAT_YXI_LOAN_DETAIL_HIS",
                "BIZ_YXI_LOAN_DETAIL",
                "BAT_YXI_REPAY_PLAN_TEMP",
                "BAT_YXI_REPAY_PLAN_HIS",
                "BIZ_YXI_REPAY_PLAN",
                "BAT_YXI_ACCOUNTING_TEMP",
                "BAT_YXI_ACCOUNTING_HIS",
                "BIZ_YXI_ACCOUNTING",
                "BAT_YXI_REPAY_APPLY_TEMP",
                "BAT_YXI_REPAY_APPLY_HIS",
                "BIZ_YXI_REPAY_APPLY",
                "CRD_UNION_APP_INFO_JB",
                "BIZ_PPD_LOAN_CASH"
        };
        //tables = new String[] {};
        String jdbc = "jdbc:oracle:thin:@172.28.3.157:1521:loandbtest";
        String usename = "cmis2019";
        String password = "cmis2019";
        String sql = "select dbms_metadata.get_ddl('TABLE','@Table') from dual";

        String a = "javax.frame.tools.task_interact.modules.task.mapper.TableIndexViewMapper";


        long count = 10000 + 1;
        for (String table : tables) {
            try (Connection connection = DriverManager.getConnection(jdbc, usename, password)) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql.replace("@Table", table));
                     ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String string = resultSet.getString(1);
                        //System.out.println(string);
                        TableIndexView view = new TableIndexView();
                        view.setId(count);
                        view.setTbName(table);
                        view.setTbCreate(string);
                        view.setTbIndex("");
                        getIndexs(connection, table, view);
                        sqlSession.insert(a + ".insert", view);
                        count++;
                    } else {
                        System.out.println("WWWWWWWWWWWWWWWWWWW" + table);
                    }
//                    break;
                }
            }
        }
    }

    public void getIndexs(Connection connection, String table, TableIndexView view) throws Exception {
        String sql = "select index_name from user_indexes where table_name = '@Table'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql.replace("@Table", table));
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String string = resultSet.getString(1);
                getIndex(connection, string, view);
            }
        }
    }

    public void getIndex(Connection connection, String index, TableIndexView view) throws Exception {
        String sql = "select  dbms_metadata.get_ddl('INDEX', '@Index' )  from dual";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql.replace("@Index", index));
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String string = resultSet.getString(1);
                view.setTbIndex(view.getTbIndex() + "\n" + index + "\n" + string + "\n");
            }
        }
    }
}
