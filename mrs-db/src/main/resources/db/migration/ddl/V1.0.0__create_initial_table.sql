DROP TABLE IF EXISTS meeting_room CASCADE;
DROP TABLE IF EXISTS reservable_room CASCADE;
DROP TABLE IF EXISTS reservation CASCADE;
DROP TABLE IF EXISTS usr CASCADE;

CREATE TABLE IF NOT EXISTS usr (
  user_id    VARCHAR(255) PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  role_name  VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS meeting_room (
  room_id   SERIAL       PRIMARY KEY,
  room_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS reservable_room (
  reserved_date DATE    NOT NULL,
  room_id       INT4    NOT NULL REFERENCES meeting_room (room_id),
  PRIMARY KEY (reserved_date, room_id)
);

CREATE TABLE IF NOT EXISTS reservation (
  reservation_id SERIAL       PRIMARY KEY,
  end_time       TIME         NOT NULL,
  start_time     TIME         NOT NULL,
  reserved_date  DATE         NOT NULL,
  room_id        INT4         NOT NULL,
  user_id        VARCHAR(255) NOT NULL REFERENCES usr (user_id),
  FOREIGN KEY (reserved_date, room_id) REFERENCES reservable_room (reserved_date, room_id)
);