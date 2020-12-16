package com.example.demo;

import com.example.demo.model.Notes;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CRUDNoteTesting extends ApplicationTests{

    @Test
    public void createReadTest() throws InterruptedException {
        String noteTitle = "Note title";
        String noteDescription = "This is note to test.";
        HomePage homePage= getHomePage();
        assertEquals("Home", driver.getTitle());
        createNote(noteTitle,noteDescription,homePage);
        homePage.openNotesTab();
        homePage = new HomePage(driver);
        Notes note = homePage.getFirstNote();
        Assertions.assertEquals(noteTitle, note.getNoteTitle());
        Assertions.assertEquals(noteDescription, note.getNoteDescription());
        deleteNote(homePage);
        Thread.sleep(4000);
        homePage.logout();
    }
    @Test
    public void noteUpdateTest() throws InterruptedException {
        String noteTitle = "My Note";
        String noteDescription = "This is my note test.";
        HomePage homePage = getHomePage();
        createNote(noteTitle, noteDescription, homePage);
        homePage.openNotesTab();
        homePage = new HomePage(driver);
        homePage.editNote();
        String modifiedNoteTitle = "Modified Note";
        homePage.modifyNoteTitle(modifiedNoteTitle);

        String modifiedNoteDescription = "This is my modified note.";
        homePage.modifyNoteDescription(modifiedNoteDescription);

        homePage.saveNoteChanges();
        homePage.openNotesTab();
        Notes note = homePage.getFirstNote();
        Assertions.assertEquals(modifiedNoteTitle, note.getNoteTitle());
        Assertions.assertEquals(modifiedNoteDescription, note.getNoteDescription());
    }

    private void createNote(String noteTitle, String noteDescription, HomePage homePage) {
        homePage.openNotesTab();
        homePage.addNewNote();
        homePage.setNoteTitle(noteTitle);
        homePage.setNoteDescription(noteDescription);
        homePage.saveNoteChanges();
        homePage.openNotesTab();
    }
    private void deleteNote(HomePage homePage) {
        homePage.deleteNote();
    }

}
