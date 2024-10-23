package javax.frame.tools.task_interact.modules.machine;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

public class MachineCodeUtils {

    //
    @Test
    public void demo() {
        System.out.println(0xe8);
        System.out.println((byte) 0xe8);
        System.out.println((byte) 0xe8 & 0xff);
    }


    // 绝对值相加 - 正常值相加 差值
    @Test
    public void demoMac() {
        byte[] bytes = new byte[]{(byte) 0xE8, (byte) 0x6A, (byte) 0x64, (byte) 0x8A, (byte) 0x38, (byte) 0x12};

        Arrays.sort(bytes);
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
        }
        int m1 = Stream.iterate(0, f -> f + 1).limit(6).map(f -> bytes[f]).mapToInt(f -> Math.abs(f)).sum();
        int m2 = Stream.iterate(0, f -> f + 1).limit(6).map(f -> bytes[f]).mapToInt(f -> f).sum();
        int m3 = m1 - m2;
        int m4 = m1 + m3;

        System.out.println(m1);
        System.out.println(m2);
        System.out.println(m3);
        System.out.println(m4);
    }

    // 证书过期说明
    // 验证：系统时间，井字棋游戏，
    // 验证：mac+md5
    // 验证：验证时间是否超过1秒
    // [1,4] 字节，存储mac
    // [5,8] 字节，存储m4字节md5排序后[4,7]
    //
    // 取Mac地址，生成license，包含有效期和，md5 验证标识

}
