package CodeSnippetsAccumulator;

import javafx.fxml.FXML;
import java.util.ArrayList;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.cell.PropertyValueFactory;

/*
   Copyright 2021 Melwyn Francis Carlo <carlo.melwyn@outlook.com>

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

public class Controller
{
    /* Public class variables */
        public static int windowNumber = 0;

        public static final double    SMALL_WAIT_TIME_IN_SECONDS = 0.05;
        public static final double   NORMAL_WAIT_TIME_IN_SECONDS = 0.25;
        public static final double SPLASH_SCREEN_TIME_IN_SECONDS = 2.00;

        public static SimpleBooleanProperty performSearchDataTableUpdate = new SimpleBooleanProperty(false);


    /* Private class variables */


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

        public static void dataDirectoryCheck()
        {
            Model.checkOrCreateDataDirectory();
        }


    /* Private FXML functions */
        @FXML
        private void addNewCode(ActionEvent event)
        {
            event.consume();
            View.launchEditWindow(0, "");
        }

        @FXML
        private void goBack(ActionEvent event)
        {
            event.consume();
            View.closeEditWindow();
        }

        @FXML
        private void searchForCode(ActionEvent event)
        {
            event.consume();
            updateSearchDataTable( searchLanguageField.getValue(), 
                                   searchTitleField.isSelected(), 
                                   searchNotesField.isSelected(), 
                                   searchTagsField.isSelected(), 
                                   searchTextField.getText());
        }

        @FXML
        private void saveData(ActionEvent event)
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
                    performSearchDataTableUpdate.set(true);
                    View.closeEditWindow();
                }
            }
            else
            {
                if (languageField.getValue().trim().length() == 0)
                {
                    View.showNotificationAlert( "The language field cannot be blank.");
                }
                else if (titleField.getText().trim().length() < Model.TITLE_FIELD_CHARS_MIN)
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
                    performSearchDataTableUpdate.set(true);
                    View.closeEditWindow();
                }
            }
        }

        @FXML
        private void searchTableClicked(MouseEvent event)
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

    /* Public FXML functions */
        @FXML
        public void initialize()
        {
            if (windowNumber == 1)
            {
                performSearchDataTableUpdate.addListener((observableValue, oldState, newState) ->
                {
                    if (newState)
                    {
                        updateSearchDataTable( Model.SearchData.searchParameter1, 
                                               Model.SearchData.searchParameter2, 
                                               Model.SearchData.searchParameter3, 
                                               Model.SearchData.searchParameter4, 
                                               Model.SearchData.searchParameter5);

                        performSearchDataTableUpdate.set(false);
                    }
                });

                searchLanguageField.getItems().add("All");
                searchLanguageField.getItems().addAll(Model.languageFieldList);

                searchLanguageField.setValue("All");

                column1.setCellValueFactory(new PropertyValueFactory<Model.SearchData, String>("srNo"));
                column2.setCellValueFactory(new PropertyValueFactory<Model.SearchData, String>("title"));
                column3.setCellValueFactory(new PropertyValueFactory<Model.SearchData, String>("language"));

                searchDataTable.focusedProperty().addListener((observableValue, formerlyFocused, currentlyFocused) ->
                {
                    if (!currentlyFocused) { searchDataTable.getSelectionModel().clearSelection(); }
                });

                updateSearchDataTable("All", true, false, false, "");
            }
            else if (windowNumber == 2)
            {
                languageField.getItems().addAll(Model.languageFieldList);

                languageField.setValue(Model.EditWindowData.language);
                   titleField.setText (Model.EditWindowData.title);
                   notesField.setText (Model.EditWindowData.notes);
                    codeField.setText (Model.EditWindowData.code);
                    tagsField.setText (Model.EditWindowData.tags);
            }
        }
}

