DROP TABLE IF EXISTS TMGR_TASK;
CREATE TABLE TMGR_TASK(
    ID VARCHAR(32) NOT NULL,
    TASK_NAME VARCHAR(255),
    TASK_TYPE VARCHAR(255),
    TASK_NO VARCHAR(255),
    EXPECTED_NUM VARCHAR(255),
    ACTUAL_NUM VARCHAR(255),
    OVER_REASON VARCHAR(255),
    USE_FLAG VARCHAR(255),
    INPUT_DATE BIGINT,
    INPUT_TIME BIGINT,
    UPDATE_DATE BIGINT,
    UPDATE_TIME BIGINT,
    PRIMARY KEY (ID)
);

COMMENT ON TABLE TMGR_TASK IS '任务表';
COMMENT ON COLUMN TMGR_TASK.ID IS '主键';
COMMENT ON COLUMN TMGR_TASK.TASK_NAME IS '任务名';
COMMENT ON COLUMN TMGR_TASK.TASK_TYPE IS '任务类型';
COMMENT ON COLUMN TMGR_TASK.TASK_NO IS '任务编号';
COMMENT ON COLUMN TMGR_TASK.EXPECTED_NUM IS '预期记录数';
COMMENT ON COLUMN TMGR_TASK.ACTUAL_NUM IS '实际记录数';
COMMENT ON COLUMN TMGR_TASK.OVER_REASON IS '任务结束说明';
COMMENT ON COLUMN TMGR_TASK.USE_FLAG IS '任务是否启用';
COMMENT ON COLUMN TMGR_TASK.INPUT_DATE IS '插入日期';
COMMENT ON COLUMN TMGR_TASK.INPUT_TIME IS '插入时间';
COMMENT ON COLUMN TMGR_TASK.UPDATE_DATE IS '更新日期';
COMMENT ON COLUMN TMGR_TASK.UPDATE_TIME IS '更新时间';


DROP TABLE IF EXISTS TMGR_TASK_ITEM;
CREATE TABLE TMGR_TASK_ITEM(
    ID VARCHAR(32) NOT NULL,
    TASK_NAME VARCHAR(255),
    TASK_NO VARCHAR(255),
    ITEM_NO VARCHAR(255),
    HTTP_RESP_CODE VARCHAR(255),
    INPUT_DATE BIGINT,
    INPUT_TIME BIGINT,
    UPDATE_DATE BIGINT,
    UPDATE_TIME BIGINT,
    PRIMARY KEY (ID)
);

COMMENT ON TABLE TMGR_TASK_ITEM IS '任务项目表';
COMMENT ON COLUMN TMGR_TASK_ITEM.ID IS '主键';
COMMENT ON COLUMN TMGR_TASK_ITEM.TASK_NAME IS '任务名';
COMMENT ON COLUMN TMGR_TASK_ITEM.TASK_NO IS '任务编号';
COMMENT ON COLUMN TMGR_TASK_ITEM.ITEM_NO IS '项目编号';
COMMENT ON COLUMN TMGR_TASK_ITEM.HTTP_RESP_CODE IS '请求响应状态码';
COMMENT ON COLUMN TMGR_TASK_ITEM.INPUT_DATE IS '插入日期';
COMMENT ON COLUMN TMGR_TASK_ITEM.INPUT_TIME IS '插入时间';
COMMENT ON COLUMN TMGR_TASK_ITEM.UPDATE_DATE IS '更新日期';
COMMENT ON COLUMN TMGR_TASK_ITEM.UPDATE_TIME IS '更新时间';


DROP TABLE IF EXISTS TM_TASK_CONFIG;
CREATE TABLE TM_TASK_CONFIG(
    TNO VARCHAR(255) NOT NULL,
    NAME VARCHAR(255),
    TYPE VARCHAR(255),
    SUFFIX_LOCAL VARCHAR(255),
    SUFFIX_REMOTE VARCHAR(255),
    EXPECTED_NUM VARCHAR(255),
    INCREMENT VARCHAR(255),
    ACTUAL_NUM VARCHAR(255),
    STATE VARCHAR(255),
    DATA_STATE VARCHAR(255),
    EXPECTED_STATE VARCHAR(255),
    BATCH_STATE VARCHAR(255),
    MANUAL_STATE VARCHAR(255),
    INPUT_DATE BIGINT,
    INPUT_TIME BIGINT,
    UPDATE_DATE BIGINT,
    UPDATE_TIME BIGINT,
    PRIMARY KEY (TNO)
);

