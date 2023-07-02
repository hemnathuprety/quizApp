package np.com.hemnath.quizapp.quiz.data.local.token.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import np.com.hemnath.quizapp.quiz.data.domain.model.ApiToken;
import np.com.hemnath.quizapp.utils.DateConverters;

@Entity(tableName = "token")
@TypeConverters(DateConverters.class)
public class TokenEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String token;

    @ColumnInfo(name = "date_column")
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ApiToken toTokenModel() {
        ApiToken tokenModel = new ApiToken();
        tokenModel.setToken(token);
        tokenModel.setDate(date);
        return tokenModel;
    }
}
