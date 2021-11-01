package CodeSnippetsAccumulator;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.util.Duration;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/* Copyright 2021 Melwyn Francis Carlo */

public class View extends Application
{
    private static Stage   primaryStage;
    private static Stage secondaryStage;

    @Override
    public void start(Stage inputPrimaryStage) throws Exception
    {
        primaryStage = inputPrimaryStage;

        final Parent rootNode = FXMLLoader.load(getClass().getResource("ui" + Model.FILE_SEPARATOR + "SplashScreen.fxml"));

        /* Prepare the window, and display the splash screen. */

        final Scene primaryScene = new Scene(rootNode);

        primaryStage.getIcons().add(new Image("CodeSnippetsAccumulator/ui/res/img/icon.png"));
        primaryStage.setTitle("Code Snippets Accumulator");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(primaryScene);
        primaryStage.setResizable(false);
        primaryStage.show();

        launchSearchWindow();
    }

    @Override
    public void stop()
    {
        System.exit(0);
    }

    public static void launchSplashScreen(String args[])
    {
        Application.launch(args);
    }

    public static void launchSearchWindow()
    {
        Controller.windowNumber = 1;

        double waitTimeInSeconds;

        PauseTransition waitTimedTask = new PauseTransition(Duration.seconds(Controller.SPLASH_SCREEN_TIME_IN_SECONDS));

        waitTimedTask.setOnFinished(event -> 
        {
            try
            {
                final Parent rootNode = FXMLLoader.load(View.class.getResource("ui" + Model.FILE_SEPARATOR + "SearchWindow.fxml"));

                Stage newPrimaryStage = new Stage();

                newPrimaryStage.setTitle("Code Snippets Accumulator");
                newPrimaryStage.setScene(primaryStage.getScene());
                newPrimaryStage.initStyle(StageStyle.DECORATED);
                newPrimaryStage.getScene().setRoot(rootNode);
                newPrimaryStage.setResizable(false);
                newPrimaryStage.setMaximized(true);
                newPrimaryStage.show();

                primaryStage.hide();

                primaryStage = newPrimaryStage;
            }
            catch (Exception exception_object)
            {
                System.out.println(exception_object.getMessage());
                System.exit(1);
            }
        });

        waitTimedTask.play();
    }

    public static void launchEditWindow(int codeSnippetId, String codeSnippetLanguage)
    {
        Controller.windowNumber = 2;

        PauseTransition waitTimedTask = new PauseTransition(Duration.seconds(Controller.NORMAL_WAIT_TIME_IN_SECONDS));

        waitTimedTask.setOnFinished(event -> 
        {
            try
            {
                Controller.setEditWindowData(codeSnippetId, codeSnippetLanguage);

                final Parent rootNode = FXMLLoader.load(View.class.getResource("ui" + Model.FILE_SEPARATOR + "EditWindow.fxml"));

                secondaryStage = new Stage();

                final Scene secondaryScene = new Scene(rootNode);

                secondaryStage.setTitle("Add/Edit Code Snippet");
                secondaryStage.initModality(Modality.APPLICATION_MODAL);
                secondaryStage.initStyle(StageStyle.UNDECORATED);
                secondaryStage.setScene(secondaryScene);
                secondaryStage.setResizable(false);
                secondaryStage.setMaximized(false);
                secondaryStage.show();
            }
            catch (Exception exception_object)
            {
                System.out.println(exception_object.getMessage());
                System.exit(1);
            }
        });

        waitTimedTask.play();
    }

    public static void closeEditWindow()
    {
        Controller.windowNumber = 1;

        secondaryStage.hide();

        secondaryStage = null;

        System.gc();
    }

    public static void showNotificationAlert(String notificationMessage)
    {
        Alert notificationAlert = new Alert(AlertType.INFORMATION, "");
        notificationAlert.setTitle       ("Notification Alert");
        notificationAlert.setHeaderText  ("");
        notificationAlert.setContentText (notificationMessage);
        notificationAlert.showAndWait();
    }

    public static boolean showConfirmationAlert(String operationType)
    {
        boolean returnValue = false;

        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION, "", ButtonType.NO, ButtonType.YES);
        confirmationAlert.setTitle       ("Confirmation Alert");
        confirmationAlert.setHeaderText  ("");
        confirmationAlert.setContentText ("Are you sure you want to " + operationType + " this code?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES)
        {
            return true;
        }

        return returnValue;
    }

    public static void setMouseCursorToWait()
    {
        primaryStage.getScene().setCursor(Cursor.WAIT);
    }

    public static void setMouseCursorToDefault()
    {
        primaryStage.getScene().setCursor(Cursor.DEFAULT);
    }
}

