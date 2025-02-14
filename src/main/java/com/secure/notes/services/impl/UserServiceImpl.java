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
import com.secure.notes.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    EmailService emailService;

    @Value("${frontend.url}")
    String frontendUrl;   //설정에 있는 주소를 가져옴

    @Override
    public void updateUserRole(Long userId, String roleName) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new RuntimeException("User not found"));
        AppRole appRole = AppRole.valueOf(roleName);
        Role role = roleRepository.findByRoleName(appRole)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new RuntimeException("User not found"));
        return convertToDto(user);
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUserName(username);
        return user.orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    @Override
    public void updateAccountLockStatus(Long userId, boolean lock) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new RuntimeException("User not found"));
        user.setAccountNonLocked(!lock); //잠근상태 업데이트
        userRepository.save(user); //유저 업데이트
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void updateAccountExpiryStatus(Long userId, boolean expire) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new RuntimeException("User not found"));
        user.setAccountNonExpired(!expire); //계정만료상태 업데이트
        userRepository.save(user); //유저 업데이트
    }

    @Override
    public void updateAccountEnabledStatus(Long userId, boolean enabled) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new RuntimeException("User not found"));
        user.setEnabled(enabled); //계정상태 업데이트
        userRepository.save(user); //유저 업데이트
    }

    @Override
    public void updateCredentialsExpiryStatus(Long userId, boolean expire) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new RuntimeException("User not found"));
        user.setCredentialsNonExpired(!expire); //비번만료상태 업데이트
        userRepository.save(user); //유저 업데이트
    }

    @Override
    public void updatePassword(Long userId, String password) {
        try {
            User user = userRepository.findById(userId).orElseThrow(()->
                    new RuntimeException("User not found"));
            user.setPassword(passwordEncoder.encode(password)); //비번암호화 해 저장
            userRepository.save(user); //유저 업데이트
        } catch (Exception e) {
            throw new RuntimeException("Failed to update password");
        }
    }

    @Override
    public void generatePasswordResetToken(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString(); //랜덤으로 유니크 문자열 토큰을 만듬
        Instant expiryDate = Instant.now().plus(24, ChronoUnit.HOURS);
        PasswordResetToken resetToken = new PasswordResetToken(token, expiryDate, user);
        passwordResetTokenRepository.save(resetToken);

        String resetUrl = frontendUrl + "/reset-password?token=" + token;
        // 이메일 보내기
        emailService.sendPasswordResetEmail(user.getEmail(), resetUrl);
    }


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