package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Provides implementation for the home screen of the quiz generator
 * 
 * @author max
 *
 */
public class Main extends Application {
  Image image;
  ArrayList<Question> questionList;
  ArrayList<Topic> topicList;
  ArrayList<Question> quizList;
  ArrayList<String> topicNames;
  int numQuestions = 0;
  Label questionNum = new Label();
  boolean x = false;
  double totQ = 0;
  double numCorrect = 0;
  private String saveFilename = "";

  @Override
  public void start(Stage primaryStage) {
    if (questionList == null) {
      questionList = new ArrayList<Question>();
      topicList = new ArrayList<Topic>();
      topicNames = new ArrayList<String>();
      quizList = new ArrayList<Question>();
    }
    try {
      // Test Questions

      BorderPane root = new BorderPane();
      VBox vBox = new VBox(40);
      FileChooser file = new FileChooser();
      Alert error = new Alert(AlertType.ERROR);
      error.setContentText("File was not of type .json");

      // label and xBox settings
      Label appName = new Label("Quiz Generator");
      appName.setFont(Font.font("Calabria", 32));
      vBox.setAlignment(Pos.CENTER);
      vBox.getChildren().add(appName);

      // create buttons
      Button createQ = new Button("Create Question");
      createQ.getStyleClass().add("create-question-button");
      Button loadJSON = new Button("Load JSON");
      loadJSON.getStyleClass().add("create-question-button");
      Button saveJSON = new Button("Save JSON");
      saveJSON.getStyleClass().add("create-question-button");
      Button createQuiz = new Button("Create Quiz");
      createQuiz.getStyleClass().add("quiz-button");

      // add buttons to window
      vBox.getChildren().add(createQ);
      vBox.getChildren().add(loadJSON);
      vBox.getChildren().add(saveJSON);
      vBox.getChildren().add(createQuiz);

      // question numbers
      questionNum.setText("Total Number of Questions: " + numQuestions);
      questionNum.setFont(Font.font("Calabria", 20));
      vBox.getChildren().add(questionNum);

      // open json file action
      loadJSON.setOnAction(e -> {
        File jsonFile = file.showOpenDialog(primaryStage);
        if (jsonFile != null) {
          String filePath = jsonFile.getPath();

          String fileExt = "";
          int i = filePath.lastIndexOf('.');
          if (i > 0) {
            fileExt = filePath.substring(i + 1);
          }
          if (!fileExt.equalsIgnoreCase("json")) {
            error.show();
          }
            readJSON(filePath);
            generateTopicList();
            questionNum.setText("Total Number of Questions: " + numQuestions);
        }
      });

      saveJSON.setOnAction(e -> {
        VBox saveVBox = new VBox(20);
        saveVBox.setAlignment(Pos.CENTER);

        Label fileName = new Label("Enter a file name");
        Button saveButton = new Button("Save");
        TextField nameField = new TextField();

        fileName.setFont(Font.font("Calabria", 18));
        nameField.setMaxWidth(350);
        nameField.setAlignment(Pos.CENTER);

        saveVBox.getChildren().add(fileName);
        saveVBox.getChildren().add(nameField);
        saveVBox.getChildren().add(saveButton);

        Stage window = new Stage();
        Scene saveScene = new Scene(saveVBox, 400, 200);
        window.setScene(saveScene);
        window.setTitle("Save File Name");
        window.show();

        saveButton.setOnAction(b -> {
          saveFilename = nameField.getText();
          writeToJSON();
          window.close();
        });

      });

      // open a new create question window
      createQ.setOnAction(e -> {

        VBox questionBox = questionCreation();
        Scene questionScene = new Scene(questionBox, 800, 600);
        Stage qWindow = new Stage();
        qWindow.setScene(questionScene);
        qWindow.setTitle("Create Question");
        qWindow.show();
      });

      // quiz creation
      createQuiz.setOnAction(e -> {
        BorderPane quizConfiguration = quizConfigure();
        Scene quizScene = new Scene(quizConfiguration, 800, 450);
        Stage qWindow = new Stage();
        qWindow.setScene(quizScene);
        qWindow.setTitle("Quiz create");
        qWindow.show();
      });

      // create and show windows
      Scene scene = new Scene(vBox, 800, 550);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.setTitle("Quiz Generator");
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  public VBox questionCreation() {
    VBox theVBox = new VBox();
    // Question Text
    Label questionTextLabel = new Label("Question Text:");
    TextField questionTextField = new TextField();
    theVBox.getChildren().addAll(questionTextLabel, questionTextField, new Label("\n\n"));
    // Question Topic
    HBox topicAndPictureHBox = new HBox();
    Label topicLabel = new Label("Question Topic:");
    TextField topicTextField = new TextField();

    // Image
    VBox filePathVBox = new VBox();
    HBox pictureHBox = new HBox();
    Label imageFilePathLabel = new Label("File path for the image:");
    TextField imageFilePathTextField = new TextField();
    Button loadImageButton = new Button("Load");
    try {
      image = new Image(new FileInputStream("HomeScreen_JSON/src/images/noFile.png"));
    } catch (FileNotFoundException e3) {
      e3.printStackTrace();
    }
    HBox filePathAndButton = new HBox(imageFilePathTextField, loadImageButton);
    filePathVBox.getChildren().addAll(imageFilePathLabel, filePathAndButton);
    Region spacerRegion = new Region();
    HBox.setHgrow(spacerRegion, Priority.ALWAYS);
    pictureHBox.getChildren().addAll(filePathVBox, spacerRegion, new ImageView(image));
    // Add stuff to VBox
    topicAndPictureHBox.getChildren().addAll(new VBox(topicLabel, topicTextField),
        new Label("\t\t\t\t\t"), pictureHBox);
    theVBox.getChildren().add(topicAndPictureHBox);

    // Press Enter
    imageFilePathTextField.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.ENTER) {
        pictureHBox.getChildren().remove(2);
        try {
          image = new Image(new FileInputStream(imageFilePathTextField.getText()));
        } catch (FileNotFoundException e1) {
          try {
            image = new Image(new FileInputStream("src/images/fileNotFound.png"));
          } catch (FileNotFoundException e2) {
            e2.printStackTrace();
          }
        }
        pictureHBox.getChildren().add(new ImageView(image));
      }
    });
    // Click "load" button
    loadImageButton.setOnAction(e -> {
      pictureHBox.getChildren().remove(2);
      try {
        image = new Image(new FileInputStream(imageFilePathTextField.getText()));
      } catch (FileNotFoundException e1) {
        try {
          image = new Image(new FileInputStream("src/images/fileNotFound.png"));
        } catch (FileNotFoundException e2) {
          e2.printStackTrace();
        }
      }
      pictureHBox.getChildren().add(new ImageView(image));
    });
    // Answers
    Label answerLabel = new Label("Answer Text:\t\t\t\t\t\t\t\t");
    Label checkBoxLabel = new Label("Correct Answer?");
    HBox labels = new HBox(answerLabel, checkBoxLabel);

