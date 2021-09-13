package model;

public class Section {
	private int section_id;
	private int cv_id;
	private String title;
	private String content;
	private int position;
	
	public Section(int section_id, int cv_id, String title, String content, int position) {
		super();
		this.section_id = section_id;
		this.cv_id = cv_id;
		this.title = title;
		this.content = content;
		this.position = position;
	}
	
	public Section(int cv_id, String title, String content, int position) {
		super();
		this.cv_id = cv_id;
		this.title = title;
		this.content = content;
		this.position = position;
	}
	
	public int getSection_id() {
		return section_id;
	}
	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}
	public int getCv_id() {
		return cv_id;
	}
	public void setCv_id(int cv_id) {
		this.cv_id = cv_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	
}
