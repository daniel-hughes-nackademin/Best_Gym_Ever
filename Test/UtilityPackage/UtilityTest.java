package UtilityPackage;

import CustomerPackage.Customer;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    void validatedSignUpInput() {
        assertTrue(Utility.validatedSignUpInput("Lukas Gustavson", "1210134508"));
        assertTrue(Utility.validatedSignUpInput("mr andersson", "0611301234"));
        assertTrue(Utility.validatedSignUpInput("mR anDErSSon", "0611301234"));


        assertFalse(Utility.validatedSignUpInput("Luka5 Gustavson", "1210134508"));
        assertFalse(Utility.validatedSignUpInput("Lukas  Gustavson", "1210134508"));
        assertFalse(Utility.validatedSignUpInput("mr ande#sson", "0611301234"));
        assertFalse(Utility.validatedSignUpInput("Lukas", "1210134508"));
        assertFalse(Utility.validatedSignUpInput("Lukas Gregorson", "1213134508")); //invalid month
        assertFalse(Utility.validatedSignUpInput("Lukas Gregorson", "1202304508")); //Invalid day
        assertFalse(Utility.validatedSignUpInput("mr. andersson", "0611301234"));
        assertFalse(Utility.validatedSignUpInput("Lukas Gregorson", "abcdefghji"));
    }

    @Test
    void numberOfSpaces() {
        String test1 = "one two three four five Six SeVeN eigHt niiiiiiiiiiiiiiiiine ";
        String test2 = "AOINDOAIWI \t <++--. ";

        assertEquals(9, Utility.numberOfSpaces(test1));
        assertEquals(3, Utility.numberOfSpaces(test2));
        assertEquals(0, Utility.numberOfSpaces("JustTextNoSpaces"));
        assertEquals(2, Utility.numberOfSpaces("  "));
        assertEquals(4, Utility.numberOfSpaces("W h  a t"));

        assertNotEquals(4, Utility.numberOfSpaces("ONE    TOO MANY"));
        assertNotEquals(3, Utility.numberOfSpaces("Tab\tIsn't\tspace\t"));
    }

    @Test
    void isLettersAndSpacesOnly() {

        assertTrue(Utility.isLettersAndSpacesOnly("       "));
        assertTrue(Utility.isLettersAndSpacesOnly("Daniel Hughes"));
        assertTrue(Utility.isLettersAndSpacesOnly("abcdefghijklmnop"));
        assertTrue(Utility.isLettersAndSpacesOnly("sgskajgblbkjIUASBDjk askdjk kAJSDKJ akjfgkajb kajUIPPWNasg aksdkajsd  askdjkJA kafg"));
        assertFalse(Utility.isLettersAndSpacesOnly("sgskajgblbkjIUASBDj9 askdjk kAJSDKJ akjfgkajb kajUIPPWNasg aksdkajsd  askdjkJA kafg"));
        assertFalse(Utility.isLettersAndSpacesOnly(null));
        assertFalse(Utility.isLettersAndSpacesOnly("3 is a number"));
        assertFalse(Utility.isLettersAndSpacesOnly("Daniel\tHughes"));
    }

    @Test
    void isDigitsOnly() {
        assertTrue(Utility.isDigitsOnly("123456789"));
        assertTrue(Utility.isDigitsOnly("638472487260462639846293849284924"));
        assertTrue(Utility.isDigitsOnly("13"));

        assertFalse(Utility.isDigitsOnly("63847248726046+2639846293849284924"));
        assertFalse(Utility.isDigitsOnly("This is a text"));
        assertFalse(Utility.isDigitsOnly("12837123 127837813"));
        assertFalse(Utility.isDigitsOnly(" 12837123127837813"));
        assertFalse(Utility.isDigitsOnly("128-3"));
    }

    @Test
    void testGetCustomerFromList() {
        List<Customer> customerList = Utility.getCustomerListFromFile(Paths.get("Test\\testFileFolder\\customersTest.txt"));

        Customer testPerson1 = new Customer();
        testPerson1.setName("Alhambra Aromes");
        testPerson1.setPersonalID("7603021234");
        testPerson1.setMembershipDate(LocalDate.of(2018, 7, 1));

        Customer testPerson2 = new Customer();
        testPerson2.setName("Mitsuko Mayotte");
        testPerson2.setPersonalID("7907281234");
        testPerson2.setMembershipDate(LocalDate.of(2018, 12, 22));

        assertEquals(customerList.get(12).getName(), testPerson2.getName());
        assertEquals(customerList.get(12).getPersonalID(), testPerson2.getPersonalID());
        assertEquals(customerList.get(12).getMembershipDate(), testPerson2.getMembershipDate());

        assertNotEquals(customerList.get(11).getMembershipDate(), testPerson2.getMembershipDate());


        customerList = Utility.getCustomerListFromFile(Paths.get("Test\\testFileFolder\\Testnumber2.txt"));

        Customer testPerson3 = new Customer();
        testPerson3.setName("Galna Pantern");
        testPerson3.setPersonalID("1212125567");
        testPerson3.setMembershipDate(LocalDate.of(1846, 2, 28));

        Customer testPerson4 = new Customer();
        testPerson4.setName("Master Yoda");
        testPerson4.setPersonalID("8910111234");
        testPerson4.setMembershipDate(LocalDate.of(2019, 10, 15));

        assertEquals(testPerson3.getName(), customerList.get(customerList.size() - 1).getName());
        assertEquals(testPerson3.getPersonalID(), customerList.get(customerList.size() - 1).getPersonalID());
        assertEquals(testPerson3.getMembershipDate(), customerList.get(customerList.size() - 1).getMembershipDate());

        assertEquals(testPerson4.getName(), customerList.get(2).getName());
        assertEquals(testPerson4.getPersonalID(), customerList.get(2).getPersonalID());
        assertEquals(testPerson4.getMembershipDate(), customerList.get(2).getMembershipDate());
    }

    @Test
    void validatedSearchInput() {

        assertTrue(Utility.validatedSearchInput("Daniel Hughes"));
        assertTrue(Utility.validatedSearchInput("HuGH GraNt"));
        assertTrue(Utility.validatedSearchInput("melissa mcdonald"));
        assertTrue(Utility.validatedSearchInput("1212348989"));
        assertTrue(Utility.validatedSearchInput("8910121212"));

        assertFalse(Utility.validatedSearchInput("89101212127"));
        assertFalse(Utility.validatedSearchInput("891012 1212"));
        assertFalse(Utility.validatedSearchInput("891012-1212"));
        assertFalse(Utility.validatedSearchInput("Daniel  Hughes"));
        assertFalse(Utility.validatedSearchInput("Daniel 1212128989"));
    }
}