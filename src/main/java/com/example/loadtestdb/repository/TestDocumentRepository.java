package com.example.loadtestdb.repository;

import com.example.loadtestdb.TestDocument;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDocumentRepository extends ReactiveCrudRepository<TestDocument, String> {
}
