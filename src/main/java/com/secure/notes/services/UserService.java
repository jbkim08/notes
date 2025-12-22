package com.secure.notes.services;

import com.secure.notes.dtos.UserDTO;
import com.secure.notes.models.Role;
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
    //유저잠금상태 업데이트
    void updateAccountLockStatus(Long userId, boolean lock);
    //유저권한 리스트 가져오기
    List<Role> getAllRoles();
    //유저계정만료상태 업데이트
    void updateAccountExpiryStatus(Long userId, boolean expire);
    //유저계정활성화상태 업데이트
    void updateAccountEnabledStatus(Long userId, boolean enabled);
    //유저 패스워드만료상태 업데이트
    void updateCredentialsExpiryStatus(Long userId, boolean expire);
    //패스워드 업데이트
    void updatePassword(Long userId, String password);
}
