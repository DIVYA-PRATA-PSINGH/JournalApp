package com.example.demo.service;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import com.example.demo.entity.User;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // You would provide your test data here. For example:
        return Stream.of(
                Arguments.of(User.builder().userName("shayam").password("shyam").build()),
                Arguments.of(User.builder().userName("suraj").password("suraj").build()
                ));
    }
}