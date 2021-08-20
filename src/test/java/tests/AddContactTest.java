package tests;

import application.MyDataProvider;
import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTest extends TestBase {
    @BeforeMethod(alwaysRun = true)
    public void precondition() {
        if (!app.userHelper().isLogged()) {
            app.userHelper().login(new User().withEmail("anat@gmail.com").withPassword("Aa12345$"));
        }
        app.ContactHelper().addOneContact();
    }

    @Test
    public void NegativeAddSameContact() {
        Contact contact = Contact.builder()
                .name("Ron")
                .lastname("Ronta")
                .phone("1234567890")
                .email("ron@gmail.com")
                .address("Shikun")
                .description("Friend")
                .build();
        app.ContactHelper().openFormContact();
        app.ContactHelper().fillFormContact(contact);
        app.ContactHelper().saveContact();
        app.ContactHelper().pause(2000);


        app.ContactHelper().openFormContact();
        app.ContactHelper().fillFormContact(contact);
        Assert.assertTrue(app.ContactHelper().isSaved());
    }

    @Test
    public void NegativeAddContactWithoutPhone() {
        Contact contact = Contact.builder()
                .name("Ron")
                .lastname("Ronta")
                .phone("")
                .email("ron@gmail.com")
                .address("Shikun")
                .description("Friend")
                .build();
        app.ContactHelper().openFormContact();
        app.ContactHelper().fillFormContact(contact);
        Assert.assertTrue(app.ContactHelper().isSaved());
    }

    @Test
    public void NegativeAddContactWithoutEmail() {
        Contact contact = Contact.builder()
                .name("Ron")
                .lastname("Ronta")
                .phone("1234567890")
                .email("")
                .address("Shikun")
                .description("Friend")
                .build();
        app.ContactHelper().openFormContact();
        app.ContactHelper().fillFormContact(contact);
        app.ContactHelper().saveContact();

        app.ContactHelper().openFormContact();
        app.ContactHelper().fillFormContact(contact);
        Assert.assertTrue(app.ContactHelper().isSaved());
    }

    @Test(invocationCount = 7)
    public void addContactTestBase() {
        int i = (int) (System.currentTimeMillis() / 1000 % 3600);

        Contact contact = Contact.builder()
                .name("Con")
                .lastname("Conta")
                .email("Con" + i + "@gmail.com")
                .phone("123456" + i)
                .address("Parom")
                .description("Friend")
                .build();
        app.ContactHelper().openFormContact();
        app.ContactHelper().fillFormContact(contact);
        app.ContactHelper().saveContact();
        Assert.assertTrue(app.ContactHelper().isContactAdded(contact.getPhone()));
    }

    @Test(dataProvider = "dataContactCSV", dataProviderClass = MyDataProvider.class)
    public void addContactTestCSV(Contact contact) {

        app.ContactHelper().openFormContact();
        app.ContactHelper().fillFormContact(contact);
        app.ContactHelper().saveContact();
        Assert.assertTrue(app.ContactHelper().isContactAdded(contact.getPhone()));
    }

    @Test(dataProvider = "dataContactDP", dataProviderClass = MyDataProvider.class)
    public void addContactTestDataProvider(String name, String lastname, String phone, String email, String address, String description) {


        Contact contact = Contact.builder()
                .name(name)
                .lastname(lastname)
                .email(phone)
                .phone(email)
                .address(address)
                .description(description)
                .build();
        app.ContactHelper().openFormContact();
        app.ContactHelper().fillFormContact(contact);
        app.ContactHelper().saveContact();
        Assert.assertTrue(app.ContactHelper().isContactAdded(contact.getPhone()));
    }

    @AfterMethod(alwaysRun = true)
    public void postcondition() {
        app.ContactHelper().removeAllContacts();
    }


}
