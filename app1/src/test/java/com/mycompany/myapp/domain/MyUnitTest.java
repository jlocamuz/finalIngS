package com.mycompany.myapp.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mycompany.myapp.web.rest.TestUtil;

import io.swagger.v3.core.util.Json;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter; 


public class MyUnitTest {

    @Test
    void firstTest() throws Exception {
        TestUtil.equalsVerifier(Blog.class);
        Blog blog1 = new Blog();
        blog1.setId(2948L);
        blog1.setName("Blog de prueba");
        blog1.setHandle("Handle de Blog de prueba");
        }


    @Test
    void comparingObjectBlogMangagmentWithAcallToApi() throws Exception {
        TestUtil.equalsVerifier(Blog.class);
        Blog blog = new Blog().id(1L).name("Integración").handle("Avon Cambridgeshire");
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:8080/api/blogs/1")).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        System.out.println("this is a response" + response.body()); 
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(blog);
        System.out.println("this is the object to JSON" + json); 
        assertThat(response.body()).isEqualTo(json);
          }
    
    
    @Test
    void comparingObjectPostMangagmentWithAcallToApi() throws Exception {
        TestUtil.equalsVerifier(Blog.class);
        Blog blog1 = new Blog().id(1L).name("Integración").handle("Avon Cambridgeshire");
        Tag tag1 = new Tag().id(1L).name("Web back-end desafío");

        HashSet<Tag> hashSet = new HashSet<Tag>();
        hashSet.add(tag1);
        //DateFormat date = new SimpleDateFormat("2023-02-07T03:00:00Z");

        Post post = new Post()
                        .id(1051L)
                        .title("Post de Integracion")
                        .content("este es el contenido de un post de integracion")
                        .blog(blog1)
                        .tags(hashSet)
                        ;

        // HTTP
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:8080/api/posts/1051")).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        System.out.println("this is a response" + response.body()); 

        // JACKSON JSON CONVERTER
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(post);
        System.out.println("this is the object to JSON" + json); 
        assertThat(response.body()).isEqualTo(json);

    }
    
    }

        
