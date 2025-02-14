package com.secure.notes.services;

import com.secure.notes.dtos.UserDTO;
import com.secure.notes.models.Role;
import com.secure.notes.models.User;

import java.util.List;

public interface UserService {
    //유저의 권한을 업데이트
    void updateUserRole(Long userId, String roleName);
    //모든 유저를 가져옴
    List<User> getAllUsers();
    //한명의 유저를 가져옴 (UserDTO 객체)
    UserDTO getUserById(Long id);
    //유저 정보를 가져옴 (유저네임)
    User findByUsername(String username);
    //유저계정 잠금 상태 변경
    void updateAccountLockStatus(Long userId, boolean lock);
    //유저 권한들 가져오기 (DB에 있는 모든 권한들)
    List<Role> getAllRoles();
    //계정만료상태 업데이트
    void updateAccountExpiryStatus(Long userId, boolean expire);
    //계정사용가능상태 업데이트
    void updateAccountEnabledStatus(Long userId, boolean enabled);
    //계정 비번만료상태 업데이트
    void updateCredentialsExpiryStatus(Long userId, boolean expire);
    //패스워드 업데이트
    void updatePassword(Long userId, String password);
    //패스워드 리셋토큰 생성
    void generatePasswordResetToken(String email);
    //새 패스워드 업데이트
    void resetPassword(String token, String newPassword);
}