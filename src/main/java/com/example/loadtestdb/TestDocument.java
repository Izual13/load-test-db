package com.example.loadtestdb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record TestDocument(@Id String id, String key, String value) {

}