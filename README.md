# Project PedPap
## cs211-651-project

## รายชื่อสมาชิก
* 6410450133 นายณพณภัทร นรศรี (nopnapatn)
    * ส่วนของ
    * ส่วนของ
    * ส่วนของ
* 641045018 นายปริภัทร์ มะลีแก้ว (Pariphatt)
    * ส่วนของ
    * ส่วนของ
* 6410451181 นายพลบุริศน์ แน่งน้อย (Phonburis)
    * ส่วนของ
    * ส่วนของ
* 6410450184 นายพลภัทร์ สร้อยเสริมทรัพย์ (phollaphat)
    * ส่วนของ
    * ส่วนของ

## วิธีการติดตั้งหรือรันโปรแกรม

## การวางโครงสร้างไฟล์
- assets
    - accounts.csv
    - bannedUsers.csv      
    - reportIssues.csv     
    - reports.csv
    - userIssues.csv
    - votes.csv
- imagesAvatar
    - 2022-10-20_1666271405144.jpg <<< แก้
    - admin1.png
    - admin2.png
    - admin3.png
    - admin4.png
    - profile-staff.png
    - profile-user.png
- src
    - main
        - java
            - com.github.saacsos
            - ku.cs
                - com.github.saacsos.fxrouter-1.0.0
                - controllers
                    - AddReportPageController         
                    - AdminHomePageController         
                    - BannedUserPageController         
                    - ChangePasswordPageController     
                    - CreditPageController             
                    - GuideBookPageController          
                    - HomePageController              
                    - LoginPageController              
                    - ManageReportsPageController      
                    - MyReportPageController        
                    - RegisterPageController         
                    - RegisterStaffPageController    
                    - ReportComplaintPageController    
                    - ReportUserPageController        
                    - ReportUnbanPageController        
                    - RequestUnbanPageController       
                    - StaffHomePageController          
                    - WelcomePageController
                - models
                    - accounts            
                        - Account
                        - AccountList
                        - AdminAccount
                        - StaffAccount
                        - UserAccount
                    - bans                
                        - Banned
                        - BannedList
                    - issues             
                        - UserIssue
                        - UserIssueList   
                    - reposts
                        - Report
                        - ReportList
                        - Vote
                        - VoteList
                    - Mode
                - services
                    - AccountListDataSource
                    - BannedUserListFileDataSource
                    - DataSource
                    - Filterer
                    - ReportFileDataSource
                    - UserListIssueDataSource
                    - VoteDataSource
                - Main
                - ProjectApplication
            - module-info.java
        - resources
            - ku.cs
                - images
                    - arty.jpg
                    - backicon.png
                    - chutter.jpg
                    - eye.png
                    - kulogo.png
                    - kulogo2.png
                    - nat.jpg
                    - neuw.jpg
                    - profile-user.png
                    - test.jpg
                - styles
                - add_report_page.fxml         
                - admin_page.fxml             
                - banned_user_page.fxml    
                - change_password_page.fxml     
                - credit_page.fxml            
                - guide_book_page.fxml       
                - home_page.fxml             
                - login_page.fxml               
                - manage_reports_page.fxml      
                - my_report_page.fxml        
                - register_page.fxml           
                - register_staff_page.fxml      
                - report_complaint_page.fxml   
                - report_user_page.fxml         
                - request_unban_page.fxml       
                - staff_page.fxml               
                - welcome_page.fxml             

## ตัวอย่างข้อมูลผู้ใช้ระบบ
* (Admin) (nopnapat) (123456) <<< Example
* (Staff)
* (Staff)
* (Staff)
* (Staff)
* (User)
* (User)
* (User)
* (User)

## สรุปสิ่งที่พัฒนาแต่ละครั้งที่นำเสนอความก้าวหน้าของระบบ
* ครั้งที่ 1 (12 สิงหาคม 2565)
    * นายณพณภัทร นรศรี (ทำหน้า Welcome Page + List Report)
    * นายปริภัทร์ มะลีแก้ว (ทำหน้า Home + Credit + GUI guidebook)
    * นายพลบุริศน์ แน่งน้อย (ทำหน้า Register + show hidden password)
    * นายพลภัทร์ สร้อยเสริมทรัพย์ (ทำหน้า login)

* ครั้งที่ 2 (8 กันยายน 2565)
    * นายณพณภัทร นรศรี (welcome page+vote page + detail report)
    * นายปริภัทร์ มะลีแก้ว (แก้ไข Register+login ให้ใช้งานได้)
    * นายพลบุริศน์ แน่งน้อย (ทำหน้า Admin+ เปลี่ยนรหัสผ่าน + Report ban user)
    * นายพลภัทร์ สร้อยเสริมทรัพย์ (ทำหน้าwelcome_staffแสดงlistviewและmy_account)

* ครั้งที่ 3 (30 กันยายน 2565)
    * นายณพณภัทร นรศรี (welcome page + report + complaint)
    * นายปริภัทร์ มะลีแก้ว (Change password + login + register)
    * นายพลบุริศน์ แน่งน้อย (manage report + thememode + css darkmode)
    * นายพลภัทร์ สร้อยเสริมทรัพย์ (staff home page + show list view)
