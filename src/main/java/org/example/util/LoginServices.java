package org.example.util;

import org.example.enums.LoginMessage;

public interface LoginServices {
    public LoginMessage loginAccount(String email, String password);
}
