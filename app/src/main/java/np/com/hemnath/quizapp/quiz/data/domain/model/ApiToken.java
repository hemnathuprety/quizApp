package np.com.hemnath.quizapp.quiz.data.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import np.com.hemnath.quizapp.quiz.data.local.token.entity.TokenEntity;

public class ApiToken {
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("response_message")
    @Expose
    private String responseMessage;
    @SerializedName("token")
    @Expose
    private String token;

    private Date date;

    @SerializedName("response_code")
    public Integer getResponseCode() {
        return responseCode;
    }

    @SerializedName("response_code")
    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    @SerializedName("response_message")
    public String getResponseMessage() {
        return responseMessage;
    }

    @SerializedName("response_message")
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @SerializedName("token")
    public String getToken() {
        return token;
    }

    @SerializedName("token")
    public void setToken(String token) {
        this.token = token;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TokenEntity toTokenEntity() {
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(token);
        tokenEntity.setDate(new Date());
        return tokenEntity;
    }
}
