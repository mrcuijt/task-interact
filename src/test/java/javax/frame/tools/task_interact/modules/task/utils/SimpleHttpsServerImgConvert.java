package javax.frame.tools.task_interact.modules.task.utils;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.sun.net.httpserver.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import javax.net.ssl.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class SimpleHttpsServerImgConvert {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        HttpsServer httpsServer = getHttpsServer();
        HttpContext context = httpsServer.createContext("/");
        context.setHandler(SimpleHttpsServerImgConvert::handleRequest);
        httpsServer.start();
        System.out.println("Start HTTPS server on port " + port + " of localhost");
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        //printRequestInfo(exchange);
        processRequestBody(exchange);
        String response = "This is the response at " + requestURI;
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.put("Access-Control-Allow-Origin", Arrays.asList("*"));
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static void printRequestInfo(HttpExchange exchange) {
        System.out.println("-- Client Info --");
        InetSocketAddress remoteAddress = exchange.getRemoteAddress();
        String remoteHost = remoteAddress.getHostName();
        System.out.println(String.format("RemoteAddress:%s; RemoteHost:%s;", remoteAddress, remoteHost));

        System.out.println("-- headers --");
        Headers requestHeaders = exchange.getRequestHeaders();
        requestHeaders.entrySet().forEach(System.out::println);

        System.out.println("-- principle --");
        HttpPrincipal principal = exchange.getPrincipal();
        System.out.println(principal);

        System.out.println("-- HTTP method --");
        String requestMethod = exchange.getRequestMethod();
        System.out.println(requestMethod);

        System.out.println("-- query --");
        URI requestURI = exchange.getRequestURI();
        String query = requestURI.getQuery();
        System.out.println(query);
        System.out.println("-- request body --");
        FileOutputStream fos = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            InputStream is = exchange.getRequestBody();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, length);
            }
            if (baos.toByteArray().length > 0) {
                fos = new FileOutputStream(new File(String.format("server_%d.dat", System.currentTimeMillis())));
                fos.write(baos.toByteArray());
                fos.flush();
                System.out.println(new String(baos.toByteArray(), "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static final String basePath = "D:/tools/upload/upload/";
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private static final String CONTENT_TYPE = "Content-type";
    private static final String PREFIX = "--";
    private static final String BOUNDARY = "boundary=";
    private static final String HTTP_BR = new String(new byte[]{0x0d, 0x0a});

    private static void processRequestBody(HttpExchange exchange) {
        //MultipartFile
        //FileItem
        //Part
        try {
            String contentType = CONTENT_TYPE;
            String boundary = BOUNDARY;
            Headers requestHeaders = exchange.getRequestHeaders();
            String[] ctType = requestHeaders.getFirst(contentType).split("; ");
            if (MULTIPART_FORM_DATA.equals(ctType[0])) {
                String boundaryHeader = ctType[1];
                String prefix = PREFIX + boundaryHeader.replace(boundary, "");
                String suffix = prefix + PREFIX;
                // 取数
                byte[] items = null;
                try (InputStream is = exchange.getRequestBody();
                     ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
                    byte[] buffer = new byte[1024];
                    int length = 0;
                    while ((length = is.read(buffer, 0, buffer.length)) != -1) {
                        baos.write(buffer, 0, length);
                    }
                    items = baos.toByteArray();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 基础信息输出
                //System.out.println(boundary + prefix);
                DataItem dataItem = new DataItem(items);
                List<byte[]> datas = dataItem.datas(prefix, suffix);
                //File file = new File(basePath, dataItem.getRoomId());
                File file = new File(basePath, "img");
                if (!file.exists()){
                    file.mkdirs();
                }
                datas.stream().forEach(f -> {
                    File danmuk = new File(file, dataItem.getRoomId() + "-" + IdWorker.getIdStr() + ".json");
                    danmuk = new File(file, dataItem.getRoomId());
                    try {
                        // String s = new String(Arrays.copyOf(f, DataItem.newLength(f)));
                        // System.out.println(s);
                        FileUtils.writeByteArrayToFile(danmuk, Arrays.copyOf(f, DataItem.newLength(f)));
                        log.info("Save file to : {}", danmuk.getAbsolutePath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String sslKeyStore = "/lig.keystore";
    public static String sslKeyStorePassword = "simulator";
    public static int port = 8600;

    public static HttpsServer getHttpsServer() {
        try {
            // Set up the socket address
            //InetSocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), port);
            InetSocketAddress address = new InetSocketAddress(port);

            // Initialise the HTTPS server
            HttpsServer httpsServer = HttpsServer.create(address, 0);
            SSLContext sslContext = SSLContext.getInstance("TLS");

            // Initialise the keystore
            char[] password = sslKeyStorePassword.toCharArray();
            KeyStore ks = KeyStore.getInstance("JKS");
            BufferedInputStream fis = new BufferedInputStream(SimpleHttpsServerImgConvert.class.getClass().getResourceAsStream(sslKeyStore));
            ks.load(fis, password);

            // Set up the key manager factory
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, password);

            // Set up the trust manager factory
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ks);

            // Set up the HTTPS context and parameters
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            httpsServer.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
                public void configure(HttpsParameters params) {
                    try {
                        // Initialise the SSL context
                        SSLContext c = SSLContext.getDefault();
                        SSLEngine engine = c.createSSLEngine();
                        params.setNeedClientAuth(false);
                        params.setCipherSuites(engine.getEnabledCipherSuites());
                        params.setProtocols(engine.getEnabledProtocols());

                        // Get the default parameters
                        SSLParameters defaultSSLParameters = c.getDefaultSSLParameters();
                        params.setSSLParameters(defaultSSLParameters);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("Failed to create HTTPS port");
                    }
                }
            });
            return httpsServer;
            //httpsServer.start();
            //LigServer server = new LigServer(httpsServer);
            //joinableThreadList.add(server.getJoinableThread());
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Failed to create HTTPS server on port " + port + " of localhost");
        }
        return null;
    }
}
