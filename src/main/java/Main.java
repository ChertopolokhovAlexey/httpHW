import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static final String URL = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public static void main(String[] args) throws IOException {

//метод настроек класса.

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("MyTestService")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        HttpGet request = new HttpGet(URL);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

//        отправка запроса
        CloseableHttpResponse response = httpClient.execute(request);

//        вывод полученных заголовков
//        Arrays.stream(response.getAllHeaders()).forEach(System.out::println);

//        чтение тела ответа
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
//        System.out.println( body);

        httpClient.close();

        postsFilter(body);
    }

    static void postsFilter(String body) throws JsonProcessingException {
        System.out.println("Отфильтровано: ");
        ObjectMapper mapper = new ObjectMapper();
        List<Posts> post = mapper.readValue(body, new TypeReference<List<Posts>>() {
        });

        List<String> filter = post
                .stream()
                .filter(a -> a.getUpvotes() != null)
                .filter(b -> b.getUpvotes() != 0)
                .map(Posts::getText).toList();
        System.out.println(filter);
    }

}
