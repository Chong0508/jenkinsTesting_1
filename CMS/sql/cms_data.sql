USE cmsdb;

INSERT INTO Conference (Name, Acronym, Year, Start_date, End_date, Submission_deadline, Notification_date, PC_member_deadline, Venue_details)
VALUES
('International Conference on AI', 'ICAI', 2025, '2025-07-10', '2025-07-12', '2025-05-31 23:59:59', '2025-06-20', '2025-06-05', 'Kuala Lumpur Convention Center, KL, Malaysia'),
('Web Engineering Conference', 'WEC', 2025, '2025-09-01', '2025-09-03', '2025-07-15 23:59:59', '2025-08-10', '2025-07-25', 'Sunway Resort Hotel, Selangor, Malaysia'),
('International Cybersecurity Summit', 'ICS', 2025, '2025-11-15', '2025-11-17', '2025-09-30 23:59:59', '2025-10-20', '2025-10-05', 'Putrajaya International Convention Centre, Putrajaya'),
('Machine Learning Symposium', 'MLS', 2025, '2025-08-20', '2025-08-22', '2025-07-10 23:59:59', '2025-07-30', '2025-07-15', 'Penang International Convention & Exhibition Centre, Penang'),
('Software Testing Conference', 'STC', 2025, '2025-12-05', '2025-12-07', '2025-10-25 23:59:59', '2025-11-15', '2025-11-01', 'Johor Bahru Convention Center, Johor');

INSERT INTO User (Name, Email, Affiliation, Password_hash)
VALUES
('Ali Ahmad', 'ali@gmail.com', 'University of Malaya', '$2y$10$examplehash1'),
('Siti Zainal', 'siti@gmail.com', 'Universiti Teknologi Malaysia', '$2y$10$examplehash2'),
('John Tan', 'john@gmail.com', 'Monash University Malaysia', '$2y$10$examplehash3'),
('Nur Farah', 'farah@gmail.com', 'Multimedia University', '$2y$10$examplehash4'),
('David Lim', 'david@gmail.com', 'Asia Pacific University', '$2y$10$examplehash5'),
('Maya Lee', 'maya@gmail.com', 'Sunway University', '$2y$10$examplehash6');

INSERT INTO Submission (ConferenceID, Title, Abstract, File_URL, Status)
VALUES
(1, 'AI in Healthcare: Trends and Challenges', 'Explores AI applications in healthcare, including predictive diagnostics and medical imaging.', 'uploads/ai_healthcare.pdf', 'Submitted'),
(1, 'Natural Language Processing Advances', 'Reviews recent NLP advances and applications in translation and chatbots.', 'uploads/nlp_advances.pdf', 'Under Review'),
(2, 'Web Security in 2025', 'Analyzes web application vulnerabilities and mitigation strategies.', 'uploads/web_security.pdf', 'Accepted'),
(3, 'Cyber Threat Intelligence', 'Examines global cyber threats and best practices for cybersecurity.', 'uploads/cyber_threat.pdf', 'Rejected'),
(4, 'Deep Learning Optimization Techniques', 'Discusses optimization methods in deep learning models.', 'uploads/dl_optimization.pdf', 'Submitted'),
(4, 'ML in Financial Forecasting', 'Applies ML models for predicting financial trends.', 'uploads/ml_finance.pdf', 'Under Review'),
(5, 'Automated Software Testing Tools', 'Evaluates tools for automated software testing and integration.', 'uploads/auto_testing.pdf', 'Accepted');

INSERT INTO Topic (Name)
VALUES
('Artificial Intelligence'),
('Machine Learning'),
('Natural Language Processing'),
('Cybersecurity'),
('Web Development'),
('Software Testing'),
('Data Mining'),
('Cloud Computing');

INSERT INTO Role (Name, Description, ParentRoleID)
VALUES
('Admin', 'System administrator with full access', NULL),
('Conference Chair', 'Oversees conference organization', NULL),
('Program Committee Member', 'Reviews submissions and gives scores', 2),
('Author', 'Submits papers to conferences', NULL),
('Reviewer', 'Reviews assigned submissions', 3),
('Attendee', 'Registers and attends conferences', NULL);

