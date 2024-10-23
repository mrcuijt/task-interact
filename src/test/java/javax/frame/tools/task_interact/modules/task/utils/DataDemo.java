package javax.frame.tools.task_interact.modules.task.utils;

import com.sun.net.httpserver.Headers;
import org.junit.Before;
import org.junit.Test;

import javax.frame.tools.task_interact.modules.websocket.command.CommandEndpoint;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataDemo {

    byte[] items;

    String fileName;

    @Before
    public void setUp() {
        fileName = "server_1722843670697.dat";
        try (FileInputStream fis = new FileInputStream(new File(fileName));
             ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = fis.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, length);
            }
            items = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private static final String CONTENT_TYPE = "Content-type";
    private static final String PREFIX = "--";
    private static final String BOUNDARY = "boundary=";
    private static final String HTTP_BR = new String(new byte[]{0x0d, 0x0a});

    String headers = "multipart/form-data; ----WebKitFormBoundarym67wqWv4WbBR3UNj";

    @Test
    public void demo1(){
        try {
            String contentType = CONTENT_TYPE;
            String boundary = BOUNDARY;
            String[] ctType = headers.split("; ");
            String httpBr = HTTP_BR;
            if (MULTIPART_FORM_DATA.equals(ctType[0])) {
                String boundaryHeader = ctType[1];
                String prefix = PREFIX + boundaryHeader.replace(boundary, "");
                String suffix = prefix + PREFIX;
                // 基础信息输出
                System.out.println(boundary + prefix);
                DataItem dataItem = new DataItem(items);
                List<byte[]> datas = dataItem.datas(prefix, suffix);
                datas.stream().forEach(f -> System.out.println(new String(f)));
                System.out.println(dataItem.getRoomId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void demo() {
        String contentType = CONTENT_TYPE;
        String boundary = BOUNDARY;
        String[] ctType = headers.split("; ");
        String httpBr = HTTP_BR;
        if (MULTIPART_FORM_DATA.equals(ctType[0])) {
            String boundaryHeader = ctType[1];
            String prefix = PREFIX + boundaryHeader.replace(boundary, "");
            String suffix = prefix + PREFIX;

            // 基础信息输出
            System.out.println(boundary + prefix);
            // 处理数据流
            ByteArrayOutputStream head = new ByteArrayOutputStream();
            ByteArrayOutputStream header = new ByteArrayOutputStream();
            ByteArrayOutputStream content = new ByteArrayOutputStream();
            ByteArrayOutputStream foot = new ByteArrayOutputStream();
            ByteArrayInputStream bin = new ByteArrayInputStream(items);

            // 定位到每个换行（结尾），从每行的开头取到结尾
            byte[] buffer = new byte[1024];
            int prefixLength = prefix.getBytes().length;
            int suffixLength = suffix.getBytes().length;
            int cur = 0;
            int curItem = 0;
            int total = items.length;
            List<ByteArrayOutputStream> datas = new ArrayList<>();
            while (cur < total) {
                byte[] bytes = readLine(items, cur);
                String line = new String(Arrays.copyOf(bytes, bytes.length - 2));
                cur += bytes.length;
                System.out.println(line);

                boolean isPrefix = prefix.equals(line);
                boolean isSuffix = suffix.equals(line);

                // 开始行
                if (isPrefix) {
                    // 表头
                    bytes = readLine(items, cur);
                    String h1 = new String(Arrays.copyOf(bytes, bytes.length - 2));
                    cur += bytes.length;
                    // 表头
                    bytes = readLine(items, cur);
                    String h2 = new String(Arrays.copyOf(bytes, bytes.length - 2));
                    cur += bytes.length;
                    // 空行
                    bytes = readLine(items, cur);
                    String h3 = new String(Arrays.copyOf(bytes, bytes.length - 2));
                    cur += bytes.length;
                    // 数据行
                    bytes = readLine(items, cur);
                    String d1 = new String(Arrays.copyOf(bytes, bytes.length - 2));
                    cur += bytes.length;

                    isPrefix = prefix.equals(line);
                    isSuffix = suffix.equals(line);
                    boolean isData = !isPrefix && !isSuffix;

                    ByteArrayOutputStream data = new ByteArrayOutputStream();
                    while (isData) {
                        data.write(bytes, 0, bytes.length);
                        bytes = readLine(items, cur);
                        d1 = new String(Arrays.copyOf(bytes, bytes.length - 2));
                        isPrefix = prefix.equals(d1);
                        isSuffix = suffix.equals(d1);
                        isData = !isPrefix && !isSuffix;
                        if (isData) {
                            data.write(bytes, 0, bytes.length - 2);
                            cur += bytes.length;
                        }
                    }
                }

                if (isSuffix) {
                    break;
                }
            }

            for (; cur < total; cur++) {

                // 应包括行尾正行读取处理
                /*
                // 首行 ------WebKitFormBoundaryqYa8QeTS6DBAaiES
                // 表头 Content-Disposition: form-data; name="file"; filename="1021"
                // 表头 Content-Type: application/octet-stream
                // 空行
                // 数据 {"name":"a"}
                // 尾行 ------WebKitFormBoundaryqYa8QeTS6DBAaiES--
                */


//                // length = cur + 1
//                brLine.write(items[cur]);
//                // 行尾标识
//                boolean br = items[cur] == 0X0A && items[cur - 1] == 0X0D;
//                if (br) {
//                    byte[] btLine = brLine.toByteArray();
//                    String line = new String(Arrays.copyOf(btLine, btLine.length - 2));
//                    boolean isPrefix = prefix.equals(line);
//                    boolean isSuffix = suffix.equals(line);
//                    boolean isData = !isPrefix && !isSuffix;
//                    if (isPrefix)
//                        System.out.println("起始：" + line);
//                    if (isData)
//                        System.out.println("数据：" + line);
//                    if (isSuffix)
//                        System.out.println("结束：" + line);
//                    brLine = new ByteArrayOutputStream();
//                }

//                // 行尾标识
//                boolean br = items[cur] == 0X0A && items[cur - 1] == 0X0D;
//                // 结尾
//                boolean end = (br && cur == total);
//
//                // 行尾标识
//                if (br) {
//                    // 首行
//                    // 表头
//                    // 表头
//                    // 空行
//                    // 数据
//                    // 尾行
//
//                    // 数据长度 [0, cur+1]
//                    int length = cur + 1;
//                    byte[] buf = new byte[length];
//                    // 读取数据
//                    bin.read(buf, 0, buf.length);
//                    String h1 = new String(buf);
//                    // 数据开始行
//                    if (prefix.equals(h1)) {
//                        System.out.println(h1);
//                    }
//                }
            }
        }
    }

    public byte[] readLine(byte[] buffer, int cur) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (; cur < buffer.length; cur++) {
            baos.write(buffer[cur]);
            boolean br = items[cur] == 0X0A && items[cur - 1] == 0X0D;
            if (br) {
                break;
            }
        }
        return baos.toByteArray();
    }

    @Test
    public void demo2() throws Exception {
        Field declaredField = CommandEndpoint.class.getDeclaredField("clientSet");
        System.out.println(declaredField);
        declaredField.setAccessible(true);
        Object o = declaredField.get(null);
        System.out.println(o);
    }
}
