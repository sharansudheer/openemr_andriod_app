package com.apicontroller;
import com.google.gson.annotations.SerializedName;
import java.util.List;
public class AppointmentResponse {

        @SerializedName("validationErrors")
        private List<String> validationErrors;

        public List<String> getValidationErrors() {
            return validationErrors;
        }

        public void setValidationErrors(List<String> validationErrors) {
            this.validationErrors = validationErrors;
        }

        public List<String> getErrorDescription() {
            return errorDescription;
        }

        public void setErrorDescription(List<String> errorDescription) {
            this.errorDescription = errorDescription;
        }


        @SerializedName("error_description")
        private List<String> errorDescription;
    @SerializedName("pc_eventDate")
    private String pcEventDate;
    public String getPcEventDate() {
        return pcEventDate;
    }

    public void setPcEventDate(String pcEventDate) {
        this.pcEventDate = pcEventDate;
    }


}
