package com.secure.notes.services.impl;

import com.secure.notes.dtos.UserDTO;
import com.secure.notes.models.AppRole;
import com.secure.notes.models.PasswordResetToken;
import com.secure.notes.models.Role;
import com.secure.notes.models.User;
import com.secure.notes.repositories.PasswordResetTokenRepository;
import com.secure.notes.repositories.RoleRepository;
import com.secure.notes.repositories.UserRepository;
import com.secure.notes.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PasswordEncoder passwordEncoder;
    final PasswordResetTokenRepository passwordResetTokenRepository;

    @Value("${frontend.url}") //application.properties 에서 값을 가져옴
    String frontendUrl;

    @Override
    public void generatePasswordResetToken(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저를 못찾음"));

        String token = UUID.randomUUID().toString(); //중복되지 않는 토큰값을 만듬
        Instant expiryDate = Instant.now().plus(24, ChronoUnit.HOURS);
        PasswordResetToken resetToken = new PasswordResetToken(token, expiryDate, user);
        passwordResetTokenRepository.save(resetToken);

        String resetUrl = frontendUrl + "/reset-password?token=" + token;
        // 이메일 보내기
        //emailService.sendPasswordResetEmail(user.getEmail(), resetUrl);
    }

    @Override
    public void updateUserRole(Long userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 못찾았습니다."));
        AppRole appRole = AppRole.valueOf(roleName); // 업데이트 할 권한
        Role role = roleRepository.findByRoleName(appRole)
                .orElseThrow(() -> new RuntimeException("권한을 못찾았습니다."));
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return convertToDto(user);
    }

    @Override
    public User findByUername(String username) {
        Optional<User> user = userRepository.findByUserName(username);
        return user.orElseThrow(()->new RuntimeException("유저를 찾을 수 없습니다." + username));
    }

    @Override
    public void updateAccountLockStatus(Long userId, boolean lock) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new RuntimeException("유저 못찾음"));
        user.setAccountNonLocked(!lock); //반대상태로 업데이트(잠금<=>해제)
        userRepository.save(user);

    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll(); //모든 권한 리스트
    }

    @Override
    public void updateAccountExpiryStatus(Long userId, boolean expire) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new RuntimeException("유저 못찾음"));
        user.setAccountNonExpired(!expire); //반대상태로 업데이트(만료<=>가능)
        userRepository.save(user);
    }

    @Override
    public void updateAccountEnabledStatus(Long userId, boolean enabled) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new RuntimeException("유저 못찾음"));
        user.setEnabled(enabled); //업데이트(사용못함<=>가능)
        userRepository.save(user);
    }

    @Override
    public void updateCredentialsExpiryStatus(Long userId, boolean expire) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new RuntimeException("유저 못찾음"));
        user.setCredentialsNonExpired(!expire); //비번 기간만료 업데이트
        userRepository.save(user);
    }

    @Override
    public void updatePassword(Long userId, String password) {
        try {
            User user = userRepository.findById(userId).orElseThrow(()
                    -> new RuntimeException("유저 못찾음"));
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("패스워드 업데이트 실패");
        }
    }

    //유저DTO로 변환하는 메소드
    private UserDTO convertToDto(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.isAccountNonLocked(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isEnabled(),
                user.getCredentialsExpiryDate(),
                user.getAccountExpiryDate(),
                user.getTwoFactorSecret(),
                user.isTwoFactorEnabled(),
                user.getSignUpMethod(),
                user.getRole(),
                user.getCreatedDate(),
                user.getUpdatedDate()
        );
    }
}
