CREATE TABLE board(
	idx INTEGER AUTO_INCREMENT,
	poster VARCHAR(20) NOT NULL,
	subject VARCHAR(255) NOT NULL,
	content TEXT NOT NULL,
	regDt DATETIME DEFAULT NOW(),
	PRIMARY KEY(idx)
);