INSERT INTO Author_Submission (UserID, SubmissionID, Is_primaryAuthor)
VALUES
(1, 1, TRUE),
(2, 1, FALSE),
(3, 2, TRUE),
(4, 3, TRUE),
(5, 4, TRUE),
(1, 5, TRUE),
(6, 6, TRUE),
(2, 7, TRUE);

INSERT INTO Reviewer_Expertise (ReviewerID, TopicID, Expertise_level)
VALUES
(5, 1, 5),
(5, 2, 4),
(2, 3, 4),
(3, 4, 5),
(4, 5, 3),
(6, 6, 5),
(1, 2, 3),
(2, 7, 4),
(3, 8, 4);

INSERT INTO User_Role (UserID, RoleID)
VALUES
(1, 1),
(1, 4),
(2, 4),
(2, 5),
(3, 5),
(3, 4),
(4, 4),
(4, 6),
(5, 2),
(5, 5),
(6, 6);

INSERT INTO Review (SubmissionID, ReviewerID, Overall_score, Confidence_score, Comment_to_Author, Comments_to_PC)
VALUES
(1, 2, 8, 4, 'Good coverage of AI in healthcare applications.', 'Recommend acceptance with minor edits.'),
(2, 3, 7, 5, 'Comprehensive NLP analysis but needs clarity.', 'Solid contribution, minor revision.'),
(3, 5, 9, 5, 'Excellent discussion on web security practices.', 'Strongly recommend acceptance.'),
(4, 2, 5, 3, 'Good effort but lacks detailed analysis.', 'Reject due to insufficient evaluation.'),
(5, 3, 8, 4, 'Well-explained optimization methods.', 'Accept with minor modifications.'),
(6, 5, 7, 5, 'Interesting insights into finance forecasting.', 'Accept for presentation.');

INSERT INTO Review_Quality_Rating (ReviewID, RaterUserID, Helpfulness_score)
VALUES
(1, 1, 5),
(2, 3, 4),
(3, 4, 5),
(4, 5, 3),
(5, 1, 5),
(6, 6, 4);

INSERT INTO Badge (Name, Criteria, Image_URL)
VALUES
('Top Reviewer', 'Awarded for receiving 5-star helpfulness ratings in reviews.', 'images/top_reviewer.png'),
('Best Paper', 'Awarded to authors of top-scoring papers.', 'images/best_paper.png'),
('Early Bird', 'Registered early for the conference.', 'images/early_bird.png'),
('Frequent Contributor', 'Submitted papers to multiple conferences.', 'images/frequent_contributor.png'),
('Active Reviewer', 'Completed at least 3 reviews.', 'images/active_reviewer.png');

INSERT INTO UserScore (UserID, BadgeID, Value)
VALUES
(2, 1, 15),
(3, 1, 10),
(5, 2, 20),
(1, 4, 8),
(6, 3, 5);

INSERT INTO UserBadge (UserID, BadgeID, Date_Earned)
VALUES
(2, 1, '2025-08-01 10:00:00'),
(3, 1, '2025-08-15 09:00:00'),
(5, 2, '2025-09-05 15:30:00'),
(1, 4, '2025-07-20 11:45:00'),
(6, 3, '2025-06-10 08:00:00');

INSERT INTO Submission_Payment (UserID, SubmissionID, Amount, Payment_Date, Payment_Method, Status)
VALUES
(1, 1, 100.00, '2025-05-20 09:00:00', 'Credit Card', 'Paid'),
(3, 2, 120.00, '2025-05-22 10:15:00', 'Online Transfer', 'Paid'),
(4, 3, 90.00, '2025-06-01 11:30:00', 'Debit Card', 'Paid'),
(5, 4, 100.00, '2025-06-05 09:45:00', 'Credit Card', 'Paid'),
(1, 5, 110.00, '2025-06-10 08:50:00', 'Online Transfer', 'Paid'),
(6, 6, 95.00, '2025-06-12 13:20:00', 'Debit Card', 'Pending'),
(2, 7, 105.00, '2025-06-15 14:10:00', 'Credit Card', 'Paid');

