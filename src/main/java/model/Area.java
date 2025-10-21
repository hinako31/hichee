package model;

import java.io.Serializable;


public class Area implements Serializable {

	    private int id;
	    private String area_name;
	    private int sort_order;

	    // コンストラクタ
	    public Area() {}

	    public Area(int id, String area_name) {
	        this.id = id;
	        this.area_name = area_name;
	    }
	    public Area(int id, String area_name,int sort_order) {
	        this.id = id;
	        this.area_name = area_name;
	        this.sort_order =sort_order;
	    }

	    // getter / setter
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getArea_name() {
	        return area_name;
	    }

	    public void setArea_name(String area_name) {
	        this.area_name = area_name;
	    }

		public int getSort_order() {
			return sort_order;
		}

		public void setSort_order(int sort_order) {
			this.sort_order = sort_order;
		}
	
}
