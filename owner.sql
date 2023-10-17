BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "owner" (
	"id"	integer,
	"name"	varchar(50) DEFAULT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "animal" (
	"id"	integer,
	"name"	varchar(50) DEFAULT NULL,
	"age"	integer DEFAULT NULL,
	"owner_id"	integer DEFAULT NULL,
	PRIMARY KEY("id" AUTOINCREMENT),
	CONSTRAINT "fk_animal_owner" FOREIGN KEY("owner_id") REFERENCES "owner"("id")
);
CREATE TABLE IF NOT EXISTS "product" (
	"id"	integer,
	"name"	varchar(50) DEFAULT NULL,
	"price"	integer DEFAULT NULL,
	"animal_id"	integer DEFAULT NULL,
	PRIMARY KEY("id" AUTOINCREMENT),
	CONSTRAINT "fk_product_animal" FOREIGN KEY("animal_id") REFERENCES "animal"("id")
);
INSERT INTO "owner" VALUES (1,'최길동');
INSERT INTO "owner" VALUES (2,'홍길동');
INSERT INTO "animal" VALUES (1,'슈슈',2,1);
INSERT INTO "animal" VALUES (2,'치치',2,1);
INSERT INTO "animal" VALUES (3,'마당이',5,2);
INSERT INTO "animal" VALUES (4,'얼룩이',7,2);
INSERT INTO "product" VALUES (1,'목줄',5000,1);
INSERT INTO "product" VALUES (2,'몸통줄',5000,2);
INSERT INTO "product" VALUES (3,'중형견 사료',10000,1);
INSERT INTO "product" VALUES (4,'소형견 사료',10000,2);
INSERT INTO "product" VALUES (5,'중형묘 사료',12000,3);
INSERT INTO "product" VALUES (6,'소형묘 사료',12000,4);
COMMIT;
