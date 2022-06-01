package vo;

/*
 report_idx(PK)
member_id, shop_id
(member_id = 구매자 
= 신고한 사람 
shop_idx = 판매자
 = 신고당한 사람)
report_tile
report_content
report_date


CREATE TABLE report (
	report_idx varchar(20) primary key,
	member_id varchar(20),
	report_type varchar(20),
	report_subject varchar(50),
	report_content varchar(500),
	report_date varchar(8),
);
 */


public class AdminReportDTO {

	
	private String report_idx;
	private String member_id;
	private String report_type;
	private String report_subject;
	private String report_content;
	private String report_date;
	public String getReport_idx() {
		return report_idx;
	}
	public void setReport_idx(String report_idx) {
		this.report_idx = report_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getReport_type() {
		return report_type;
	}
	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}
	public String getReport_subject() {
		return report_subject;
	}
	public void setReport_subject(String report_subject) {
		this.report_subject = report_subject;
	}
	public String getReport_content() {
		return report_content;
	}
	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}
	public String getReport_date() {
		return report_date;
	}
	public void setReport_date(String report_date) {
		this.report_date = report_date;
	}
	@Override
	public String toString() {
		return "AdminReportDTO [report_idx=" + report_idx + ", member_id=" + member_id + ", report_type=" + report_type
				+ ", report_subject=" + report_subject + ", report_content=" + report_content + ", report_date="
				+ report_date + "]";
	}

	
	
}
