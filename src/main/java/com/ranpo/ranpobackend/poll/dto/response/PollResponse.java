package com.ranpo.ranpobackend.poll.dto.response;

import com.ranpo.ranpobackend.poll.domain.Poll;
import com.ranpo.ranpobackend.poll.domain.PollOption;
import com.ranpo.ranpobackend.poll.domain.enums.AuthType;
import com.ranpo.ranpobackend.poll.domain.enums.WinnerScope;
import com.ranpo.ranpobackend.poll.domain.enums.WinnerSelectType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PollResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private AuthType authType;
    private WinnerSelectType winnerSelectType;
    private WinnerScope winnerScope;
    private int totalWinnerCount;
    private List<PollOptionResponse> options = new ArrayList<>();
    private Boolean rewardEnabled;

    private PollResponse(
            Long id,
            String title,
            String description,
            LocalDateTime startAt,
            LocalDateTime endAt,
            AuthType authType,
            WinnerSelectType winnerSelectType,
            WinnerScope winnerScope,
            int totalWinnerCount,
            List<PollOptionResponse> options,
            Boolean rewardEnabled
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.authType = authType;
        this.winnerSelectType = winnerSelectType;
        this.winnerScope = winnerScope;
        this.totalWinnerCount = totalWinnerCount;
        this.options = options;
        this.rewardEnabled = rewardEnabled;
    }

    public static PollResponse from(Poll poll) {
        List<PollOptionResponse> options = poll.getPollOptions().stream()
                        .map(PollOptionResponse::from)
                        .toList();

        return new PollResponse(
                poll.getId(),
                poll.getTitle(),
                poll.getDescription(),
                poll.getStartAt(),
                poll.getEndAt(),
                poll.getAuthType(),
                poll.getWinnerSelectType(),
                poll.getWinnerScope(),
                poll.getTotalWinnerCount(),
                options,
                poll.getRewardEnabled()
        );
    }

    @Getter
    public static class PollOptionResponse {
        private Long id;
        private String content;
        private int sortOrder;

        private PollOptionResponse(Long id, String content, int sortOrder) {
            this.id = id;
            this.content = content;
            this.sortOrder = sortOrder;
        }

        public static PollOptionResponse from(PollOption pollOption) {
            return new PollOptionResponse(
                    pollOption.getId(),
                    pollOption.getContent(),
                    pollOption.getSortOrder()
            );
        }
    }

}
