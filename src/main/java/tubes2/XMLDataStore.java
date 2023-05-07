package tubes2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.json.XML;
import org.json.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import java.io.File;
import java.io.FileReader;

public class XMLDataStore implements IDataStore {
    private String filePath="src/main/java/tubes2/data";

    @Override
    public String getFilePath() {
        return this.filePath;
    }

    @Override
    public void setFilePath(String path) {
        this.filePath=path;
    }

    @Override
    public Customer getCustomer(int id) {
        CustomerList customerList=this.readCustomer();
        return customerList.getCustomerByID(id);
    }

    @Override
    public CustomerList readCustomer() {
        try (FileReader reader=new FileReader(this.getFilePath()+"/customer.xml")){
            Gson gson=new GsonBuilder()
                    .registerTypeAdapter(Customer.class,new CustomerDeserializer())
                    .create();
            JSONObject jsonObject= XML.toJSONObject(reader);
            JsonParser parser=new JsonParser();
            JsonObject root=parser.parse(jsonObject.toString()).getAsJsonObject();
//            System.out.println(root);
            JsonObject customlist=root.getAsJsonObject("customerListClass");
//            System.out.println(customlist);
            CustomerList cl=gson.fromJson(customlist,new TypeToken<CustomerList>(){}.getType());
            return cl;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addCustomer(Customer customer) {
        CustomerList cl=this.readCustomer();
        cl.addCustomer(customer);
        this.writeCustomer(cl);
    }

    @Override
    public void writeCustomer(CustomerList customerList) {
        try{
            JAXBContext jaxbContext=JAXBContext.newInstance(CustomerList.class);
            Marshaller marshaller=jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(customerList,new File(this.getFilePath()+"/customer.xml"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public BarangList readBarang() {
        try (FileReader reader=new FileReader(this.getFilePath()+"/barang.xml")){
            Gson gson=new GsonBuilder()
                    .registerTypeAdapter(Barang.class,new BarangDeserializer())
                    .create();
            JSONObject jsonObject= XML.toJSONObject(reader);
            JsonParser parser=new JsonParser();
            JsonObject root=parser.parse(jsonObject.toString()).getAsJsonObject();
//            System.out.println(root);
            JsonObject baranglist=root.getAsJsonObject("barangListClass");
//            System.out.println(customlist);
            BarangList bl=gson.fromJson(baranglist,new TypeToken<BarangList>(){}.getType());
            return bl;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeBarang(BarangList barangList) {
        try{
            JAXBContext jaxbContext=JAXBContext.newInstance(BarangList.class);
            Marshaller marshaller=jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(barangList,new File(this.getFilePath()+"/barang.xml"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Barang getBarang(int idBarang) {
        BarangList barangList=this.readBarang();
        return barangList.getBarang(idBarang);
    }

    @Override
    public void updateCustomer(Customer customer) {
        CustomerList customerList=this.readCustomer();
        customerList.updateCustomer(customer);
        this.writeCustomer(customerList);
    }

    @Override
    public void addBarang(Barang barang) {
        BarangList barangList=this.readBarang();
        barangList.addBarang(barang);
        this.writeBarang(barangList);
    }

    @Override
    public void updateBarang(Barang barang) {
        BarangList barangList=this.readBarang();
        barangList.updateBarang(barang);
        this.writeBarang(barangList);
    }
}