    // Text fields for questions
    ArrayList<TextField> textFields = new ArrayList<TextField>();
    TextField q1Text = new TextField();
    TextField q2Text = new TextField();
    TextField q3Text = new TextField();
    TextField q4Text = new TextField();
    TextField q5Text = new TextField();
    textFields.add(q1Text);
    textFields.add(q2Text);
    textFields.add(q3Text);
    textFields.add(q4Text);
    textFields.add(q5Text);
    q1Text.setPrefWidth(300);
    q2Text.setPrefWidth(300);
    q3Text.setPrefWidth(300);
    q4Text.setPrefWidth(300);
    q5Text.setPrefWidth(300);

    // Check Boxes
    ArrayList<RadioButton> radioButtons = new ArrayList<RadioButton>();
    RadioButton q1Box = new RadioButton();
    RadioButton q2Box = new RadioButton();
    RadioButton q3Box = new RadioButton();
    RadioButton q4Box = new RadioButton();
    RadioButton q5Box = new RadioButton();
    radioButtons.add(q1Box);
    radioButtons.add(q2Box);
    radioButtons.add(q3Box);
    radioButtons.add(q4Box);
    radioButtons.add(q5Box);
    // Only one at a time can be selected
    ToggleGroup question1 = new ToggleGroup();

    q1Box.setToggleGroup(question1);
    q2Box.setToggleGroup(question1);
    q3Box.setToggleGroup(question1);
    q4Box.setToggleGroup(question1);
    q5Box.setToggleGroup(question1);

    VBox answersVBox = new VBox(new HBox(q1Text, q1Box), new HBox(q2Text, q2Box),
        new HBox(q3Text, q3Box), new HBox(q4Text, q4Box), new HBox(q5Text, q5Box));
    theVBox.getChildren().addAll(new Label("\n\n\n\n\n\n\n\n"), labels, answersVBox);