INSERT INTO Registration (UserID, ConferenceID, Registration_Type, Registration_Date, Attending_Status, Meal_Preference, Special_Requests)
VALUES
(1, 1, 'Presenter', '2025-07-01 09:00:00', 'Confirmed', 'Vegetarian', 'Need projector for presentation'),
(2, 1, 'Attendee', '2025-07-02 10:30:00', 'Pending', 'Halal', 'Near front seat preferred'),
(3, 2, 'Presenter', '2025-08-15 14:00:00', 'Confirmed', 'No Preference', NULL),
(4, 3, 'Attendee', '2025-10-25 08:45:00', 'Confirmed', 'Vegetarian', 'Require wheelchair access'),
(5, 4, 'Committee', '2025-08-10 09:15:00', 'Confirmed', 'Halal', NULL),
(6, 5, 'Attendee', '2025-11-30 11:00:00', 'Pending', 'No Preference', 'Request early session schedule');

INSERT INTO Registration_Payment (RegistrationID, Amount, Payment_Date, Payment_Method, Status)
VALUES
(1, 250.00, '2025-07-01 09:15:00', 'Credit Card', 'Paid'),
(2, 200.00, '2025-07-02 11:00:00', 'Online Transfer', 'Pending'),
(3, 300.00, '2025-08-15 14:30:00', 'Credit Card', 'Paid'),
(4, 180.00, '2025-10-25 09:00:00', 'Debit Card', 'Paid'),
(5, 0.00, '2025-08-10 09:30:00', 'Waived', 'Approved'),
(6, 220.00, '2025-11-30 11:15:00', 'Credit Card', 'Pending');

INSERT INTO Submission_History (SubmissionID, Status_Change_Date, Old_Status, New_Status, Changed_By_UserID)
VALUES
(1, '2025-05-20 12:00:00', 'Draft', 'Submitted', 1),
(2, '2025-05-25 15:00:00', 'Submitted', 'Under Review', 3),
(3, '2025-06-01 09:30:00', 'Under Review', 'Accepted', 4),
(4, '2025-06-10 10:00:00', 'Under Review', 'Rejected', 2),
(5, '2025-06-15 11:00:00', 'Submitted', 'Under Review', 3),
(6, '2025-06-20 13:00:00', 'Under Review', 'Accepted', 5),
(7, '2025-06-25 10:30:00', 'Submitted', 'Accepted', 2);

INSERT INTO User_Notification (UserID, Type, Message, Is_Read, Created_Date)
VALUES
(1, 'Submission', 'Your paper "AI in Healthcare" has been successfully submitted.', TRUE, '2025-05-20 12:30:00'),
(2, 'Review', 'You have been assigned to review "AI in Healthcare".', FALSE, '2025-05-21 09:00:00'),
(3, 'Payment', 'Your submission fee for "NLP Advances" has been confirmed.', TRUE, '2025-05-22 10:30:00'),
(4, 'Conference', 'Your registration for ICS 2025 is confirmed.', FALSE, '2025-06-01 08:00:00'),
(5, 'Badge', 'You have earned the "Best Paper" badge.', TRUE, '2025-09-05 16:00:00'),
(6, 'Reminder', 'Please complete your payment for MLS registration.', FALSE, '2025-11-25 09:15:00');

INSERT INTO Permission (Name, Module)
VALUES
('Manage Users', 'Admin'),
('Manage Conferences', 'Conference'),
('Manage Submissions', 'Submission'),
('Review Papers', 'Review'),
('View Reports', 'Analytics'),
('Register for Conference', 'Registration'),
('Manage Roles', 'Admin'),
('Assign Reviewers', 'Program Committee'),
('Send Notifications', 'Communication');

INSERT INTO Role_Permission (RoleID, PermissionID)
VALUES
(1, 1), -- Admin -> Manage Users
(1, 2), -- Admin -> Manage Conferences
(1, 7), -- Admin -> Manage Roles
(2, 2), -- Conference Chair -> Manage Conferences
(2, 8), -- Conference Chair -> Assign Reviewers
(2, 9), -- Conference Chair -> Send Notifications
(3, 4), -- PC Member -> Review Papers
(3, 5), -- PC Member -> View Reports
(4, 3), -- Author -> Manage Submissions
(4, 6), -- Author -> Register for Conference
(5, 4), -- Reviewer -> Review Papers
(6, 6); -- Attendee -> Register for Conference