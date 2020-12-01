package com.raczkowski.springintro.github.controller;

import com.raczkowski.springintro.github.client.async.GithubAsyncHttpClient;
import com.raczkowski.springintro.github.client.sync.GithubSyncHttpClient;
import com.raczkowski.springintro.github.dto.GithubRepositoryDto;
import com.raczkowski.springintro.github.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class RepositoryController {

    private GithubAsyncHttpClient githubAsyncHttpClient;
    private GithubSyncHttpClient githubSyncHttpClient;

    public RepositoryController(GithubAsyncHttpClient githubAsyncHttpClient,
                                GithubSyncHttpClient githubSyncHttpClient) {
        this.githubAsyncHttpClient = githubAsyncHttpClient;
        this.githubSyncHttpClient = githubSyncHttpClient;
    }

//    @GetMapping("/repositories/{owner}/{repositoryName}")
//    public Mono<GithubRepositoryDto> getRepository(@PathVariable String owner,
//                                                   @PathVariable String repositoryName) {
//        return githubClient.getGithubRepository(owner, repositoryName);
//    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<String> handleNotFoundExceptions(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }

    @GetMapping("/repositories/{owner}/{repositoryName}")
    public GithubRepositoryDto getRepository(@PathVariable String owner,
                                             @PathVariable String repositoryName) throws InterruptedException, IOException, URISyntaxException {
        return githubSyncHttpClient.getGithubRepository(owner, repositoryName);
    }


}
