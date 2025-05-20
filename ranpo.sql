CREATE TABLE members (
         id BIGINT AUTO_INCREMENT PRIMARY KEY,
         nickname VARCHAR(100),
         email VARCHAR(255) UNIQUE,
         password VARCHAR(255),
         phone VARCHAR(20),
         role VARCHAR(20),
         created_at TIMESTAMP
);

CREATE TABLE poll_rewards (
          id BIGINT AUTO_INCREMENT PRIMARY KEY,
          type VARCHAR(20),
          value TEXT,
          description TEXT,
          created_at TIMESTAMP
);

CREATE TABLE polls (
           id BIGINT AUTO_INCREMENT PRIMARY KEY,
           member_id BIGINT,
           title VARCHAR(255),
           description TEXT,
           start_at TIMESTAMP,
           end_at TIMESTAMP,
           auth_type VARCHAR(20),
           winner_selection VARCHAR(20),
           winner_scope VARCHAR(20),
           total_winner_count INT,
           reward_enabled BOOLEAN,
           poll_reward_id BIGINT,
           reward_method VARCHAR(20),
           created_at TIMESTAMP,
           deleted_at TIMESTAMP,
           FOREIGN KEY (member_id) REFERENCES members(id),
           FOREIGN KEY (poll_reward_id) REFERENCES poll_rewards(id)
);

CREATE TABLE poll_options (
          id BIGINT AUTO_INCREMENT PRIMARY KEY,
          poll_id BIGINT,
          content VARCHAR(255),
          winner_count INT,
          sort_order INT,
          created_at TIMESTAMP,
          FOREIGN KEY (poll_id) REFERENCES polls(id)
);

CREATE TABLE voters (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        poll_id BIGINT,
        auth_type VARCHAR(20),
        email VARCHAR(255),
        phone VARCHAR(20),
        member_id BIGINT,
        verified_at TIMESTAMP,
        FOREIGN KEY (poll_id) REFERENCES polls(id),
        FOREIGN KEY (member_id) REFERENCES members(id)
);

CREATE TABLE votes (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       poll_id BIGINT,
       poll_option_id BIGINT,
       voter_id BIGINT,
       anonymous_token VARCHAR(255),
       created_at TIMESTAMP,
       is_winner BOOLEAN,
       FOREIGN KEY (poll_id) REFERENCES polls(id),
       FOREIGN KEY (poll_option_id) REFERENCES poll_options(id),
       FOREIGN KEY (voter_id) REFERENCES voters(id)
);

CREATE TABLE poll_winners (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      poll_id BIGINT,
      poll_option_id BIGINT,
      vote_id BIGINT,
      voter_id BIGINT,
      is_reward_sent BOOLEAN,
      reward_sent_at TIMESTAMP,
      created_at TIMESTAMP,
      FOREIGN KEY (poll_id) REFERENCES polls(id),
      FOREIGN KEY (poll_option_id) REFERENCES poll_options(id),
      FOREIGN KEY (vote_id) REFERENCES votes(id),
      FOREIGN KEY (voter_id) REFERENCES voters(id)
);
