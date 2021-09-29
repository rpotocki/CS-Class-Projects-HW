package application;

public class Choice
{
  private String choiceText;
  private boolean isCorrectAnswer;
  
  public Choice() {
    
  }
  public Choice(String choiceText, boolean isCorrectAnswer)
  {
    this.choiceText=choiceText;
    this.isCorrectAnswer=isCorrectAnswer;
  }
  
  public String getText()
  {
    return choiceText;
  }
  
  public boolean isCorrectAnswer()
  {
    return isCorrectAnswer;
  }
  
  public void setCorrect(Boolean answer) {
    this.isCorrectAnswer = answer;
  }
  
  public void setText(String text) {
    this.choiceText = text;
  }
}