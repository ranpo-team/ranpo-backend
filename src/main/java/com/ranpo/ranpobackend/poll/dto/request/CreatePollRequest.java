package com.ranpo.ranpobackend.poll.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ranpo.ranpobackend.poll.domain.Poll;
import com.ranpo.ranpobackend.poll.domain.enums.AuthType;
import com.ranpo.ranpobackend.poll.domain.enums.WinnerScope;
import com.ranpo.ranpobackend.poll.domain.enums.WinnerSelectType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreatePollRequest {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime startAt;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime endAt;

    @NotNull
    private AuthType authType;

    @NotNull
    private WinnerSelectType winnerSelectType;

    @NotNull
    private WinnerScope winnerScope;

    @Min(0)
    private int totalWinnerCount;

    private Boolean rewardEnabled;

    @Valid
    @NotNull
    @NotEmpty
    private List<CreatePollOptionRequest> options;

    @Getter
    public static class CreatePollOptionRequest {

        @NotBlank
        String content;

        @Min(0)
        int winnerCount;

        @Min(1)
        int sortOrder;
    }

    @Getter
    public static class Response {
        private Long id;
        private String title;

        private Response(Long id, String title) {
            this.id = id;
            this.title = title;
        }

        public static Response from(Poll poll) {
            return new Response(poll.getId(), poll.getTitle());
        }
    }
}
