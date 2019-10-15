/*
 Navicat Premium Data Transfer

 Source Server         : pgsqlhost
 Source Server Type    : PostgreSQL
 Source Server Version : 90614
 Source Host           : localhost:5432
 Source Catalog        : ExamTips
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90614
 File Encoding         : 65001

 Date: 13/10/2019 16:19:02
*/


-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS "public"."admin";
CREATE TABLE "public"."admin" (
  "id" int4 NOT NULL,
  "username" varchar(50) COLLATE "pg_catalog"."default",
  "password" varchar(50) COLLATE "pg_catalog"."default",
  "name" varchar(50) COLLATE "pg_catalog"."default",
  "phone" varchar(11) COLLATE "pg_catalog"."default"
)
;
-- pgsql 自增id设置
DROP TABLE IF EXISTS "public"."admin";
CREATE TABLE "public"."admin" (
  "id" SERIAL primary key,
  "system" varchar(50) COLLATE "pg_catalog"."default",
  "password" varchar(50) COLLATE "pg_catalog"."default",
  "name" varchar(50) COLLATE "pg_catalog"."default",
  "phone" varchar(11) COLLATE "pg_catalog"."default",
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "deleted" int4  NOT NULL DEFAULT 0
)
;
-- ----------------------------
-- Table structure for advertisement
-- ----------------------------
DROP TABLE IF EXISTS "public"."advertisement";
CREATE TABLE "public"."advertisement" (
   "id" SERIAL primary key,
  "exam_type_id" int4,
  "ad_type" int4,
  "ad_title" varchar(50) COLLATE "pg_catalog"."default",
  "ad_picture" varchar(255) COLLATE "pg_catalog"."default",
  "ad_comment" varchar(255) COLLATE "pg_catalog"."default",
  "creater_admin_id" int4,
  "status" int4,
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "deleted" int4  NOT NULL DEFAULT 0
)
;

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS "public"."collection";
CREATE TABLE "public"."collection" (
  "id" SERIAL primary key,
  "topic_id" int4,
  "exam_tips_id" int4,
  "person_id" int4,
  "advertisement_id" int4,
  "status" int4,
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "deleted" int4  NOT NULL DEFAULT 0
)
;

-- ----------------------------
-- Table structure for exam_note
-- ----------------------------
DROP TABLE IF EXISTS "public"."exam_note";
CREATE TABLE "public"."exam_note" (
   "id" SERIAL primary key,
  "my_exam_id" int4,
  "exam_user" varchar(50) COLLATE "pg_catalog"."default",
  "exam_pass" varchar(50) COLLATE "pg_catalog"."default",
  "note_comment" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "deleted" int4  NOT NULL DEFAULT 0
)
;

-- ----------------------------
-- Table structure for exam_tips
-- ----------------------------
DROP TABLE IF EXISTS "public"."exam_tips";
CREATE TABLE "public"."exam_tips" (
  "id" SERIAL primary key,
  "exam_type_id" int4,
  "exam_url" varchar(255) COLLATE "pg_catalog"."default",
  "exam_tips_title" varchar(255) COLLATE "pg_catalog"."default",
  "exam_comment" varchar(255) COLLATE "pg_catalog"."default",
  "signup_begintime" timestamp(6) NOT NULL,
  "signup_endtime" timestamp(6) NOT NULL,
  "exam_begintime" timestamp(6) NOT NULL,
  "exam_endtime" timestamp(6) NOT NULL,
  "click_number" int4 NOT NULL DEFAULT 0,
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "deleted" int4  NOT NULL DEFAULT 0
)
;

-- ----------------------------
-- Table structure for exam_type
-- ----------------------------
DROP TABLE IF EXISTS "public"."exam_type";
CREATE TABLE "public"."exam_type" (
   "id" SERIAL primary key,
  "exam_name" varchar(255) COLLATE "pg_catalog"."default",
  "parent_id" int4,
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "deleted" int4  NOT NULL DEFAULT 0
)
;

