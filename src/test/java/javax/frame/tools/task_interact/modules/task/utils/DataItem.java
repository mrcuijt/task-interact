package javax.frame.tools.task_interact.modules.task.utils;

import lombok.Getter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class DataItem {

    public DataItem(byte[] datas) {
        this.cur = 0;
        this.datas = datas;
        this.total = datas.length;
    }

    private int cur;

    private int total;

    private byte[] datas;

    private String roomId;

    public List<byte[]> datas(String prefix, String suffix) {
        List<byte[]> items = new ArrayList<>();
        byte[] bytes = line();
        String line = new String(Arrays.copyOf(bytes, bytes.length - 2));
        boolean isPrefix = prefix.equals(line);
        boolean isSuffix = suffix.equals(line);
        while (cur < total) {
            // 开始行
            if (isPrefix) {
                // 表头
                // Content-Disposition: form-data; name="file"; filename="102"
                bytes = line();
                String h1 = new String(Arrays.copyOf(bytes, newLength(bytes)));
                roomId = (roomId != null) ? roomId : fileName(h1);
                // 表头
                // Content-Type: application/octet-stream
                bytes = line();
                String h2 = new String(Arrays.copyOf(bytes, newLength(bytes)));
                // 空行
                bytes = line();
                String h3 = new String(Arrays.copyOf(bytes, newLength(bytes)));
                // 数据行
                bytes = line();
                String d1 = new String(Arrays.copyOf(bytes, newLength(bytes)));

                // 数据验证
                isPrefix = prefix.equals(d1);
                isSuffix = suffix.equals(d1);
                boolean isData = !isPrefix && !isSuffix;

                // 写入数据
                ByteArrayOutputStream itemData = new ByteArrayOutputStream();
                if (isData) {
                    itemData.write(bytes, 0, bytes.length);
                }
                // 后续数据
                while (isData) {
                    bytes = line();
                    d1 = new String(Arrays.copyOf(bytes, newLength(bytes)));
                    isPrefix = prefix.equals(d1);
                    isSuffix = suffix.equals(d1);
                    isData = !isPrefix && !isSuffix;
                    if (isData) {
                        itemData.write(bytes, 0, newLength(bytes));
                    }
                }
                items.add(itemData.toByteArray());
            }
        }
        cur = 0;
        return items;
    }

    private byte[] line() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (; cur < datas.length; cur++) {
            baos.write(datas[cur]);
            boolean br = datas[cur] == 0X0A && datas[cur - 1] == 0X0D;
            if (br) {
                cur++;
                break;
            }
        }
        byte[] bytes = baos.toByteArray();
        //cur += bytes.length;
        //System.out.println(new String(Arrays.copyOf(bytes, newLength(bytes))));
        return bytes;
    }

    private String fileName(String header) {
        String[] split = header.split("; ");
        String fName = split[2];
        fName = fName.replace("filename=", "").replace("\"", "");
        return fName;
    }

    public static int newLength(byte[] bytes) {
        return bytes.length > 2 ? bytes.length - 2 : bytes.length;
    }
}
