package com.example.loadtestdb;

import com.example.loadtestdb.repository.TestDocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@SpringBootApplication
public class LoadTestDbApplication {

    public static void main(String[] args) {
        log.info("http://localhost:8080");
        SpringApplication.run(LoadTestDbApplication.class, args);
    }

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/")
    public static class CheckController {

        private static final Random random = new SecureRandom();
        private final TestDocumentRepository documentRepository;

        @GetMapping("save")
        public Mono<TestDocument> save() {
            return documentRepository.save(new TestDocument(null, "2", "3"));
        }

        @GetMapping("find/{id}")
        public Mono<TestDocument> find(@PathVariable String id) {
            return documentRepository.findById(id);
        }

        @GetMapping("test")
        public Mono<String> test() {
            for (int i = 0; i < 10; i++) {
                var dirtyHack = i;
                var start = System.currentTimeMillis() / 1000;
                documentRepository.saveAll(buildDocuments()).count()
                        .map(count -> "" + dirtyHack + ": " + count + " " + (System.currentTimeMillis() / 1000 - start))
                        .subscribe(System.out::println);
            }
            return Mono.create(sink -> sink.success("ok"));
        }

        private static List<TestDocument> buildDocuments() {
            List<TestDocument> results = new ArrayList<>();
            for (int i = 0; i < 100000; i++) {
                results.add(new TestDocument(null, "key" + i + random.nextInt(), "value" + i));
            }

            return results;
        }
    }
}
