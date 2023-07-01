package np.com.hemnath.quizapp.quiz.data.local.quiz.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import np.com.hemnath.quizapp.quiz.data.domain.model.QuizModel;
import np.com.hemnath.quizapp.utils.Converters;

@Entity(tableName = "quizzes")
public class QuizEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correctAnswer;
    private String incorrectAnswers;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(String incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public QuizModel toQuizModel() {
        QuizModel quiz = new QuizModel();
        quiz.setCategory(category);
        quiz.setType(type);
        quiz.setDifficulty(difficulty);
        quiz.setQuestion(question);
        quiz.setCorrectAnswer(correctAnswer);
        quiz.setIncorrectAnswers(Converters.fromString(incorrectAnswers));
        return quiz;
    }
}
