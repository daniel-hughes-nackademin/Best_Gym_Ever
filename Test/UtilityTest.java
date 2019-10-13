import UtilityPackage.Utility;
import GymAndMenuPackage.BestGymEver;
import CustomerPackage.Customer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    void getCustomerListFromFile() {

    }


    @Test
    void showCustomerList(){
        BestGymEver bye = new BestGymEver();
        String filePath = "Test\\testFileFolder\\customersTest.txt";
        bye.setCustomerList(Utility.getCustomerListFromFile(filePath));

        assertTrue(Utility.getCustomerListAsString(bye.getCustomerList()).contains(bye.getCustomerList().get(0).getName()));
        assertTrue(Utility.getCustomerListAsString(bye.getCustomerList()).contains(bye.getCustomerList().get(0).getPersonalID()));
        assertTrue(Utility.getCustomerListAsString(bye.getCustomerList()).contains(bye.getCustomerList().get(5).getName()));
        assertTrue(Utility.getCustomerListAsString(bye.getCustomerList()).contains(bye.getCustomerList().get(5).getMembershipDate().toString()));
        assertTrue(Utility.getCustomerListAsString(bye.getCustomerList()).contains(bye.getCustomerList().get(5).toString()));
        assertFalse(Utility.getCustomerListAsString(bye.getCustomerList()).equals(null));
        assertFalse(Utility.getCustomerListAsString(bye.getCustomerList()).equals(filePath));
        assertFalse(Utility.getCustomerListAsString(bye.getCustomerList()).contains("Ida Malmkvist"));

        List<Customer> nullList = new ArrayList<>();
        assertTrue(Utility.getCustomerListAsString(nullList).equals(""));
        assertFalse(Utility.getCustomerListAsString(nullList) == null);
    }
}