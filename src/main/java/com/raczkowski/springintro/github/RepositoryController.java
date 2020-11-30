package com.raczkowski.springintro.github;

import com.raczkowski.springintro.backdoor.exception.CustomerNotFoundException;
import com.raczkowski.springintro.backdoor.exception.OrderNotFoundException;
import com.raczkowski.springintro.github.client.GithubClient;
import com.raczkowski.springintro.github.dto.GithubRepositoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class RepositoryController {

    private GithubClient githubClient;

    public RepositoryController(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    @GetMapping("/repositories/{owner}/{repositoryName}")
    public Mono<GithubRepositoryDto> getRepository(@PathVariable String owner,
                                                   @PathVariable String repositoryName) {
        return githubClient.getGithubRepository(owner, repositoryName);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<String> handleNotFoundExceptions(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }


}
