package application;

import java.util.ArrayList;

public class Topic
{
  private String topicName;
  private ArrayList<Question> questionList;

  public Topic(String name)
  {
    topicName = name;
    questionList = new ArrayList<Question>();
  }

  public String getTopicName()
  {
    return topicName;
  }

  public void addQuestion(Question q)
  {
    questionList.add(q);
  }

  public ArrayList<Question> getQuestionList()
  {
    return questionList;
  }
  
  public int getNumQuestions()
  {
    return questionList.size();
  }
}
