USE cmsdb;

-- 1. Conference
CREATE TABLE Conference (
                            ConferenceID INT AUTO_INCREMENT PRIMARY KEY,
                            Name VARCHAR(255) NOT NULL,
                            Acronym VARCHAR(50) NOT NULL,
                            Year INT NOT NULL,
                            Start_date DATE NOT NULL,
                            End_date DATE NOT NULL,
                            Submission_deadline DATETIME NOT NULL,
                            Notification_date DATE NOT NULL,
                            PC_member_deadline DATE NOT NULL,
                            Venue_details TEXT
);

-- 2. User (Master list of all individuals)
CREATE TABLE User (
                      UserID INT AUTO_INCREMENT PRIMARY KEY,
                      Name VARCHAR(255) NOT NULL,
                      Email VARCHAR(255) UNIQUE NOT NULL,
                      Affiliation VARCHAR(255),
                      Password_hash CHAR(60) NOT NULL
);

-- 3. Submission
CREATE TABLE Submission (
                            SubmissionID INT AUTO_INCREMENT PRIMARY KEY,
                            ConferenceID INT NOT NULL,
                            Title VARCHAR(500) NOT NULL,
                            Abstract TEXT,
                            File_URL VARCHAR(255),
                            Status VARCHAR(50) NOT NULL,
                            FOREIGN KEY (ConferenceID) REFERENCES Conference(ConferenceID)
                                ON UPDATE CASCADE ON DELETE CASCADE
);

-- 4. Topic (Research areas/keywords)
CREATE TABLE Topic (
                       TopicID INT AUTO_INCREMENT PRIMARY KEY,
                       Name VARCHAR(255) UNIQUE NOT NULL
);

-- 5. Role (Defines general and sub-roles with hierarchy)
CREATE TABLE Role (
                      RoleID INT AUTO_INCREMENT PRIMARY KEY,
                      Name VARCHAR(50) UNIQUE NOT NULL,
                      Description TEXT,
                      ParentRoleID INT,
                      FOREIGN KEY (ParentRoleID) REFERENCES Role(RoleID)
                          ON UPDATE CASCADE ON DELETE SET NULL
);

-- 6. Author_Submission (Links User to Submission)
CREATE TABLE Author_Submission (
                                   UserID INT NOT NULL,
                                   SubmissionID INT NOT NULL,
                                   Is_primaryAuthor BOOLEAN,
                                   PRIMARY KEY (UserID, SubmissionID),
                                   FOREIGN KEY (UserID) REFERENCES User(UserID)
                                       ON UPDATE CASCADE ON DELETE CASCADE,
                                   FOREIGN KEY (SubmissionID) REFERENCES Submission(SubmissionID)
                                       ON UPDATE CASCADE ON DELETE CASCADE
);

-- 7. Reviewer_Expertise (M:M between User and Topic)
CREATE TABLE Reviewer_Expertise (
                                    ReviewerID INT NOT NULL,
                                    TopicID INT NOT NULL,
                                    Expertise_level INT CHECK (Expertise_level BETWEEN 1 AND 5),
                                    PRIMARY KEY (ReviewerID, TopicID),
                                    FOREIGN KEY (ReviewerID) REFERENCES User(UserID)
                                        ON UPDATE CASCADE ON DELETE CASCADE,
                                    FOREIGN KEY (TopicID) REFERENCES Topic(TopicID)
                                        ON UPDATE CASCADE ON DELETE CASCADE
);

-- 8. User_Role (M:M between User and Role)
CREATE TABLE User_Role (
                           UserID INT NOT NULL,
                           RoleID INT NOT NULL,
                           PRIMARY KEY (UserID, RoleID),
                           FOREIGN KEY (UserID) REFERENCES User(UserID)
                               ON UPDATE CASCADE ON DELETE CASCADE,
                           FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
                               ON UPDATE CASCADE ON DELETE CASCADE
);

-- 9. Review (The core review report)
CREATE TABLE Review (
                        ReviewID INT AUTO_INCREMENT PRIMARY KEY,
                        SubmissionID INT NOT NULL,
                        ReviewerID INT NOT NULL,
                        Overall_score INT,
                        Confidence_score INT,
                        Comment_to_Author TEXT,
                        Comments_to_PC TEXT,
                        FOREIGN KEY (SubmissionID) REFERENCES Submission(SubmissionID)
                            ON UPDATE CASCADE ON DELETE CASCADE,
                        FOREIGN KEY (ReviewerID) REFERENCES User(UserID)
                            ON UPDATE CASCADE ON DELETE CASCADE
);

-- 10. Review_Quality_Rating (Author/PC feedback on the review)
CREATE TABLE Review_Quality_Rating (
                                       RatingID INT AUTO_INCREMENT PRIMARY KEY,
                                       ReviewID INT NOT NULL,
                                       RaterUserID INT NOT NULL,
                                       Helpfulness_score INT CHECK (Helpfulness_score BETWEEN 1 AND 5),
                                       FOREIGN KEY (ReviewID) REFERENCES Review(ReviewID)
                                           ON UPDATE CASCADE ON DELETE CASCADE,
                                       FOREIGN KEY (RaterUserID) REFERENCES User(UserID)
                                           ON UPDATE CASCADE ON DELETE CASCADE
);

-- 11. Badge (Achievement definitions)
CREATE TABLE Badge (
                       BadgeID INT AUTO_INCREMENT PRIMARY KEY,
                       Name VARCHAR(100) NOT NULL,
                       Criteria TEXT NOT NULL,
                       Image_URL VARCHAR(255)
);

