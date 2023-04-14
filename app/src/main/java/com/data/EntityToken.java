package com.data;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "refresh_token")

public class EntityToken {
    @PrimaryKey
    private int id;

    private String token;

    public EntityToken (int id, String token) {
        this.id = id;
        this.token = token;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