-- ----------------------------
-- Table structure for forum
-- ----------------------------
DROP TABLE IF EXISTS "public"."forum";
CREATE TABLE "public"."forum" (
   "id" SERIAL primary key,
  "forum_name" varchar(50) COLLATE "pg_catalog"."default",
  "exam_type_id" int4,
  "forum_description" varchar(255) COLLATE "pg_catalog"."default",
  "creater_admin_id" int4,
  "person_or_admin" int4,
  "join_number" int4,
  "click_number" int4,
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "deleted" int4  NOT NULL DEFAULT 0
)
;

-- ----------------------------
-- Table structure for my_exam
-- ----------------------------
DROP TABLE IF EXISTS "public"."my_exam";
CREATE TABLE "public"."my_exam" (
  "id" SERIAL primary key,
  "exam_id" int4,
  "person_id" int4,
  "status" int4,
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "deleted" int4  NOT NULL DEFAULT 0
)
;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS "public"."person";
CREATE TABLE "public"."person" (
  "id" SERIAL primary key,
  "name" varchar(50) COLLATE "pg_catalog"."default",
  "username" varchar(50) COLLATE "pg_catalog"."default",
  "password" varchar(50) COLLATE "pg_catalog"."default",
  "sex" int4,
  "phone" varchar(11) COLLATE "pg_catalog"."default",
  "age" int4,
  "education" varchar(50) COLLATE "pg_catalog"."default",
  "industry" varchar(50) COLLATE "pg_catalog"."default",
  "employ_status" int4,
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "deleted" int4  NOT NULL DEFAULT 0
)
;

-- ----------------------------
-- Table structure for person_join_forum
-- ----------------------------
DROP TABLE IF EXISTS "public"."person_join_forum";
CREATE TABLE "public"."person_join_forum" (
  "id" SERIAL primary key,
  "person_id" int4,
  "forum_id" int4,
  "join_status" int4,
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "deleted" int4  NOT NULL DEFAULT 0
)
;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS "public"."record";
CREATE TABLE "public"."record" (
  "id" SERIAL primary key,
  "person_id" int4,
  "topic_id" int4,
  "reply_id" int4,
  "forum_id" int4,
  "person_join_forum_id" int4,
  "join_status" int4,
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "deleted" int4  NOT NULL DEFAULT 0
)
;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS "public"."reply";
CREATE TABLE "public"."reply" (
  "id" SERIAL primary key,
  "topic_id" int4,
  "reply_comment" varchar(255) COLLATE "pg_catalog"."default",
  "reply_time" time(6),
  "replier_person_id" int4,
  "reply_praise_number" int4,
  "status" int4,
    "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "deleted" int4  NOT NULL DEFAULT 0
)
;

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS "public"."topic";
CREATE TABLE "public"."topic" (
   "id" SERIAL primary key,
  "forum_id" int4,
  "exam_type_id" int4,
  "creater_person_id" int4,
  "topic_title" varchar(50) COLLATE "pg_catalog"."default",
  "topic_comment" varchar(255) COLLATE "pg_catalog"."default",
  "reply_number" int4,
  "praise_number" int4,
  "click_number" int4,
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "deleted" int4  NOT NULL DEFAULT 0
)
;

-- ----------------------------
-- Table structure for topic_label
-- ----------------------------
DROP TABLE IF EXISTS "public"."topic_label";
CREATE TABLE "public"."topic_label" (
 "id" SERIAL primary key,
  "topic_id" int4,
  "exam_type_id" int4,
    "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  "deleted" int4  NOT NULL DEFAULT 0
)
;

