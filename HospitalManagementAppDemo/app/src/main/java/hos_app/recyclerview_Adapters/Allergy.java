package hos_app.recyclerview_Adapters;


public class Allergy {
    private String date;
    private String allergy;
    private String practitioner;

    public Allergy() {
    }

    public Allergy(String date, String allergy, String practitioner) {
        this.date = date;
        this.allergy = allergy;
        this.practitioner = practitioner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getPractitioner() {
        return practitioner;
    }

    public void setPractitioner(String practitioner) {
        this.practitioner = practitioner;
    }
}

