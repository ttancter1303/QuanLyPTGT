package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.data.Vehicle;
import org.example.data.XeMay;

import java.io.*;
import com.google.gson.reflect.TypeToken;
import org.example.data.XeOtoCon;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    String filePath = "DataPTGT.json";
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    public void add_vehicles()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("nhập tên xe ");
        String tenxe = scanner.nextLine();

        System.out.println("nhập tên hãng sản xuất ");
        String hang_san_xuat = scanner.nextLine();

        System.out.println("nhập năm sản xuất ");
        int nam_san_xuat = scanner.nextInt();

        System.out.println("nhập dung tích ");
        int dung_tich = scanner.nextInt();

        scanner.nextLine();
        String loai_xe = "XeMay";

        System.out.print("Nhập giá tiền: ");
        double gia_tien = scanner.nextInt();

        System.out.print("Nhập Lệ Phí Trước Ba: ");
        float le_phi_truoc_ba  = scanner.nextFloat();

        System.out.print("Nhập mã số thuế: ");
        String ma_so_thue = scanner.nextLine();
        maSoThueChecker(vehicles,ma_so_thue);

        scanner.nextLine();

        System.out.print("Nhập tên người khai thuế: ");
        String ten_nguoi_khai_thue = scanner.nextLine();

        XeMay xeMay = new XeMay( loai_xe ,tenxe, hang_san_xuat, nam_san_xuat, gia_tien, le_phi_truoc_ba, ma_so_thue, ten_nguoi_khai_thue,dung_tich);

        // thêm đối tượng xe máy vào danh sach vehicles \
        vehicles.add(xeMay);
        //  lưu danh sách vehicles vào tệp tin
        saveData();

    }
    public void testData(){
        XeMay xeMay = new XeMay("XeMay","Wave","honda",2015,10007000,1.5F,"TN986876GV76","Ta Tuan Anh",100);
        XeMay xeMay1 = new XeMay("XeMay","Wave","honda",2015,10300000,1.5F,"TN9868764V56","Ta Tuan Anh",100);
        XeMay xeMay2 = new XeMay("XeMay","Wave","honda",2015,10800000,1.5F,"TN986873GV56","Ta Tuan Anh",100);
        XeMay xeMay3 = new XeMay("XeMay","Wave","honda",2015,10300000,1.5F,"TN986871GV56","Ta Tuan Anh",100);
        XeMay xeMay4 = new XeMay("XeMay","Wave","honda",2015,10008000,1.5F,"TN986879GV56","Ta Tuan Anh",100);
        XeOtoCon otoCon0 = new XeOtoCon("Oto","civic","honda",2019,190000800,2F,"BD98965896HY","Phung Ngoc Bao",1035,100);
        XeOtoCon otoCon1 = new XeOtoCon("Oto","civic","honda",2019,190400000,2F,"BD98795896HY","Phung Ngoc Bao",1035,100);
        XeOtoCon otoCon2 = new XeOtoCon("Oto","civic","honda",2019,190080000,2F,"BD98705896HY","Phung Ngoc Bao",1035,100);
        XeOtoCon otoCon3 = new XeOtoCon("Oto","civic","honda",2019,199000000,2F,"BD98715896HY","Phung Ngoc Bao",1035,100);
        XeOtoCon otoCon4 = new XeOtoCon("Oto","civic","honda",2019,190004000,2F,"BD987905896HY","Phung Ngoc Bao",1035,100);
        XeOtoCon otoCon5 = new XeOtoCon("Oto","civic","honda",2019,195000000,2F,"BD98764896HY","Phung Ngoc Bao",1035,100);

        vehicles.add(xeMay);
        vehicles.add(xeMay1);
        vehicles.add(xeMay2);
        vehicles.add(xeMay3);
        vehicles.add(xeMay4);
        vehicles.add(otoCon1);
        vehicles.add(otoCon2);
        vehicles.add(otoCon0);
        vehicles.add(otoCon3);
        vehicles.add(otoCon4);
        vehicles.add(otoCon5);
    }
    public void showData(){
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }
    }
    public void saveData(){
        try {
            Writer writer = new FileWriter(filePath);
            File saveData = new File(filePath);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            if (saveData.createNewFile()) {
                System.out.println("File created: " + saveData.getName());
            } else {
                gson.toJson(vehicles,writer);
                writer.close(); // Đóng Writer sau khi ghi dữ liệu vào tệp tin
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private boolean maSoThueChecker(ArrayList<Vehicle> list,String taxId){
        for (Vehicle vehicle : list) {
            if (vehicle.getMaSoThue().equals(taxId)){
                System.out.println("ma so thue da ton tai");
                return true;
            }
        }
        return false;
    }
    private ArrayList<Vehicle> readData(){
        ArrayList<Vehicle> dataFormFile = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Sử dụng Gson để chuyển đổi từ JSON sang ArrayList
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Vehicle>>() {}.getType();
            dataFormFile = gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi đọc tệp tin: " + e.getMessage());
        }
        return dataFormFile;
    }
    public void editData(){
//        nhớ ép kiểu từ vehical sang loại phương tiện tương ứng để sử dụng get set của class tương ứng


    }
    public void sort(){
//        nhớ ép kiểu từ vehical sang loại phương tiện tương ứng để sử dụng get set của class tương ứng

    }
    public void search(){
//        nhớ ép kiểu từ vehical sang loại phương tiện tương ứng để sử dụng get set của class tương ứng

    }
    public void thongKe(){
//        nhớ ép kiểu từ vehical sang loại phương tiện tương ứng để sử dụng get set của class tương ứng

    }

}