COMMENT ON TABLE TM_TASK_CONFIG IS '任务运行配置';
COMMENT ON COLUMN TM_TASK_CONFIG.TNO IS '编号';
COMMENT ON COLUMN TM_TASK_CONFIG.NAME IS '名称';
COMMENT ON COLUMN TM_TASK_CONFIG.TYPE IS '类型';
COMMENT ON COLUMN TM_TASK_CONFIG.SUFFIX_LOCAL IS '本地后缀';
COMMENT ON COLUMN TM_TASK_CONFIG.SUFFIX_REMOTE IS '远程后缀';
COMMENT ON COLUMN TM_TASK_CONFIG.EXPECTED_NUM IS '预期数';
COMMENT ON COLUMN TM_TASK_CONFIG.INCREMENT IS '增量数';
COMMENT ON COLUMN TM_TASK_CONFIG.ACTUAL_NUM IS '实际数';
COMMENT ON COLUMN TM_TASK_CONFIG.STATE IS '任务状态;0-任务建立（红色），1-任务开启（蓝色），2-任务结束（绿色），3-任务中断（红色）';
COMMENT ON COLUMN TM_TASK_CONFIG.DATA_STATE IS '数据状态;0-异常（红色），1-正常（绿色）';
COMMENT ON COLUMN TM_TASK_CONFIG.EXPECTED_STATE IS '预期状态;0-异常（红色），1-正常（绿色）';
COMMENT ON COLUMN TM_TASK_CONFIG.BATCH_STATE IS '批量状态;0-异常（红色），1-正常（绿色）';
COMMENT ON COLUMN TM_TASK_CONFIG.MANUAL_STATE IS '人工复核;0-异常（红色），1-正常（绿色）';
COMMENT ON COLUMN TM_TASK_CONFIG.INPUT_DATE IS '插入日期';
COMMENT ON COLUMN TM_TASK_CONFIG.INPUT_TIME IS '插入时间';
COMMENT ON COLUMN TM_TASK_CONFIG.UPDATE_DATE IS '更新日期';
COMMENT ON COLUMN TM_TASK_CONFIG.UPDATE_TIME IS '更新时间';


DROP TABLE IF EXISTS TM_TASK_CONFIG_HIS_LOG;
CREATE TABLE TM_TASK_CONFIG_HIS_LOG(
    ID VARCHAR(255) NOT NULL,
    TNO VARCHAR(255) NOT NULL,
    NAME VARCHAR(255),
    TYPE VARCHAR(255),
    SUFFIX_LOCAL VARCHAR(255),
    SUFFIX_REMOTE VARCHAR(255),
    EXPECTED_NUM VARCHAR(255),
    INCREMENT VARCHAR(255),
    ACTUAL_NUM VARCHAR(255),
    STATE VARCHAR(255),
    DATA_STATE VARCHAR(255),
    EXPECTED_STATE VARCHAR(255),
    BATCH_STATE VARCHAR(255),
    MANUAL_STATE VARCHAR(255),
    INPUT_DATE BIGINT,
    INPUT_TIME BIGINT,
    UPDATE_DATE BIGINT,
    UPDATE_TIME BIGINT,
    PRIMARY KEY (ID)
);

COMMENT ON TABLE TM_TASK_CONFIG_HIS_LOG IS '任务运行配置历史记录';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.ID IS '标识';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.TNO IS '编号';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.NAME IS '名称';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.TYPE IS '类型';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.SUFFIX_LOCAL IS '本地后缀';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.SUFFIX_REMOTE IS '远程后缀';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.EXPECTED_NUM IS '预期数';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.INCREMENT IS '增量数';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.ACTUAL_NUM IS '实际数';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.STATE IS '任务状态;0-任务建立（红色），1-任务开启（蓝色），2-任务结束（绿色），3-任务中断（红色）';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.DATA_STATE IS '数据状态;0-异常（红色），1-正常（绿色）';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.EXPECTED_STATE IS '预期状态;0-异常（红色），1-正常（绿色）';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.BATCH_STATE IS '批量状态;0-异常（红色），1-正常（绿色）';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.MANUAL_STATE IS '人工复核;0-异常（红色），1-正常（绿色）';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.INPUT_DATE IS '插入日期';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.INPUT_TIME IS '插入时间';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.UPDATE_DATE IS '更新日期';
COMMENT ON COLUMN TM_TASK_CONFIG_HIS_LOG.UPDATE_TIME IS '更新时间';


