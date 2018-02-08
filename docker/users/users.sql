DROP DATABASE IF EXISTS bactrian_users;

CREATE DATABASE bactrian_users DEFAULT CHARACTER SET 'utf8'
  DEFAULT COLLATE 'utf8_unicode_ci';

USE bactrian_users;


CREATE TABLE user (
  UserId BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Username VARCHAR(30) NOT NULL,
  HashedPassword BINARY(60) NOT NULL,
  AccountNonExpired BOOLEAN NOT NULL,
  AccountNonLocked BOOLEAN NOT NULL,
  CredentialsNonExpired BOOLEAN NOT NULL,
  Enabled BOOLEAN NOT NULL,
  CONSTRAINT user_unique UNIQUE (Username)
) ENGINE = InnoDB;

CREATE TABLE user_Authority (
  UserId BIGINT UNSIGNED NOT NULL,
  Authority VARCHAR(100) NOT NULL,
  UNIQUE KEY user_Authority_User_Authority (UserId, Authority),
  CONSTRAINT user_Authority_UserId FOREIGN KEY (UserId)
    REFERENCES user (UserId) ON DELETE CASCADE
) ENGINE = InnoDB;


INSERT INTO user (Username, HashedPassword, AccountNonExpired,
                           AccountNonLocked, CredentialsNonExpired, Enabled)
VALUES ( -- s1a2t3o4r
  'Carol', '$2a$10$.6kYphQ8VqJ9NPKtMje.JeWt1aoX4/ZRzVFGiO7Cen.rk88laGTCi',
  TRUE, TRUE, TRUE, TRUE
);

INSERT INTO user (Username, HashedPassword, AccountNonExpired,
                           AccountNonLocked, CredentialsNonExpired, Enabled)
VALUES ( -- a5r6e7p8o
  'Albert', '$2a$10$CXNlyhzVkeHbqNpIJNBTl.WXP9WVouQwqh7M7IkI/WTXywakU5kha',
  TRUE, TRUE, TRUE, TRUE
);

INSERT INTO user (Username, HashedPassword, AccountNonExpired,
                           AccountNonLocked, CredentialsNonExpired, Enabled)
VALUES ( -- t4e3n2e1t
  'Werner', '$2a$10$BKSkkYAh5COX/vvQrwPwuucL77Ydf61EEd97kwaBndKtxHJktQX/S',
  TRUE, TRUE, TRUE, TRUE
);

INSERT INTO user (Username, HashedPassword, AccountNonExpired,
                           AccountNonLocked, CredentialsNonExpired, Enabled)
VALUES ( -- o8p7e6r5a
  'Alice', '$2a$10$fDcpl4fYkqyaKwVfBOFwTu5igi7yXzCw2AWp0oSkZ0iwMXzZsZ2t.',
  TRUE, TRUE, TRUE, TRUE
);

INSERT INTO user (Username, HashedPassword, AccountNonExpired,
                           AccountNonLocked, CredentialsNonExpired, Enabled)
VALUES ( -- r1o2t3a4s
  'Richard', '$2a$10$ej/Cmw3p0b8UbWOQAhiEW.2LW03nspV2yLFaoli0CdxK8./miktoW',
  TRUE, TRUE, TRUE, TRUE
);



INSERT INTO user_Authority (UserId, Authority)
  VALUES (1, 'VIEW');

INSERT INTO user_Authority (UserId, Authority)
  VALUES (2, 'VIEW');

INSERT INTO user_Authority (UserId, Authority)
  VALUES (3, 'VIEW'), (3, 'UPDATE');

INSERT INTO user_Authority (UserId, Authority)
  VALUES (4, 'VIEW'), (4, 'CREATE'), (4, 'UPDATE');

INSERT INTO user_Authority (UserId, Authority)
  VALUES (5, 'VIEW'), (5, 'CREATE'), (5, 'UPDATE'), (5, 'DELETE');


