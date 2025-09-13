package com.svick.brz.spring.service;

import com.svick.brz.spring.model.dto.UserDto;
import com.svick.brz.spring.repository.UserRepository;
import com.svick.brz.spring.model.entity.UserEntity;
import com.svick.brz.spring.utils.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public Page<UserDto> users(String query, Pageable pageable) {
        Page<UserEntity> userList;

        if (!query.isEmpty()) {
            String tsQuery = query.trim().replaceAll("\\s+", " & ");
            userList = userRepository.search(tsQuery, pageable);
        } else {
            userList = userRepository.findAll(pageable);
        }

        log.info("Number of elements received from database - {}", userList.getNumberOfElements());
        return userMapper.toDTOPage(userList);
    }

}
