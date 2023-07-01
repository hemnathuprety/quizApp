package np.com.hemnath.quizapp.quiz.data.local.token;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import np.com.hemnath.quizapp.quiz.data.local.token.entity.TokenEntity;

@Dao
public interface TokenDeo {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertToken(TokenEntity token);

    @Query("SELECT * FROM token")
    LiveData<List<TokenEntity>> getAllToken();

    @Query("DELETE FROM token")
    void nukeTable();
}
