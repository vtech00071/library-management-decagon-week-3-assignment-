package org.example.util;

import org.example.enums.AccountCreationMessage;

import java.util.Map;

public interface CreateAccountServices {

     AccountCreationMessage createAccount(Map<String, String> userFields);

}
