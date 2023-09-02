package com.havefunwith.controller;

import com.havefunwith.dto.Response;
import com.havefunwith.exception.exceptions.InputValidationException;
import com.havefunwith.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
@RequiredArgsConstructor
public class ReactiveMathValidationController {

    private final ReactiveMathService reactiveMathService;

    @GetMapping("square/{input}/throw")
    public Mono<Response> findSquare(@PathVariable int input) {
        System.out.println("findSquare -- throw :: " + input);
        if (input < 10 || input > 20) {
            throw new InputValidationException(input);
        }
        return reactiveMathService.findSquare(input);
    }

    @GetMapping("square/{input}/mono-error")
    public Mono<Response> monoError(@PathVariable int input) {
        return Mono.just(input)
                .handle((integer, sink) -> {
                    if (integer >= 10 && integer <= 20)
                        sink.next(integer);
                    else
                        sink.error(new InputValidationException(integer));
                })
                .cast(Integer.class)
                .flatMap(i -> reactiveMathService.findSquare(i));
    }

    @GetMapping("square/{input}/pipeline-flow")
    public Mono<ResponseEntity<Response>> pipelineFlow(@PathVariable int input) {
        return Mono.just(input)
                .filter(i -> i >= 10 && i <= 20)
                .flatMap(i -> reactiveMathService.findSquare(i))
                .map(i -> ResponseEntity.ok(i))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

}
