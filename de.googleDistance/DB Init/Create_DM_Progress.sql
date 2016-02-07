CREATE TABLE `DM_Progress` (
	`Task_Nr`	INTEGER NOT NULL,
	`Accuracy`	INTEGER NOT NULL,
	`Destination_X`	REAL NOT NULL,
	`Destination_Y`	REAL NOT NULL,
	`Step_Length`	REAL NOT NULL,
	PRIMARY KEY(Task_Nr,Accuracy,Destination_X,Destination_Y)
);