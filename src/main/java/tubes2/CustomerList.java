package tubes2;

import java.util.ArrayList;
import java.util.List;

public class CustomerList {
    private List<Customer> customerList;
    public CustomerList(){
        this.customerList=new ArrayList<>();
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
    public void addCustomer(Customer customer){
        this.customerList.add(customer);
    }
    public void updateCustomer(Customer customer){
        for (Customer c :
                this.customerList) {
            if (c.getId() == customer.getId()) {
                c.setHistoriTransaksi(customer.getHistoriTransaksi());
            }
        }
    }
    public Customer getCustomerByID(int id){
        return this.customerList.stream()
                .filter(customer -> customer.getId()==id)
                .findFirst()
                .orElse(null);
    }
}
