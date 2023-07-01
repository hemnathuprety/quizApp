package np.com.hemnath.quizapp.quiz.data.local.token.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import np.com.hemnath.quizapp.quiz.data.domain.model.ApiToken;

@Entity(tableName = "token")
public class TokenEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String token;

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

    public ApiToken toTokenModel() {
        ApiToken tokenModel = new ApiToken();
        tokenModel.setToken(token);
        return tokenModel;
    }
}
