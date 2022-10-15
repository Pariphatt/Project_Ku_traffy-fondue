package ku.cs.models.reports;

import ku.cs.models.account.StaffAccount;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class Report {
    private String topic;
    private String detail;
    private String userReport;
    private String status;
    private int vote;
    private String agency;
    private String solution;
    private String type;
    private String staffReport; //เปลี่ยนชื่อ
    private String reportTime;
    private String reasonsPost;

    public Report(String topic, String detail,String userReport,String type,String agency,String reportTime){
        this.topic = topic;
        this.detail = detail;
        this.userReport = userReport;
        this.type = type;
        this.status = "ยังไม่ดำเนินการ";
        this.vote = 0;
        this.solution = "";
        this.agency = agency;
        this.staffReport = null;
        this.reportTime = reportTime;
    }

    public String getReportTime() {
        return reportTime;
    }

    public String getAgency() {
        return agency;
    }

    public Report(String topic, String detail, String userReport, String type, int vote, String solution, String status,String agency,String staffReport,String reportTime){

        this.topic = topic;
        this.detail = detail;
        this.userReport = userReport;
        this.type = type;
        this.vote = vote;
        this.status = status;
        this.solution = solution;
        this.agency = agency;
        this.staffReport =staffReport;
        this.reportTime = reportTime;
    }


    public Report(String topic, String detail, int vote, String type) {
        this.topic = topic;
        this.detail = detail;
        this.type = type;
        this.vote = vote;
        this.status = "ยังไม่ดำเนินการ";
        this.solution = " ";
    }
    public Report(String topic, String type,String detail,String reasonsPost){
        this.topic = topic;
        this.detail = detail;
        this.type = type;
        this.reasonsPost = reasonsPost;
    }

    public String getReasonsPost() {
        return reasonsPost;
    }

    public void setReasonsPost(String reasonsPost) {
        this.reasonsPost = reasonsPost;
    }

    public String getSolution() {
        return solution;
    }

    public String getTopic() {
        return topic;
    }

    public String getDetail() {
        return detail;
    }

    public String getUserReport() {
        return userReport;
    }

    public String getStatus() {
        return status;
    }

    public int getVote() {return vote;}

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return topic + "("+vote+")"  +"    "+reportTime;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStaffReport() {
        return staffReport;
    }

    public void setStaffReport(String staffReport) {
        this.staffReport = staffReport;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
    public boolean goToManage(Report report, StaffAccount staff){
        if (report.getType().equals("ยานพาหนะ") && staff.getAgency().equals("กองยานพาหนะ")){
            return true;
        } else if (report.getType().equals("อาคารสถานที่และความปลอดภัย")&& staff.getAgency().equals("อาคารและสถานที่")) {
            return true;
        }
        else if (report.getType().equals("IT หรือ ปัญหาด้านคอมพิวเตอร์")&& staff.getAgency().equals("สำนักบริการคอมพิวเตอร์")) {
            return true;
        }
        else if (report.getType().equals("กิจกรรมนิสิต")&& staff.getAgency().equals("กองกิจกรรมนิสิต")) {
            return true;
        }
        else if (report.getType().equals("ทรัพย์สินในมหาวิทยาลัย")&& staff.getAgency().equals("สำนักงานทรัพย์สิน")) {
            return true;
        }
        else if (report.getType().equals("อื่นๆ")&& staff.getAgency().equals("สำนักงานอธิการบดี")) {
            return true;
        }
//                return report.getType().equals("IT หรือ ปัญหาด้านคอมพิวเตอร์");
        return false;
        }

    public void plusVote() {this.vote += 1;}
    public void minusVote(){this.vote -= 1;}

}

