package com.ranpo.ranpobackend.poll.service;

import com.ranpo.ranpobackend.poll.domain.Poll;
import com.ranpo.ranpobackend.poll.domain.PollOption;
import com.ranpo.ranpobackend.poll.domain.repository.PollOptionRepository;
import com.ranpo.ranpobackend.poll.dto.request.CreatePollRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollOptionService {

    private final PollOptionRepository pollOptionRepository;

    @Transactional
    public void savePollOptions(
            List<CreatePollRequest.CreatePollOptionRequest> requests,
            Poll poll
    ) {
        List<PollOption> options = requests.stream()
                .map(request -> PollOption.builder()
                                .poll(poll)
                                .content(request.getContent())
                                .winnerCount(request.getWinnerCount())
                                .sortOrder(request.getSortOrder())
                                .build()
                ).toList();

        pollOptionRepository.saveAll(options);
    }
}
