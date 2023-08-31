package com.havefunwith.service;

import com.havefunwith.dto.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Stream methods in this file
 * ==========================================================================================
 * .peek()
 * The peek() method in Java's java.util.stream is for debugging, allowing inspection of stream
 * elements without modification, often used to print elements during processing.
 * ==========================================================================================
 *
 */
@Service
public class MathService {

    public Response findSquare(int input) {
        return new Response(input * input);
    }

    public List<Response> multiplicationTable(int input) {
        return IntStream.rangeClosed(1, 10)
                .peek(i -> SleepUtil.sleepSeconds(1))
                .peek(i -> System.out.println("math-service processing : " + i * input))
                .mapToObj(i -> new Response(i * input))
                .collect(Collectors.toList());
    }

}
