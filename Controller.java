package CodeSnippetsAccumulator;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import java.util.regex.Pattern;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.application.Platform;
import javafx.scene.text.FontWeight;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import javafx.scene.Node;
import javafx.scene.text.TextFlow;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import javafx.util.Duration;
import javafx.application.Platform;
import javafx.animation.PauseTransition;

/* Copyright 2021 Melwyn Francis Carlo */

public class Controller
{
    /* Public class variables */
        public static int windowNumber = 0;

        public static final double    SMALL_WAIT_TIME_IN_SECONDS = 0.05;
        public static final double   NORMAL_WAIT_TIME_IN_SECONDS = 0.25;
        public static final double SPLASH_SCREEN_TIME_IN_SECONDS = 2.00;


    /* Private class variables */
        private static boolean performSearchDataTableUpdate = false;


    /* Private FXML variables */
        @FXML
        private ComboBox<String> searchLanguageField;

        @FXML
        private CheckBox searchTitleField;

        @FXML
        private CheckBox searchNotesField;

        @FXML
        private CheckBox searchTagsField;

        @FXML
        private TextField searchTextField;

        @FXML
        private ComboBox<String> languageField;

        @FXML
        private TextField titleField;

        @FXML
        private TextArea codeField;

        @FXML
        private TextArea notesField;

        @FXML
        private TextField tagsField;

        @FXML
        private TableView<Model.SearchData> searchDataTable;

        @FXML
        private TableColumn<Model.SearchData, String> column1;

        @FXML
        private TableColumn<Model.SearchData, String> column2;

        @FXML
        private TableColumn<Model.SearchData, String> column3;


    /* Private class functions */
        private void forceFocus(Node node)
        {
            PauseTransition waitTimedTask = new PauseTransition(Duration.seconds(SMALL_WAIT_TIME_IN_SECONDS));

            waitTimedTask.setOnFinished(event -> 
            {
                if (!node.isFocused())
                    {
                        node.requestFocus();
                        forceFocus(node);
                    }
            });

            waitTimedTask.play();
        }

        private void updateSearchDataTable(String arg1, boolean arg2, boolean arg3, boolean arg4, String arg5)
        {
            View.setMouseCursorToWait();

            searchDataTable.getItems().clear();

            ArrayList<String[]> searchResult = Model.searchForRequiredCode(arg1, arg2, arg3, arg4, arg5);

            for (int i = 0; i < searchResult.size(); i++)
            {
                searchDataTable.getItems().add(new Model.SearchData( searchResult.get(i)[0], 
                                                                     searchResult.get(i)[1], 
                                                                     searchResult.get(i)[2]));
            }

            View.setMouseCursorToDefault();
        }


    /* Public class functions */
        public static void launchTheApp(String args[])
        {
            View.launchSplashScreen(args);
        }

        public static void setEditWindowData(int codeSnippetId, String codeSnippetLanguage)
        {
            Model.EditWindowData.reset(codeSnippetId);

            if (codeSnippetId != 0)
            {
                Model.EditWindowData.loadDataFromFile(codeSnippetId, codeSnippetLanguage);
            }
        }


    /* Public FXML functions */
        @FXML
        void addNewCode(ActionEvent event)
        {
            event.consume();

            View.launchEditWindow(0, "");
        }

        @FXML
        void goBack(ActionEvent event)
        {
            event.consume();

            View.closeEditWindow();
        }

        @FXML
        void searchForCode(ActionEvent event)
        {
            event.consume();

            updateSearchDataTable( searchLanguageField.getValue(), 
                                   searchTitleField.isSelected(), 
                                   searchNotesField.isSelected(), 
                                   searchTagsField.isSelected(), 
                                   searchTextField.getText());
        }

        @FXML
        void saveData(ActionEvent event)
        {
            if (Model.EditWindowData.compare( languageField.getValue(), 
                                                 titleField.getText(), 
                                                  codeField.getText(), 
                                                 notesField.getText(), 
                                                  tagsField.getText()))
            {
                View.showNotificationAlert("No changes have been made. This is the equivalent of 'Cancel'.");
                View.closeEditWindow();
            }
            else if (titleField.getText().length() == 0 
                 &&   codeField.getText().length() == 0 
                 &&  notesField.getText().length() == 0 
                 &&   tagsField.getText().length() == 0)
            {
                if (View.showConfirmationAlert("delete"))
                {
                    Model.EditWindowData.delete();

                    performSearchDataTableUpdate = true;

                    View.closeEditWindow();
                }
            }
            else
            {
                if (titleField.getText().trim().length() < Model.TITLE_FIELD_CHARS_MIN)
                {
                    View.showNotificationAlert( "The title field cannot be less than " + 
                                                 Model.TITLE_FIELD_CHARS_MIN + 
                                                " characters.");
                }
                else if (codeField.getText().trim().length() < Model.CODE_FIELD_CHARS_MIN)
                {
                    View.showNotificationAlert( "The code field cannot be less than " + 
                                                 Model.CODE_FIELD_CHARS_MIN + 
                                                " characters.");
                }
                else if (View.showConfirmationAlert("save"))
                {
                    Model.EditWindowData.save();

                    performSearchDataTableUpdate = true;

                    View.closeEditWindow();
                }
            }
        }

        @FXML
        void searchTableClicked(MouseEvent event)
        {
            if (event.getClickCount() == 2 && (!searchDataTable.getItems().isEmpty()))
            {
                final int rowIndex = searchDataTable.getSelectionModel().getSelectedIndex();

                if (rowIndex >= 0)
                {
                    View.launchEditWindow( Model.SearchData.idList.get(rowIndex).intValue(), 
                                           (String) searchDataTable.getColumns().get(2).getCellObservableValue(rowIndex).getValue());
                }
            }
        }

        @FXML
        public void initialize()
        {
            if (windowNumber == 1)
            {
                searchLanguageField.getItems().add("All");
                searchLanguageField.getItems().addAll(Model.languageFieldList);

                searchLanguageField.setValue("All");

                column1.setCellValueFactory(new PropertyValueFactory<Model.SearchData, String>("srNo"));
                column2.setCellValueFactory(new PropertyValueFactory<Model.SearchData, String>("title"));
                column3.setCellValueFactory(new PropertyValueFactory<Model.SearchData, String>("language"));

                searchDataTable.focusedProperty().addListener((observableValue, formerlyFocused, currentlyFocused) ->
                {
                    if (currentlyFocused)
                    {
                        if (performSearchDataTableUpdate)
                        {
                            updateSearchDataTable( Model.SearchData.searchParameter1, 
                                                   Model.SearchData.searchParameter2, 
                                                   Model.SearchData.searchParameter3, 
                                                   Model.SearchData.searchParameter4, 
                                                   Model.SearchData.searchParameter5);

                            performSearchDataTableUpdate = false;
                        }
                    }
                    else
                    {
                        searchDataTable.getSelectionModel().clearSelection();
                    }
                });

                updateSearchDataTable("All", true, false, false, "");
            }
            else if (windowNumber == 2)
            {
                languageField.getItems().add("All");
                languageField.getItems().addAll(Model.languageFieldList);

                languageField.setValue(Model.EditWindowData.language);

                titleField.setText(Model.EditWindowData.title);

                codeField.setText(Model.EditWindowData.code);

                notesField.setText(Model.EditWindowData.notes);

                tagsField.setText(Model.EditWindowData.tags);
            }
        }


    /* Event handlers */
        EventHandler<KeyEvent> keyPressedHandler = new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                event.consume();
            }
        };
}

