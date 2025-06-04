package com.ranpo.ranpobackend.member.controller;

import com.ranpo.ranpobackend.global.annotation.LoginUser;
import com.ranpo.ranpobackend.global.auth.dto.AuthenticatedUser;
import com.ranpo.ranpobackend.global.dto.response.ApiResponse;
import com.ranpo.ranpobackend.member.dto.response.MyProfileResponse;
import com.ranpo.ranpobackend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/members/me")
    public ResponseEntity<ApiResponse<MyProfileResponse>> getMyProfile(
            @LoginUser AuthenticatedUser authenticatedUser
    ) {
        return ApiResponse.onSuccess(MyProfileResponse.from(authenticatedUser));
    }
}
