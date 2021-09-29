package application;

import java.util.ArrayList;

public class Question
{
  private String questionText;
  private String topic;
  private String imageFilePath;
  private ArrayList<Choice> choiceList;

  /**
   * 
   * @param qText
   * @param topic
   * @param image
   * @param choices
   */
  
  public Question() {
    
  }
  
  public Question(String qText, String topic, String image, ArrayList<Choice> choices)
  {
    this.questionText = qText;
    this.topic = topic;
    this.imageFilePath = image;
    this.choiceList = choices;
  }

  public String GetQuestionText()
  {
    return questionText;
  }

  public String GetTopic()
  {
    return topic;
  }

  public String GetImageFilePath()
  {
    return imageFilePath;
  }

  public ArrayList<Choice> GetChoiceArray()
  {
    return choiceList;
  }
  
  public void SetQuestionText(String questionText) {
    this.questionText = questionText;
  }
  
  public void SetTopic(String topic) {
    this.topic = topic;
  }
  
  public void SetImageFilePath(String imgFilePath) {
    this.imageFilePath = imgFilePath;
  }
  
  public void SetChoiceList(ArrayList<Choice> choices) {
    this.choiceList = choices;
  }
  
}