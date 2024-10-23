package javax.frame.tools.task_interact.modules.machine;

import org.junit.Test;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MachineUtils {

    @Test
    public void mac(){
        System.out.println(getMac());
    }

    private static List<String> getLocalHostLANAddress() throws UnknownHostException, SocketException {
        List<String> ips = new ArrayList<String>();
        Enumeration<NetworkInterface> interfs = NetworkInterface.getNetworkInterfaces();
        while (interfs.hasMoreElements()) {
            NetworkInterface interf = interfs.nextElement();
            Enumeration<InetAddress> addres = interf.getInetAddresses();
            while (addres.hasMoreElements()) {
                InetAddress in = addres.nextElement();
                if (in instanceof Inet4Address) {
                    System.out.println("v4:" + in.getHostAddress());
                    if (!"127.0.0.1".equals(in.getHostAddress())) {
                        ips.add(in.getHostAddress());
                    }
                }
            }
        }
        return ips;
    }

    /**
     * MAC
     * 通过jdk自带的方法,先获取本机所有的ip,然后通过NetworkInterface获取mac地址
     * @return
     */
    public static String getMac() {
        try {
            String resultStr = "";
            List<String> ls = getLocalHostLANAddress();
            for(String str : ls){
                InetAddress ia = InetAddress.getByName(str);// 获取本地IP对象
                // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
                byte[] mac = NetworkInterface.getByInetAddress(ia)
                        .getHardwareAddress();
                // 下面代码是把mac地址拼装成String
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    if (i != 0) {
                        sb.append("-");
                    }
                    // mac[i] & 0xFF 是为了把byte转化为正整数
                    String s = Integer.toHexString(mac[i] & 0xFF);
                    sb.append(s.length() == 1 ? 0 + s : s);
                }
                // 把字符串所有小写字母改为大写成为正规的mac地址并返回
                resultStr += sb.toString().toUpperCase()+",";
            }
            return resultStr;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