-- 12. UserScore (Cumulative scores for each badge)
CREATE TABLE UserScore (
                           UserID INT NOT NULL,
                           BadgeID INT NOT NULL,
                           Value INT NOT NULL DEFAULT 0,
                           PRIMARY KEY (UserID, BadgeID),
                           FOREIGN KEY (UserID) REFERENCES User(UserID)
                               ON UPDATE CASCADE ON DELETE CASCADE,
                           FOREIGN KEY (BadgeID) REFERENCES Badge(BadgeID)
                               ON UPDATE CASCADE ON DELETE CASCADE
);

-- 13. UserBadge (M:M between User and Badge)
CREATE TABLE UserBadge (
                           UserID INT NOT NULL,
                           BadgeID INT NOT NULL,
                           Date_Earned DATETIME NOT NULL,
                           PRIMARY KEY (UserID, BadgeID),
                           FOREIGN KEY (UserID) REFERENCES User(UserID)
                               ON UPDATE CASCADE ON DELETE CASCADE,
                           FOREIGN KEY (BadgeID) REFERENCES Badge(BadgeID)
                               ON UPDATE CASCADE ON DELETE CASCADE
);

-- 14. Submission_Payment (Fees related directly to a paper)
CREATE TABLE Submission_Payment (
                                    SubPaymentID INT AUTO_INCREMENT PRIMARY KEY,
                                    UserID INT NOT NULL,
                                    SubmissionID INT NOT NULL,
                                    Amount DECIMAL(10,2) NOT NULL,
                                    Payment_Date DATETIME NOT NULL,
                                    Payment_Method VARCHAR(50),
                                    Status VARCHAR(50) NOT NULL,
                                    FOREIGN KEY (UserID) REFERENCES User(UserID)
                                        ON UPDATE CASCADE ON DELETE CASCADE,
                                    FOREIGN KEY (SubmissionID) REFERENCES Submission(SubmissionID)
                                        ON UPDATE CASCADE ON DELETE CASCADE
);

-- 15. Registration (Attendee sign-up details and preferences)
CREATE TABLE Registration (
                              RegistrationID INT AUTO_INCREMENT PRIMARY KEY,
                              UserID INT NOT NULL,
                              ConferenceID INT NOT NULL,
                              Registration_Type VARCHAR(50) NOT NULL,
                              Registration_Date DATETIME NOT NULL,
                              Attending_Status VARCHAR(50) NOT NULL,
                              Meal_Preference VARCHAR(50),
                              Special_Requests TEXT,
                              FOREIGN KEY (UserID) REFERENCES User(UserID)
                                  ON UPDATE CASCADE ON DELETE CASCADE,
                              FOREIGN KEY (ConferenceID) REFERENCES Conference(ConferenceID)
                                  ON UPDATE CASCADE ON DELETE CASCADE
);

-- 16. Registration_Payment (Financial records linked to Registration)
CREATE TABLE Registration_Payment (
                                      RegPaymentID INT AUTO_INCREMENT PRIMARY KEY,
                                      RegistrationID INT NOT NULL,
                                      Amount DECIMAL(10,2) NOT NULL,
                                      Payment_Date DATETIME NOT NULL,
                                      Payment_Method VARCHAR(50),
                                      Status VARCHAR(50) NOT NULL,
                                      FOREIGN KEY (RegistrationID) REFERENCES Registration(RegistrationID)
                                          ON UPDATE CASCADE ON DELETE CASCADE
);

-- 17. Submission_History (Full lifecycle audit trail)
CREATE TABLE Submission_History (
                                    HistoryID INT AUTO_INCREMENT PRIMARY KEY,
                                    SubmissionID INT NOT NULL,
                                    Status_Change_Date DATETIME NOT NULL,
                                    Old_Status VARCHAR(50),
                                    New_Status VARCHAR(50) NOT NULL,
                                    Changed_By_UserID INT,
                                    FOREIGN KEY (SubmissionID) REFERENCES Submission(SubmissionID)
                                        ON UPDATE CASCADE ON DELETE CASCADE,
                                    FOREIGN KEY (Changed_By_UserID) REFERENCES User(UserID)
                                        ON UPDATE CASCADE ON DELETE SET NULL
);

-- 18. User_Notification (System communication)
CREATE TABLE User_Notification (
                                   NotificationID INT AUTO_INCREMENT PRIMARY KEY,
                                   UserID INT NOT NULL,
                                   Type VARCHAR(50) NOT NULL,
                                   Message TEXT NOT NULL,
                                   Is_Read BOOLEAN DEFAULT FALSE,
                                   Created_Date DATETIME NOT NULL,
                                   FOREIGN KEY (UserID) REFERENCES User(UserID)
                                       ON UPDATE CASCADE ON DELETE CASCADE
);

-- 19. Permission (Defines every possible action)
CREATE TABLE Permission (
                            PermissionID INT AUTO_INCREMENT PRIMARY KEY,
                            Name VARCHAR(100) UNIQUE NOT NULL,
                            Module VARCHAR(50)
);

-- 20. Role_Permission (M:M between Role and Permission for access control)
CREATE TABLE Role_Permission (
                                 RoleID INT NOT NULL,
                                 PermissionID INT NOT NULL,
                                 PRIMARY KEY (RoleID, PermissionID),
                                 FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
                                     ON UPDATE CASCADE ON DELETE CASCADE,
                                 FOREIGN KEY (PermissionID) REFERENCES Permission(PermissionID)
                                     ON UPDATE CASCADE ON DELETE CASCADE
);