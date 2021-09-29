package com.geekbrains.ru.springproduct.service;

import com.geekbrains.ru.springproduct.domain.RoleEntity;

public interface RoleService {

    RoleEntity findByName(String name);

}
