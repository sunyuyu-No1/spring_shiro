package com.dk.service;

import com.dk.pojo.TPerson;

public interface UserService {
    TPerson findByUsername(String username);
}
