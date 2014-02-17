# --- !Ups
CREATE TABLE EVENTS (
	EVENT_ID BIGINT(6) GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,
	EVENT_NAME VARCHAR(100) NOT NULL,
	EVENT_DATE DATE,
	PERFORMER VARCHAR(200),
	LOCATION VARCHAR(200)
);

# --- !Downs
DROP TABLE EVENTS;