DROP TABLE IF EXISTS TM_TASK_RUN;
CREATE TABLE TM_TASK_RUN(
    ID VARCHAR(255) NOT NULL,
    TNO VARCHAR(255) NOT NULL,
    NAME VARCHAR(255),
    TYPE VARCHAR(255),
    SUFFIX VARCHAR(255),
    EXPECTED_NUM VARCHAR(255),
    INCREMENT VARCHAR(255),
    ACTUAL_NUM VARCHAR(255),
    RUN_STATE VARCHAR(255),
    INTERRUPT_STATE VARCHAR(255),
    INPUT_DATE BIGINT,
    INPUT_TIME BIGINT,
    UPDATE_DATE BIGINT,
    UPDATE_TIME BIGINT,
    PRIMARY KEY (ID)
);

COMMENT ON TABLE TM_TASK_RUN IS '任务运行实例';
COMMENT ON COLUMN TM_TASK_RUN.ID IS '标识';
COMMENT ON COLUMN TM_TASK_RUN.TNO IS '编号';
COMMENT ON COLUMN TM_TASK_RUN.NAME IS '名称';
COMMENT ON COLUMN TM_TASK_RUN.TYPE IS '类型';
COMMENT ON COLUMN TM_TASK_RUN.SUFFIX IS '后缀';
COMMENT ON COLUMN TM_TASK_RUN.EXPECTED_NUM IS '预期数';
COMMENT ON COLUMN TM_TASK_RUN.INCREMENT IS '增量数';
COMMENT ON COLUMN TM_TASK_RUN.ACTUAL_NUM IS '实际数';
COMMENT ON COLUMN TM_TASK_RUN.RUN_STATE IS '运行状态;0-异常（红色），1-正常（绿色）';
COMMENT ON COLUMN TM_TASK_RUN.INTERRUPT_STATE IS '中断状态;0-中断（红色），1-正常（绿色）';
COMMENT ON COLUMN TM_TASK_RUN.INPUT_DATE IS '插入日期';
COMMENT ON COLUMN TM_TASK_RUN.INPUT_TIME IS '插入时间';
COMMENT ON COLUMN TM_TASK_RUN.UPDATE_DATE IS '更新日期';
COMMENT ON COLUMN TM_TASK_RUN.UPDATE_TIME IS '更新时间';


DROP TABLE IF EXISTS TABLE_INDEX_VIEW;
CREATE TABLE TABLE_INDEX_VIEW(
    ID NUMBER(11) generated by default as IDENTITY,
    TB_NAME VARCHAR(255),
    TB_CREATE CLOB,
    TB_INDEX CLOB,
    PRIMARY KEY (ID)
);

COMMENT ON TABLE TABLE_INDEX_VIEW IS '表索引查看';
COMMENT ON COLUMN TABLE_INDEX_VIEW.ID IS '主键';
COMMENT ON COLUMN TABLE_INDEX_VIEW.TB_NAME IS '表名';
COMMENT ON COLUMN TABLE_INDEX_VIEW.TB_CREATE IS '建表明细';
COMMENT ON COLUMN TABLE_INDEX_VIEW.TB_INDEX IS '建表索引';


DROP TABLE IF EXISTS AUTH_LOG;
CREATE TABLE AUTH_LOG(
    ID VARCHAR(255) NOT NULL,
    USER_ID VARCHAR(255),
    REQ_TIME BIGINT,
    REQ_DATA CLOB,
    REQ_RESULT VARCHAR(255),
    PROCESS_TIME BIGINT,
    INPUT_DATE BIGINT,
    INPUT_TIME BIGINT,
    UPDATE_DATE BIGINT,
    UPDATE_TIME BIGINT,
    PRIMARY KEY (ID)
);

COMMENT ON TABLE AUTH_LOG IS '授权日志';
COMMENT ON COLUMN AUTH_LOG.ID IS '标识';
COMMENT ON COLUMN AUTH_LOG.USER_ID IS '用户Id';
COMMENT ON COLUMN AUTH_LOG.REQ_TIME IS '请求时间';
COMMENT ON COLUMN AUTH_LOG.REQ_DATA IS '请求数据';
COMMENT ON COLUMN AUTH_LOG.REQ_RESULT IS '请求结果';
COMMENT ON COLUMN AUTH_LOG.PROCESS_TIME IS '处理时间';
COMMENT ON COLUMN AUTH_LOG.INPUT_DATE IS '插入日期';
COMMENT ON COLUMN AUTH_LOG.INPUT_TIME IS '插入时间';
COMMENT ON COLUMN AUTH_LOG.UPDATE_DATE IS '更新日期';
COMMENT ON COLUMN AUTH_LOG.UPDATE_TIME IS '更新时间';