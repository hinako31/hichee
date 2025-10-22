package model;

import java.io.Serializable;
import java.util.Date;

public class Diary implements Serializable {
	private int id;
    private int userid;
    private String name;         // 店の名前
    private String review;       // 感想
    private Integer period_year;            // 訪問年
    private Integer period_month;           // 訪問月
    private Integer area_id;    // 都道府県ID
    private String file_name;    // 画像ファイル名
    private String file_path;    // 画像パス
    private Date created_at;      // 登録日時
    private Date updated_at;      // 更新日時

    public Diary() {}

    
 // getter/setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Integer getPeriod_year() {
	    return period_year;
	}
	public void setPeriod_year(Integer period_year) {
	    this.period_year = period_year;
	}

	public Integer getPeriod_month() {
	    return period_month;
	}
	public void setPeriod_month(Integer period_month) {
	    this.period_month = period_month;
	}


	public Integer getArea_id() {
		return area_id;
	}

	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
    
}   
   