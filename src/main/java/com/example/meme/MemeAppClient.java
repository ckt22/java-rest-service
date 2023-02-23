package com.example.meme;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.concurrent.CompletionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service
public class MemeAppClient {

    ObjectMapper objectMapper = new ObjectMapper();

    Gson gson = new GsonBuilder().create();

    @Value("${humor.api.key}")
    private String humorApiKey;

    public String sampleApiRequest() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://jsonplaceholder.typicode.com/todos"))
            .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        return response.body();

    }

    public Todo syncGson() throws Exception {
        String response = sampleApiRequest();

        List<Todo> todo = gson.fromJson(response, new TypeToken<List<Todo>>() {
        }.getType());

        return todo.get(1);

    }

    // public Todo syncJackson() throws Exception {
    //     String response = sampleApiRequest();

    //     Todo[] todo = objectMapper.readValue(response, Todo[].class);

    //     return todo[1];

    // }

    // public Todo asyncJackson() throws Exception {

    //     HttpRequest request = HttpRequest.newBuilder()
    //         .uri(URI.create("https://jsonplaceholder.typicode.com/todos"))
    //         .build();

    //     MemeAppClient todoAppClient = new MemeAppClient();

    //     List<Todo> todo = HttpClient.newHttpClient()
    //         .sendAsync(request, BodyHandlers.ofString())
    //         .thenApply(HttpResponse::body)
    //         .thenApply(todoAppClient::readValueJackson)
    //         .get();

    //     return todo.get(1);

    // }

    public Joke getJokeFromInternet() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.humorapi.com/jokes/random?api-key=" + humorApiKey))
            .build();

        MemeAppClient todoAppClient = new MemeAppClient();

        Joke joke = HttpClient.newHttpClient()
            .sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(todoAppClient::readJoke)
            .get();

        return joke;

    }

    public Todo asyncGson() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://jsonplaceholder.typicode.com/todos"))
            .build();
        MemeAppClient todoAppClient = new MemeAppClient();

        List<Todo> todo = HttpClient.newHttpClient()
            .sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(todoAppClient::readValueGson)
            .get();

        return todo.get(1);

    }

    Joke readJoke(String content) {

        try {
            return objectMapper.readValue(content, new TypeReference<Joke>() {
            });
        } catch (IOException ioe) {
            throw new CompletionException(ioe);
        }
    }

    List<Todo> readValueJackson(String content) {

        try {
            return objectMapper.readValue(content, new TypeReference<List<Todo>>() {
            });
        } catch (IOException ioe) {
            throw new CompletionException(ioe);
        }
    }
    
    List<Todo> readValueGson(String content) {

        return gson.fromJson(content, new TypeToken<List<Todo>>() {
        }.getType());

    }

}
