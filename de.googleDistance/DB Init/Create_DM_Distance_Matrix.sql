CREATE TABLE `DM_Distance_Matrix` (
	`Start_X`	REAL NOT NULL,
	`Start_Y`	REAL NOT NULL,
	`Destination_X`	REAL NOT NULL,
	`Destination_Y`	REAL NOT NULL,
	`Duration`	INTEGER NOT NULL,
	PRIMARY KEY(Start_Y,Start_X,Destination_X,Destination_Y)
);