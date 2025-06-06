package com.ranpo.ranpobackend.poll.controller;

import com.ranpo.ranpobackend.global.annotation.LoginUser;
import com.ranpo.ranpobackend.global.auth.dto.AuthenticatedUser;
import com.ranpo.ranpobackend.global.dto.response.ApiResponse;
import com.ranpo.ranpobackend.poll.dto.request.CreatePollRequest;
import com.ranpo.ranpobackend.poll.service.PollService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PollController {

    private final PollService pollService;

    @PostMapping("/api/v1/polls/create")
    public ResponseEntity<ApiResponse<CreatePollRequest.Response>> createPoll(
            @LoginUser AuthenticatedUser loginUser,
            @Valid @RequestBody CreatePollRequest request
    ) {
        CreatePollRequest.Response response = pollService.createPoll(loginUser, request);
        return ApiResponse.onSuccess(response);
    }
}
