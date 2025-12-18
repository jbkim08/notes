package com.secure.notes.services;

import com.secure.notes.dtos.UserDTO;
import com.secure.notes.models.User;

import java.util.List;

public interface UserService {
    //유저의 권한을 수정
    void updateUserRole(Long userId, String roleName);
    //모든 유저 가져오기
    List<User> getAllUsers();
    //id로 특정 유저를 가져오기
    UserDTO getUserById(Long id);
    //유저네임으로 유저찾기
    User findByUername(String username);
}
