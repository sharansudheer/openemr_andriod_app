package com.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface RefreshTokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveRefreshToken(EntityToken refreshToken);

    @Query("SELECT * FROM refresh_token WHERE id = :id")
    EntityToken getRefreshToken(int id);

    @Query("DELETE FROM refresh_token")
    void deleteAll();
}
