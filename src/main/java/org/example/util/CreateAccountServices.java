package org.example.util;

import org.example.enums.AccountCreationMessage;

import java.util.Map;

public interface CreateAccountServices {
    public AccountCreationMessage createAccount(Map<String, String> userFields);
}
