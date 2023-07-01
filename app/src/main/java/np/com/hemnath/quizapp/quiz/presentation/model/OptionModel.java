package np.com.hemnath.quizapp.quiz.presentation.model;

public class OptionModel {
    String answers;
    Boolean selected;

    public OptionModel(String answers, Boolean selected) {
        this.answers = answers;
        this.selected = selected;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
