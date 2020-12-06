package com.raczkowski.springintro.github.client.async;

import com.raczkowski.springintro.github.configuration.ApplicationProperties;
import com.raczkowski.springintro.github.dto.GithubRepositoryDto;
import com.raczkowski.springintro.github.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Service
public class GithubAsyncHttpClient {

    private static final String GITHUB_API_BASE_URL = "https://api.github.com";
    private static final String GITHUB_V3_MIME_TYPE = "application/vnd.github.v3+json";
    private static final String REPOSITORY_NOT_FOUND_EXCEPTION_MESSAGE = "Repository not found for given name: %s";
    private final WebClient webClient;
    private final ApplicationProperties applicationProperties;

    public GithubAsyncHttpClient(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        this.webClient = WebClient.builder()
                .baseUrl(GITHUB_API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, GITHUB_V3_MIME_TYPE)
                .build();
    }

    public Mono<GithubRepositoryDto> getGithubRepository(String owner, String repositoryName) {
        return webClient.get().uri("repos/{owner}/{name}", owner, repositoryName)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.is4xxClientError(),
                        clientResponse -> Mono.error(
                                new NotFoundException(
                                        format(REPOSITORY_NOT_FOUND_EXCEPTION_MESSAGE, repositoryName)
                                )))
                .bodyToMono(GithubRepositoryDto.class);
    }

}
