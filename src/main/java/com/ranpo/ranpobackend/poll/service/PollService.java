package com.ranpo.ranpobackend.poll.service;

import com.ranpo.ranpobackend.global.auth.dto.AuthenticatedUser;
import com.ranpo.ranpobackend.global.exception.CustomException;
import com.ranpo.ranpobackend.global.exception.ErrorCode;
import com.ranpo.ranpobackend.member.domain.Member;
import com.ranpo.ranpobackend.poll.domain.Poll;
import com.ranpo.ranpobackend.poll.domain.repository.PollRepository;
import com.ranpo.ranpobackend.poll.dto.request.CreatePollRequest;
import com.ranpo.ranpobackend.poll.dto.response.PollResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PollService {

    private final PollRepository pollRepository;
    private final PollOptionService pollOptionService;

    @Transactional
    public CreatePollRequest.Response createPoll(
            AuthenticatedUser loginUser,
            CreatePollRequest request
    ) {
        Member member = new Member(loginUser);
        Poll poll = Poll.builder()
                .member(member)
                .title(request.getTitle())
                .description(request.getDescription())
                .startAt(request.getStartAt())
                .endAt(request.getEndAt())
                .authType(request.getAuthType())
                .winnerSelectType(request.getWinnerSelectType())
                .winnerScope(request.getWinnerScope())
                .totalWinnerCount(request.getTotalWinnerCount())
                .rewardEnabled(request.getRewardEnabled())
                .build();

        pollRepository.save(poll);
        pollOptionService.savePollOptions(request.getOptions(), poll);
        return CreatePollRequest.Response.from(poll);
    }

    @Transactional(readOnly = true)
    public PollResponse getPollById(Long pollId) {
        Poll poll = pollRepository.findWithOptionsById(pollId)
                .orElseThrow(() -> CustomException.from(ErrorCode.POLL_NOT_FOUND));

        return PollResponse.from(poll);
    }
}
