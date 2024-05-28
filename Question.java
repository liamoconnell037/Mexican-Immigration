public class Question {
    private String question;
    private String[] answers;
    private char rightAnswer; // index of answers
    private QuestionType type;


    public Question(String question, String[] answers, char rightAnswer, QuestionType type) {
        this.question = question;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
        this.type = type;
    }
    public String getQuestion() {return question;}
    public String[] getAnswers() {return answers;}
    public char getRightAnswer() {return rightAnswer;}
    public QuestionType getType() {return type;}

    public enum QuestionType {
        History,
        Spanish,
        Government
    }
}
