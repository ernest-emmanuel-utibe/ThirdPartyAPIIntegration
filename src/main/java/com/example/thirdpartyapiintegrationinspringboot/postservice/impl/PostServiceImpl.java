package com.example.thirdpartyapiintegrationinspringboot.postservice.impl;

import com.example.thirdpartyapiintegrationinspringboot.postservice.PostService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    // Create the baseUrl and keep it same so as all endpoints can hit the url.
    String baseUrl = "https://jsonplaceholder.typicode.com/";

    // Pass the baseUrl to the new StringBuilder object.
    StringBuilder stringBuilder = new StringBuilder(baseUrl);

    // This will make the Url independent.
    String POST = "/posts";
    String POST_BY_ID = "/posts/";

    private final RestTemplate restTemplate;

    @Override
    public List<Map<String, Object>> getPosts() {
        HttpEntity <Void> httpEntity = new HttpEntity<>(getHttpHeaders());
        String url = stringBuilder.append(POST).toString();
        var response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, List.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> getPostById(int id) {
        HttpEntity <Void> httpEntity = new HttpEntity<>(getHttpHeaders());
        String url = stringBuilder.append(POST_BY_ID).append(id).toString();
        var response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Map.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> insertPost(Map<String, Object> payload) {
        HttpEntity <Map> httpEntity = new HttpEntity<>(payload, getHttpHeaders());
        String url = stringBuilder.append(POST).toString();
        var response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> updatePost(Map<String, Object> payload, int id) {
        HttpEntity <Map> httpEntity = new HttpEntity<>(payload, getHttpHeaders());
        String url = stringBuilder.append(POST_BY_ID).append(id).toString();
        var response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Map.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> deletePost(int id) {
        HttpEntity <Map> httpEntity = new HttpEntity<>(getHttpHeaders());
        String url = stringBuilder.append(POST_BY_ID).append(id).toString();
        var response = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Map.class);
        return response.getBody();
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