-- ----------------------------
-- Primary Key structure for table admin
-- ----------------------------
ALTER TABLE "public"."admin" ADD CONSTRAINT "admin_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table advertisement
-- ----------------------------
CREATE INDEX "FK_admin_advertisement_r" ON "public"."advertisement" USING btree (
  "creater_admin_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "FK_examtype_advertisement_r" ON "public"."advertisement" USING btree (
  "exam_type_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table advertisement
-- ----------------------------
ALTER TABLE "public"."advertisement" ADD CONSTRAINT "advertisement_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table collection
-- ----------------------------
CREATE INDEX "FK_advertisement_collection_r" ON "public"."collection" USING btree (
  "advertisement_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "FK_examtips_collection_r" ON "public"."collection" USING btree (
  "exam_tips_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "FK_person_collection_r" ON "public"."collection" USING btree (
  "person_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "FK_topic_collection" ON "public"."collection" USING btree (
  "topic_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table collection
-- ----------------------------
ALTER TABLE "public"."collection" ADD CONSTRAINT "collection_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table exam_note
-- ----------------------------
CREATE INDEX "FK_myexam_userpassnote_r" ON "public"."exam_note" USING btree (
  "my_exam_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table exam_note
-- ----------------------------
ALTER TABLE "public"."exam_note" ADD CONSTRAINT "exam_note_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table exam_tips
-- ----------------------------
CREATE INDEX "FK_examtips_examtype" ON "public"."exam_tips" USING btree (
  "exam_type_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table exam_tips
-- ----------------------------
ALTER TABLE "public"."exam_tips" ADD CONSTRAINT "exam_tips_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table exam_type
-- ----------------------------
CREATE INDEX "FK_examtype的自连接" ON "public"."exam_type" USING btree (
  "parent_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table exam_type
-- ----------------------------
ALTER TABLE "public"."exam_type" ADD CONSTRAINT "exam_type_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table forum
-- ----------------------------
CREATE INDEX "FK_admin_forum_r" ON "public"."forum" USING btree (
  "creater_admin_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "FK_examtype_forum_r" ON "public"."forum" USING btree (
  "exam_type_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table forum
-- ----------------------------
ALTER TABLE "public"."forum" ADD CONSTRAINT "forum_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table my_exam
-- ----------------------------
CREATE INDEX "FK_examtips_myexam_r" ON "public"."my_exam" USING btree (
  "exam_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "FK_person_myexam_r" ON "public"."my_exam" USING btree (
  "person_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table my_exam
-- ----------------------------
ALTER TABLE "public"."my_exam" ADD CONSTRAINT "my_exam_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table person
-- ----------------------------
ALTER TABLE "public"."person" ADD CONSTRAINT "person_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table person_join_forum
-- ----------------------------
CREATE INDEX "FK_forum_person_r" ON "public"."person_join_forum" USING btree (
  "forum_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "FK_person_forum_r" ON "public"."person_join_forum" USING btree (
  "person_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table person_join_forum
-- ----------------------------
ALTER TABLE "public"."person_join_forum" ADD CONSTRAINT "person_join_forum_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table record
-- ----------------------------
CREATE INDEX "FK_forum_record_r" ON "public"."record" USING btree (
  "forum_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "FK_person_record" ON "public"."record" USING btree (
  "person_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "FK_personjoinforum_record_r" ON "public"."record" USING btree (
  "person_join_forum_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "FK_reply_record_r" ON "public"."record" USING btree (
  "reply_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "FK_topic_record_r" ON "public"."record" USING btree (
  "topic_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table record
-- ----------------------------
ALTER TABLE "public"."record" ADD CONSTRAINT "record_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table reply
-- ----------------------------
CREATE INDEX "FK_person_reply_c" ON "public"."reply" USING btree (
  "replier_person_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "FK_topic_reply_r" ON "public"."reply" USING btree (
  "topic_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table reply
-- ----------------------------
ALTER TABLE "public"."reply" ADD CONSTRAINT "reply_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table topic
-- ----------------------------
CREATE INDEX "FK_forum_topic_r" ON "public"."topic" USING btree (
  "forum_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "FK_person_topic_r" ON "public"."topic" USING btree (
  "creater_person_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table topic
-- ----------------------------
ALTER TABLE "public"."topic" ADD CONSTRAINT "topic_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table topic_label
-- ----------------------------
CREATE INDEX "FK_examtype_topic_r" ON "public"."topic_label" USING btree (
  "exam_type_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "FK_topic_examtype_r" ON "public"."topic_label" USING btree (
  "topic_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table topic_label
-- ----------------------------
ALTER TABLE "public"."topic_label" ADD CONSTRAINT "topic_label_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table advertisement
-- ----------------------------
ALTER TABLE "public"."advertisement" ADD CONSTRAINT "FK_admin_advertisement_r" FOREIGN KEY ("creater_admin_id") REFERENCES "admin" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."advertisement" ADD CONSTRAINT "FK_examtype_advertisement_r" FOREIGN KEY ("exam_type_id") REFERENCES "exam_type" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table collection
-- ----------------------------
ALTER TABLE "public"."collection" ADD CONSTRAINT "FK_advertisement_collection_r" FOREIGN KEY ("advertisement_id") REFERENCES "advertisement" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."collection" ADD CONSTRAINT "FK_examtips_collection_r" FOREIGN KEY ("exam_tips_id") REFERENCES "exam_tips" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."collection" ADD CONSTRAINT "FK_person_collection_r" FOREIGN KEY ("person_id") REFERENCES "person" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."collection" ADD CONSTRAINT "FK_topic_collection" FOREIGN KEY ("topic_id") REFERENCES "topic" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table exam_note
-- ----------------------------
ALTER TABLE "public"."exam_note" ADD CONSTRAINT "FK_myexam_userpassnote_r" FOREIGN KEY ("my_exam_id") REFERENCES "my_exam" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table exam_tips
-- ----------------------------
ALTER TABLE "public"."exam_tips" ADD CONSTRAINT "FK_examtips_examtype" FOREIGN KEY ("exam_type_id") REFERENCES "exam_type" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table exam_type
-- ----------------------------
ALTER TABLE "public"."exam_type" ADD CONSTRAINT "FK_examtype的自连接" FOREIGN KEY ("parent_id") REFERENCES "exam_type" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table forum
-- ----------------------------
ALTER TABLE "public"."forum" ADD CONSTRAINT "FK_admin_forum_r" FOREIGN KEY ("creater_admin_id") REFERENCES "admin" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."forum" ADD CONSTRAINT "FK_examtype_forum_r" FOREIGN KEY ("exam_type_id") REFERENCES "exam_type" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table my_exam
-- ----------------------------
ALTER TABLE "public"."my_exam" ADD CONSTRAINT "FK_examtips_myexam_r" FOREIGN KEY ("exam_id") REFERENCES "exam_tips" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."my_exam" ADD CONSTRAINT "FK_person_myexam_r" FOREIGN KEY ("person_id") REFERENCES "person" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table person_join_forum
-- ----------------------------
ALTER TABLE "public"."person_join_forum" ADD CONSTRAINT "FK_forum_person_r" FOREIGN KEY ("forum_id") REFERENCES "forum" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."person_join_forum" ADD CONSTRAINT "FK_person_forum_r" FOREIGN KEY ("person_id") REFERENCES "person" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table record
-- ----------------------------
ALTER TABLE "public"."record" ADD CONSTRAINT "FK_forum_record_r" FOREIGN KEY ("forum_id") REFERENCES "forum" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."record" ADD CONSTRAINT "FK_person_record" FOREIGN KEY ("person_id") REFERENCES "person" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."record" ADD CONSTRAINT "FK_personjoinforum_record_r" FOREIGN KEY ("person_join_forum_id") REFERENCES "person_join_forum" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."record" ADD CONSTRAINT "FK_reply_record_r" FOREIGN KEY ("reply_id") REFERENCES "reply" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."record" ADD CONSTRAINT "FK_topic_record_r" FOREIGN KEY ("topic_id") REFERENCES "topic" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table reply
-- ----------------------------
ALTER TABLE "public"."reply" ADD CONSTRAINT "FK_person_reply_c" FOREIGN KEY ("replier_person_id") REFERENCES "person" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."reply" ADD CONSTRAINT "FK_topic_reply_r" FOREIGN KEY ("topic_id") REFERENCES "topic" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table topic
-- ----------------------------
ALTER TABLE "public"."topic" ADD CONSTRAINT "FK_forum_topic_r" FOREIGN KEY ("forum_id") REFERENCES "forum" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."topic" ADD CONSTRAINT "FK_person_topic_r" FOREIGN KEY ("creater_person_id") REFERENCES "person" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table topic_label
-- ----------------------------
ALTER TABLE "public"."topic_label" ADD CONSTRAINT "FK_examtype_topic_r" FOREIGN KEY ("exam_type_id") REFERENCES "exam_type" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."topic_label" ADD CONSTRAINT "FK_topic_examtype_r" FOREIGN KEY ("topic_id") REFERENCES "topic" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
