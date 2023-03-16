package com.apicontroller;
import com.google.gson.annotations.SerializedName;
public class AllergyResponse {
        @SerializedName("title")
        private String title;
        @SerializedName("begdate")
        private String begdate;
        @SerializedName("enddate")
        private String enddate;


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBegdate() {
            return begdate;
        }

        public void setBegdate(String begdate) {
            this.begdate = begdate;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

}
