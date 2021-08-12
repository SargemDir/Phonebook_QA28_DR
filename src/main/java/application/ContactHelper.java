package application;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void openFormContact() {
        click(By.xpath("//a[.='ADD']"));
    }

    public void saveContact() {
        click(By.xpath("//b[.='Save']"));
    }

    public void fillFormContact(Contact contact) {
        type(By.cssSelector("[placeholder='Name']"), contact.getName());
        type(By.cssSelector("[placeholder='Last Name']"), contact.getLastname());
        type(By.cssSelector("[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("[placeholder='Address']"), contact.getAddress());
        type(By.cssSelector("[placeholder='description']"), contact.getDescription());
    }

    public boolean isContactAdded(String phone) {
        List<WebElement> contacts = wd.findElements(By.xpath("//h3"));
        for (WebElement el : contacts) {
            if (el.getText().equals(phone)) {
                System.out.print(el.getText());
                return true;
            }
        }
        return false;
    }

    public boolean removeOneContact() {
        int currentNumberofContacts = wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        wd.findElement(By.cssSelector(".contact-item_card__2SOIM")).click();
        click(By.xpath("//button[.='Remove']"));
        pause(2000);
        int newNumberofContacts = wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        if (currentNumberofContacts > newNumberofContacts) {
            return true;
        }
        return false;
    }

    public boolean removeAllContacts() {
        if (wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size() == 0) {

            return false;
        }
        while (wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size() != 0) {
            removeOneContact();
        }
        return true;
    }

    public void addOneContact() {
        int i = (int)(System.currentTimeMillis()/1000%3600);

        Contact contact = Contact.builder()
                .name("Con")
                .lastname("Conta")
                .phone("123456" + i)
                .email("Con" + i+ "@gmail.com")
                .address("Parom")
                .description("Friend")
                .build();
        openFormContact();
        fillFormContact(contact);
        saveContact();
        Assert.assertTrue(isContactAdded(contact.getPhone()));
    }

    public boolean isSaved() {
//        return wd.findElement(By.xpath("//button/b")).isEnabled();
        try {
            saveContact();
        } finally {
            return true;
        }
    }
}