    // Back and Finish Buttons
    Button backButton = new Button("Back");
    Button saveButton = new Button("Save Question");
    backButton.setOnAction(e -> {
      Stage stage = (Stage) backButton.getScene().getWindow();
      stage.close();
      // Close window (returns to main menu)
    });
    saveButton.setOnAction(e -> {
      ArrayList<Choice> choiceList = new ArrayList<Choice>();
      for (int i = 0; i < 5; i++) {
        choiceList.add(new Choice(textFields.get(i).getText(), radioButtons.get(i).isSelected()));
      }
      Question newQuestion = new Question(questionTextField.getText(), topicTextField.getText(),
          imageFilePathTextField.getText(), choiceList);
      boolean createNewTopic = true;
      for (int i = 0; i < topicList.size(); i++) {
        if (topicList.get(i).getTopicName().equals(topicTextField.getText())) {
          topicList.get(i).addQuestion(newQuestion);
          numQuestions++;
          createNewTopic = false;
          break;
        }
      }
      if (createNewTopic) {
        Topic newTopic = new Topic(topicTextField.getText());
        newTopic.addQuestion(newQuestion);
        numQuestions++;
        questionNum.setText("Total Number of Questions: " + numQuestions);
        topicList.add(newTopic);
      }
      Stage stage = (Stage) saveButton.getScene().getWindow();
      stage.close();
      // Return to main menu by closing current window
    });
    Region reg1 = new Region();
    HBox.setHgrow(reg1, Priority.ALWAYS);
    HBox buttons = new HBox(backButton, reg1, saveButton);
    theVBox.getChildren().addAll(new Label("\n\n\n\n"), buttons);
    // Setup scene stuff
    return theVBox;
  }

  public VBox quizDisplay() {
    try {
      if (!x) {
        totQ = quizList.size();
      }
      if (quizList.size() != 0) {

        // Check and Home buttons
        Button Check = new Button("Check");
        Button Home = new Button("Home");
        Check.setMinSize(150, 75);
        Check.setFont(new Font("Arial", 28));
        Home.setMinSize(150, 75);
        Home.setFont(new Font("Arial", 28));

        // Multiple choice answer buttons

        ArrayList<Choice> AChoices = new ArrayList<Choice>();
        ArrayList<RadioButton> Buttons = new ArrayList<RadioButton>();
        ToggleGroup answers = new ToggleGroup();
        int correctInd = 0;
        Question curQ = quizList.remove(0);
        AChoices = curQ.GetChoiceArray();
        for (Choice c : AChoices) {
          if (c.isCorrectAnswer()) {
            correctInd = AChoices.indexOf(c);
          }
          Buttons.add(new RadioButton(c.getText()));
        }
        Check.setDisable(true);
        for (RadioButton b : Buttons) {
          b.setFont(new Font("Arial", 20));
          b.setToggleGroup(answers);
          b.setOnAction(e -> Check.setDisable(false));
        }
        RadioButton correct = Buttons.get(correctInd);
        // Check Functionality

        // LAYOUT OF QUIZ PAGE

        VBox layout = new VBox();

        for (RadioButton x : Buttons) {
          layout.getChildren().addAll(x);
        }
        Check.setOnAction(e -> {
          Alert YN = new Alert(AlertType.INFORMATION);
          if (correct.isSelected()) {
            YN.setTitle("Result");
            YN.setContentText("Correct");
            YN.showAndWait();
            numCorrect++;
          } else {
            YN.setTitle("Result");
            YN.setContentText("Incorrect");
            YN.showAndWait();
          }
          Stage stage = (Stage) Check.getScene().getWindow();
          stage.close();
          VBox quizBox = quizDisplay();
          Scene quizViewScene = new Scene(quizBox, 800, 600);
          Stage qWindow = new Stage();
          qWindow.setScene(quizViewScene);
          qWindow.setTitle("Create Question");
          qWindow.show();
        });

        Home.setOnAction(e -> {
          start((Stage) Home.getScene().getWindow());
        });
        Label qPage = new Label(curQ.GetQuestionText());
        qPage.setFont(new Font("Arial", 32));
        qPage.setWrapText(true);
        BorderPane root = new BorderPane();

        // Image
        Image pic = null;
        ImageView imageView = null;
        boolean image = false;
        if (curQ.GetImageFilePath() != null) {
          if (curQ.GetImageFilePath().equals("")) {
            // Image
            pic = new Image(new FileInputStream(curQ.GetImageFilePath()));
            imageView = new ImageView(pic);
            image = true;
          }
        }

        VBox v = new VBox();
        v.setMinHeight(600);
        v.setMinWidth(800);
        root.setMinHeight(600);
        root.setMinWidth(800);
        BorderPane footerFormat = new BorderPane();
        BorderPane headerFormat = new BorderPane();
        BorderPane ImageCenter = new BorderPane();
        v.getChildren().add(root);
        root.setTop(headerFormat);
        root.setLeft(layout);
        root.setBottom(footerFormat);
        footerFormat.setLeft(Home);
        footerFormat.setRight(Check);
        if (!image) {
          headerFormat.setMinHeight(200);
        }
        headerFormat.setCenter(qPage);
        headerFormat.setTop(ImageCenter);
        if (image) {
          ImageCenter.setCenter(imageView);
        }
        layout.setSpacing(10.0);
        return v;
      } else {
        VBox vf = new VBox();
        Button Home = new Button("Home");
        Home.setOnAction(e -> {
          start((Stage) Home.getScene().getWindow());
        });
        BorderPane Format = new BorderPane();
        vf.setMinHeight(600);
        vf.setMinWidth(800);
        Format.setMinHeight(600);
        Format.setMinWidth(800);
        Double dec = (double) (numCorrect / totQ);
        int per = (int) (dec * 100);
        String str = "Number Correct: " + numCorrect + "\nNumber of Questions: " + totQ
            + "\nPercentage: " + per + "%";
        Label results = new Label("RESULTS");
        Label output = new Label(str);
        BorderPane header = new BorderPane();
        results.setFont(new Font("Arial", 100));
        output.setFont(new Font("Arial", 20));
        Format.setTop(header);
        Format.setCenter(output);
        header.setCenter(results);
        Format.setBottom(Home);
        vf.getChildren().add(Format);
        return vf;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new VBox();
  }

  /**
   * Method to generate a scene with necessary components for the quiz configuration It will have a
   * text field for user to enter the numer of questions for the quiz and a list of topic with
   * checkbox for user to choose topic for the quiz.
   * 
   * @return a scene that contains GUI components for quiz congifuration
   */
  public BorderPane quizConfigure() {
    // the Title on the top
    Label titleLabel = new Label("Quiz Configuration");
    titleLabel.setFont(Font.font("Ariel", 30));

    // the 3 button on the bottom, back on the left, reset and confirm on the right
    Button backButton = new Button("Back"), clearButton = new Button("Reset"),
        confirmButton = new Button("Confirm");
    Region bottomRowPadding = new Region();
    HBox buttonRow = new HBox();
    buttonRow.getChildren().addAll(backButton, bottomRowPadding, clearButton, confirmButton);
    HBox.setHgrow(bottomRowPadding, Priority.ALWAYS);
    buttonRow.setSpacing(10);

    // label and textfield for entering number of questions
    Label quesNumLabel = new Label("Please enter the number of question.");
    quesNumLabel.setFont(Font.font("Ariel", 15));
    TextField numOfQuestion = new TextField();
    Label numOQueWarning = new Label(
        "Proceeding without entering the number of questions will be consider as using all the "
            + "question from the chosen topics to the quiz");
    numOQueWarning.setWrapText(true);
    numOQueWarning.setMaxWidth(250);
    // Limiting input to only integer
    UnaryOperator<Change> filter = change -> {
      String text = change.getText();
      if (text.matches("[0-9]*")) {
        return change;
      }
      return null;
    };
    TextFormatter<String> textFormatter = new TextFormatter<>(filter);
    numOfQuestion.setTextFormatter(textFormatter);
    // above codes come from https://stackoverflow.com/a/36436243
    VBox numQuestionDisplay = new VBox();
    numQuestionDisplay.getChildren().addAll(quesNumLabel, numOfQuestion, numOQueWarning);

    // label and Vbox used to display all the topic
    Label topicLabel = new Label("Select one or multiple topics for quiz's questions.");
    topicLabel.setFont(Font.font("Ariel", 20));
    VBox topicListDisplay = new VBox();
    VBox topicDisplay = new VBox();
    topicDisplay.getChildren().addAll(topicLabel, new Label(" "), topicListDisplay);

    // a loop that add actual topic from topicList
    ArrayList<String> sortedTopicList = new ArrayList<String>();
    topicList.stream().forEach(e -> sortedTopicList.add(e.getTopicName()));
    Collections.sort(sortedTopicList);
    for (String topic : sortedTopicList) {
      if (topic != null) {
        topicListDisplay.getChildren().add(new HBox(new CheckBox(), new Label(topic)));
      }
    }

    // Close window (returns to main menu)
    backButton.setOnAction(e -> {
      Stage stage = (Stage) backButton.getScene().getWindow();
      stage.close();
    });
    // clear user input by reset checkbox and textfield
    clearButton.setOnAction(e -> {
      numOfQuestion.clear();
      topicListDisplay.getChildren().clear();
      for (String topic : sortedTopicList) {
        if (topic != null) {
          topicListDisplay.getChildren().add(new HBox(new CheckBox(), new Label(topic)));
        }
      }
    });


    // set up borderpane and scene
    BorderPane root = new BorderPane();
    // Scene scene = new Scene(root, 800, 450);
    root.setTop(titleLabel);
    BorderPane.setAlignment(titleLabel, Pos.CENTER);
    root.setLeft(topicDisplay);
    root.setBottom(buttonRow);
    root.setRight(numQuestionDisplay);

    // Set confirm button to lead to quiz screen
    confirmButton.setOnAction(e -> {
      System.out.println("Printing quizGenerate list content");
      quizGenerate(root).forEach(q -> System.out.println(q.GetQuestionText()));
      quizList = quizGenerate(root);
      totQ = quizList.size();
      VBox quizBox = quizDisplay();
      Scene quizViewScene = new Scene(quizBox, 800, 600);
      Stage qWindow = new Stage();
      qWindow.setScene(quizViewScene);
      qWindow.setTitle("Quiz display");
      qWindow.show();
    });

    return root;
  }

  /**
   * This method takes the information from the quiz generation page and generate a list of question
   * for the quiz accordingly. More specifically, it add all the question that match the selected
   * topic(s) and randomly picking given amount of question. If asked for more than question
   * available then return all the available question.
   * 
   * @param quizGeneratePage BorderPane that contains quiz generation page's GUI components
   * @return list of questions that will be used for the quiz
   */
  public ArrayList<Question> quizGenerate(BorderPane quizGeneratePage) {
    Random rand = new Random();
    ArrayList<Question> quizQuestion = new ArrayList<Question>(),
        topicQuestion = new ArrayList<Question>();
    try {
      // find out the selected topic and put all the associated questions into
      // topicQuestion
      // to get the list of HBox containing checkBox and topic name combo, we do this:
      // root(quizGeneratePage) -> topicDisplay(.getLeft) ->
      // topicListDisplay(.getChildren.get(2))
      // ->
      // HBox that contain checkBox and topic name
      List<Node> topicHBoxList =
          ((VBox) ((VBox) quizGeneratePage.getLeft()).getChildren().get(2)).getChildren();
      for (int i = 0; i < topicHBoxList.size(); i++) {
        if (((CheckBox) ((HBox) topicHBoxList.get(i)).getChildren().get(0)).isSelected()) {
          topicQuestion.addAll(
              getTopicByName(((Label) ((HBox) topicHBoxList.get(i)).getChildren().get(1)).getText())
                  .getQuestionList());
        }
      }
      System.out.println("After checking topicQuestion have:");
      topicQuestion.stream().forEach(q -> System.out.println(q.GetQuestionText()));
      
      // getting the number of question from textField user input and use random
      // number generator
      // to generate the list of question for the quiz
      long numOfQuestion = -666; // default to negative since textfield value can't go below zero
      if (!((TextField) ((VBox) quizGeneratePage.getRight()).getChildren().get(1)).getText()
          .equals("")) {
        // if textField doesn't have anything, skip the parsing
        numOfQuestion = Long.parseLong(
            ((TextField) ((VBox) quizGeneratePage.getRight()).getChildren().get(1)).getText());
      }
      if (numOfQuestion >= topicQuestion.size() || numOfQuestion == -666) {
        System.out.println("User did not enter num of Question");
        // if asked for more than what we have or nothing, just give them all
        quizQuestion = new ArrayList<Question>(topicQuestion);
        
      } else {
        // randomly picking question
        System.out.println("Randomly picking question");
        for (long i = 0; i < numOfQuestion; i++) {
          quizQuestion.add(topicQuestion.get(rand.nextInt(topicQuestion.size())));
        }
      }
    } catch (NumberFormatException e) {
      // just in case some fucker put in something larger than long
      e.printStackTrace();
    }
    System.out.println("quizQuestion list size: "+quizQuestion.size());
    return quizQuestion;
  }

  /**
   * Private helper method to get the corresponding Topic object using String
   * @param name name of topic
   * @return Topic object with that specific name
   */
  private Topic getTopicByName(String name) {
    List<Topic> result = topicList.stream().filter(topic -> topic.getTopicName().equals(name))
        .collect(Collectors.toList());
    System.out.println("getTopicByName result "+ result.get(0).getTopicName());
    return result.get(0);
  }


  public void readJSON(String jsonFilepath) {
    ArrayList<Question> tempQuestionList = new ArrayList<Question>();
    JSONParser parser = new JSONParser();
    try { // reads in the json file
      FileReader file = new FileReader(jsonFilepath);
      Object obj = parser.parse(file);
      JSONObject jObject = (JSONObject) obj;
      JSONArray question = (JSONArray) jObject.get("questionArray");

      // loop through all questions
      for (int i = 0; i < question.size(); i++) {
        Question newQuestion = new Question();
        JSONObject jObject1 = (JSONObject) question.get(i);
        String questionText = (String) jObject1.get("questionText");
        newQuestion.SetQuestionText(questionText);
        String questionTopic = (String) jObject1.get("topic");
        newQuestion.SetTopic(questionTopic);
        String questionImage = (String) jObject1.get("image");
        newQuestion.SetImageFilePath(questionImage);
        ArrayList<Choice> choices = new ArrayList<Choice>();
        JSONArray jsonChoices = (JSONArray) jObject1.get("choiceArray");

        // loop through all choices
        for (int j = 0; j < jsonChoices.size(); j++) {
          Choice newChoice = new Choice();
          JSONObject currentChoice = (JSONObject) jsonChoices.get(j);
          String choiceText = (String) currentChoice.get("choice");
          newChoice.setText(choiceText);
          String isCorrect = (String) currentChoice.get("isCorrect");
          if (isCorrect.equals("T")) {
            newChoice.setCorrect(true);
          } else {
            newChoice.setCorrect(false);
          }
          choices.add(newChoice);
        }

        // add questions to a temporary list
        newQuestion.SetChoiceList(choices);
        tempQuestionList.add(newQuestion);
      }

    } catch (FileNotFoundException e) {

    } catch (Exception e) {

    }
    for (int i = 0; i < tempQuestionList.size(); i++) {
      questionList.add(tempQuestionList.get(i));
      numQuestions++;
    }

  }

  public void generateTopicList() {
    for (int i = 0; i < questionList.size(); i++) {
      if (topicNames.contains(questionList.get(i).GetTopic())) { //if it's in topic list
        for (int j = 0; j < topicList.size(); j++) {
          if ((topicList.get(j).getTopicName().equals(questionList.get(i).GetTopic())) && !(topicList.get(j).getQuestionList().contains(questionList.get(i)))) {
            topicList.get(j).addQuestion(questionList.get(i));
            break;
          }
        }
      }
      else {
        topicNames.add(questionList.get(i).GetTopic());
        Topic newTopic = new Topic(questionList.get(i).GetTopic());
        newTopic.addQuestion(questionList.get(i));
        topicList.add(newTopic);
      }
    }
  }

  public void writeToJSON() {
    JSONObject questions = new JSONObject();
    JSONArray questionArray = new JSONArray();
    try{
      for (int i = 0; i < questionList.size(); i++) {
        JSONObject question = new JSONObject();
        question.put("meta-data", "");
        question.put("questionText", questionList.get(i).GetQuestionText() );
        question.put("topic", questionList.get(i).GetTopic());
        question.put("image", questionList.get(i).GetImageFilePath());
        JSONArray choiceArray = new JSONArray();
        ArrayList<Choice> questionChoices = new ArrayList<Choice>();
        questionChoices = questionList.get(i).GetChoiceArray();
        for (int j = 0; j < questionChoices.size(); j++) {
          JSONObject choice = new JSONObject();
          if (questionChoices.get(j).isCorrectAnswer()) {
            choice.put("isCorrect", "T");
            choice.put("choice", questionChoices.get(j).getText());
          }
          else {
            choice.put("isCorrect", "F");
            choice.put("choice", questionChoices.get(j).getText());
          }
          choiceArray.add(choice);
        }
        question.put("choiceArray", choiceArray);
        questionArray.add(question);
      }
      questions.put("questionArray", questionArray);
      // write to file
      FileWriter JSONfile = new FileWriter(saveFilename +".json");
      JSONfile.write(questions.toJSONString());
      JSONfile.flush();
      JSONfile.close();
    } catch (IOException e) {

    }
  }
}
