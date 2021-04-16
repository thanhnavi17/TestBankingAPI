package banking.api.service;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class CallAPI {
    static final String url = "https://6076ac761ed0ae0017d69875.mockapi.io/test";

    public static void callAPI(Map<String, String> response){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        // Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<Map<String, String>> requestBody = new HttpEntity<>(response, headers);

        // Gửi yêu cầu với phương thức POST.
        ResponseEntity<String> result = restTemplate.postForEntity(url, requestBody, String.class);

        String restCall = result.getBody();
        System.out.println(restCall);
    }
}
