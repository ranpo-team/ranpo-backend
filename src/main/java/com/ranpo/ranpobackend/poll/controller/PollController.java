package com.ranpo.ranpobackend.poll.controller;

import com.ranpo.ranpobackend.global.annotation.LoginUser;
import com.ranpo.ranpobackend.global.auth.dto.AuthenticatedUser;
import com.ranpo.ranpobackend.global.dto.response.ApiResponse;
import com.ranpo.ranpobackend.poll.dto.request.CreatePollRequest;
import com.ranpo.ranpobackend.poll.dto.response.PollResponse;
import com.ranpo.ranpobackend.poll.service.PollService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/v1/polls/{pollId}")
    public ResponseEntity<ApiResponse<PollResponse>> getPoll(
            @PathVariable Long pollId
    ) {
        PollResponse response = pollService.getPollById(pollId);
        return ApiResponse.onSuccess(response);
    }
}
