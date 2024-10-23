package javax.frame.tools.task_interact.modules.task.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Slf4j
@Validated
@Component
@Configuration
@ConfigurationProperties(
        prefix = "springboot.fileupload"
)
public class FileUploadConfiguration {

    public static String UPLOAD_DIRECTORY;

    public static String UPLOAD_BASE_PATH;

    @NotNull(message = "缺少属性配置：springboot.fileupload.base-path")
    public String getBasePath() {
        return FileUploadConfiguration.UPLOAD_BASE_PATH;
    }

    public void setBasePath(String basePath) {
        FileUploadConfiguration.UPLOAD_BASE_PATH = basePath;
        log.info("springboot.fileupload.directory:{}", FileUploadConfiguration.UPLOAD_BASE_PATH);
    }

    @NotNull(message = "缺少属性配置：springboot.fileupload.base-path")
    public String getDirectory() {
        return FileUploadConfiguration.UPLOAD_DIRECTORY;
    }

    public void setDirectory(String directory) {
        FileUploadConfiguration.UPLOAD_DIRECTORY = directory;
        log.info("springboot.fileupload.directory:{}", FileUploadConfiguration.UPLOAD_DIRECTORY);
    }

    private boolean enable = false;

    @NotNull(message = "缺少属性配置：springboot.fileupload.enable")
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
        log.info("springboot.fileupload.enable:{}", enable);
    }
}
