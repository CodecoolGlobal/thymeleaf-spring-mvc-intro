package com.raczkowski.springintro.github.client.sync;

import com.raczkowski.springintro.github.configuration.ApplicationProperties;
import com.raczkowski.springintro.github.dto.GithubRepositoryDto;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class GithubSyncHttpClient {

    private static final String GITHUB_API_BASE_URL = "https://api.github.com";
    private static final String GITHUB_V3_MIME_TYPE = "application/vnd.github.v3+json";
    private static final String REPOSITORY_NOT_FOUND_EXCEPTION_MESSAGE = "Repository not found for given name: %s";

    private final HttpClient httpClient;

    public GithubSyncHttpClient(ApplicationProperties applicationProperties) {
        this.httpClient = HttpClient.newBuilder()
                .authenticator(authenticator(applicationProperties.getGithub().getUsername(),
                        applicationProperties.getGithub().getToken()))
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }

    public List<GithubRepositoryDto> getGithubRepository(String owner, String repositoryName) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest get = HttpRequest.newBuilder()
                .uri(new URI(GITHUB_API_BASE_URL))
                .headers(HttpHeaders.CONTENT_TYPE, GITHUB_V3_MIME_TYPE)
                .GET()
                .build();

        return httpClient.send(get, HttpResponse.BodyHandlers.ofString())
    }

    private Authenticator authenticator(String username, String password) {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password.toCharArray());
            }
        };
    }

}
