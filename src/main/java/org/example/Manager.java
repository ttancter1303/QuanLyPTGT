package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.data.Vehicle;
import org.example.data.XeMay;
import org.example.data.XeOtoBanTai;

import java.io.*;
import com.google.gson.reflect.TypeToken;
import org.example.data.XeOtoCon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Manager {
    static String filePath = "DataPTGT.json";
    ArrayList<Vehicle> vehicles = readData();
    public void add_vehicles() {
        Scanner scanner = new Scanner(System.in);
        String tenxe;
        do {
            System.out.println("Nhập tên xe: ");
            tenxe = scanner.nextLine();
            // Kiểm tra đầu vào với biểu thức chính quy
            if (!tenxe.matches("[a-zA-Z0-9]+")) {
                System.out.println("Tên xe hợp lệ mời nhập lại.");
            }
        } while (!tenxe.matches("[a-zA-Z0-9]+"));
        String hang_san_xuat;
        do {
            System.out.println("nhập tên hãng sản xuất ");
            hang_san_xuat = scanner.nextLine();
            // Kiểm tra đầu vào với biểu thức chính quy
            if (!hang_san_xuat.matches("[a-zA-Z]+")) {
                System.out.println("Tên hãng sản xuất hợp lệ mời nhập lại.");
            }
        } while (!hang_san_xuat.matches("[a-zA-Z]+"));
        // System.out.println("nhập năm sản xuất ");
        int nam_san_xuat;
        do {
            System.out.println("Nhập năm sản xuất: ");
            if (scanner.hasNextInt()) {
                nam_san_xuat = scanner.nextInt();
                break;
            } else {
                System.out.println("Năm sản xuất không hợp lệ. Vui lòng nhập lại.");
                scanner.next(); // Đọc và loại bỏ giá trị không hợp lệ khỏi bộ đệm của scanner
            }
        } while (true);
        int dung_tich;
        do {
            System.out.println("Nhập dung tích xe: ");
            if (scanner.hasNextInt()) {
                dung_tich = scanner.nextInt();
                break;
            } else {
                System.out.println("dung tích xe không hợp lệ. Vui lòng nhập lại.");
                scanner.next(); // Đọc và loại bỏ giá trị không hợp lệ khỏi bộ đệm của scanner
            }
        } while (true);


        double gia_tien;
        do {
            System.out.println("Nhập giá tiền : ");
            if (scanner.hasNextInt()) {
                gia_tien = scanner.nextInt();
                break;
            } else {
                System.out.println("giá tiền không hợp lệ. Vui lòng nhập lại.");
                scanner.next(); // Đọc và loại bỏ giá trị không hợp lệ khỏi bộ đệm của scanner
            }
        } while (true);

        System.out.print("Nhập Lệ Phí Trước Ba: ");
        float le_phi_truoc_ba;
        do {
            if (scanner.hasNextInt()) {
                le_phi_truoc_ba = scanner.nextInt();
                break;
            } else {
                System.out.println("Lệ Phí Trước Ba không hợp lệ. Vui lòng nhập lại.");
                scanner.next(); // Đọc và loại bỏ giá trị không hợp lệ khỏi bộ đệm của scanner
            }
        } while (true);
        System.out.print("Nhập tên người khai thuế: ");
        String ten_nguoi_khai_thue = scanner.next();
        String ma_so_thue;
        do {
            System.out.println("Nhập mã số thuế : ");
            ma_so_thue = scanner.nextLine();
            if (maSoThueChecker(vehicles,ma_so_thue)){
                System.out.println("Mã số thuế không hợp lệ");
            }
        }while (maSoThueChecker(vehicles,ma_so_thue));

        XeMay xeMay = new XeMay("XeMay", tenxe, hang_san_xuat, nam_san_xuat, gia_tien, le_phi_truoc_ba, ma_so_thue, ten_nguoi_khai_thue, dung_tich);

        // thêm đối tượng xe máy vào danh sach vehicles \
        vehicles.add(xeMay);
        System.out.print("Thêm vehicle thành công: ");
        //  lưu danh sách vehicles vào tệp tin
        saveData();

    }
    static void cot_print_oto_con() {
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("| STT | Mã số thuế    | Loại xe |  Tên  |  Hãng  | Năm sản xuất |  Giá Tiền  | Lệ phí trước bạ(%) | Tên người khai thuế | Dung tích động c | Dung tích cốp |");
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------------------+");
    }
    static void cot_print_oto_ban_tai() {
        System.out.println("+-------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("| STT | Mã số thuế | Loại xe | Tên | Hãng | Năm sản xuất | Giá Tiền | Lệ phí trước bạ(%) | Tên người khai thuế | Dung tích động c | Tai trong | Chieu dai thung xe  |");
        System.out.println("+-------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
    }

    static void Dong_print_oto_ban_tai(XeOtoBanTai xe_ban_tai, int stt) {
        System.out.printf("| %-3s | %-13s | %-7s | %-5s | %-7s | %-12s | %-8s | %-18s | %-19s | %-17s | %-13s |%-13s |",
                stt, xe_ban_tai.getMaSoThue(), xe_ban_tai.loaiXe, xe_ban_tai.getTenPhuongTien(), xe_ban_tai.getHangSanXuat(),
                xe_ban_tai.getNamSanXuat(), Math.round(xe_ban_tai.getGiaTien()), xe_ban_tai.getLePhiTruocBa(), xe_ban_tai.getTenNguoiKhaiThue(),
                xe_ban_tai.getDungTichDongCo(),xe_ban_tai.getTaiTrong(),xe_ban_tai.getChieuDaiThungXe());
        // System.out.println(formattedRow);
        System.out.println("\n+----------------------------------------------------------------------------------------------------------------------------------------------------------------+");
    }
    static void Dong_print_oto_con(XeOtoCon xecon, int stt) {
        System.out.printf("| %-3s | %-13s | %-7s | %-5s | %-7s | %-12s | %-8s | %-18s | %-19s | %-17s | %-13s |",
                stt, xecon.getMaSoThue(), xecon.loaiXe, xecon.getTenPhuongTien(), xecon.getHangSanXuat(),
                xecon.getNamSanXuat(), Math.round(xecon.getGiaTien()), xecon.getLePhiTruocBa(), xecon.getTenNguoiKhaiThue(),
                xecon.getDungTichDongCo(),xecon.getDungTichCop(),xecon.getDungTichCop());
        // System.out.println(formattedRow);
        System.out.println("\n+-----------------------------------------------------------------------------------------------------------------------------------------------------------+");
    }
    static void cot_xe_may() {
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("| STT | Mã số thuế   | Loại xe | Tên    | Hãng  | Năm sản xuất | Giá Tiền | Lệ phí trước bạ(%) | Tên người khai thuế | Dung tích động cơ |");
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------+");
    }
    static void Dong_xe_may(XeMay xeMay, int stt) {
        System.out.printf("| %-3s | %-12s | %-7s | %-6s | %-6s | %-10s | %-8s | %-18s | %-20s | %-17s |",
                stt, xeMay.getMaSoThue(), xeMay.loaiXe, xeMay.getTenPhuongTien(), xeMay.getHangSanXuat(),
                xeMay.getNamSanXuat(), Math.round(xeMay.getGiaTien()), xeMay.getLePhiTruocBa(), xeMay.getTenNguoiKhaiThue(),
                xeMay.getDungTichDongCo());
        // System.out.println(formattedRow);
        System.out.println("\n+---------------------------------------------------------------------------------------------------------------------------------------+");
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
        System.out.println("Hiển thị thông tin phương tiện");
        System.out.println("1,Xe máy");
        System.out.println("2,Xe ô tô");
        System.out.println("3,Xe ô tô bán tải");
        System.out.println("0,Exit");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice){
            case 1:{
                XeMay xeMay;

                cot_xe_may();
                int stt = 1;
                for (Vehicle vehicle : vehicles) {

                    if (vehicle instanceof XeMay) {
                        xeMay = (XeMay) vehicle;
                        Dong_xe_may(xeMay, stt);
                        stt++;
                    }

                }
                break;
            }
            case 2:{
                XeOtoCon xeOtoCon;

                cot_print_oto_con();
                int stt = 1;
                for (Vehicle vehicle : vehicles) {

                    if (vehicle instanceof XeOtoCon) {
                        xeOtoCon = (XeOtoCon) vehicle;
                        Dong_print_oto_con(xeOtoCon, stt);
                        stt++;
                    }

                }
                break;
            }
            case 3:{
                XeOtoBanTai xeOtoBanTai;

                cot_print_oto_ban_tai();
                int stt = 1;
                for (Vehicle vehicle : vehicles) {

                    if (vehicle instanceof XeOtoBanTai) {
                        xeOtoBanTai = (XeOtoBanTai) vehicle;
                        Dong_print_oto_ban_tai(xeOtoBanTai, stt);
                        stt++;
                    }

                }
                break;
            }

            case 0:{

            }
            default:{

            }
        }
    }

    static void pause ()
    {
        System.out.println("\nNhấn enter để tiếp tục");
        new java.util.Scanner(System.in).nextLine();
    }
    static void cot() {
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("| STT | Mã số thuế   | Loại xe | Tên    | Hãng  | Năm sản xuất | Giá Tiền | Lệ phí trước bạ(%) | Tên người khai thuế | Dung tích động cơ |");
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------+");
    }


    static void Dong(XeMay xeMay, int stt) {
        System.out.printf("| %-3s | %-12s | %-7s | %-6s | %-6s | %-10s | %-8s | %-18s | %-20s | %-17s |",
                stt, xeMay.getMaSoThue(), xeMay.loaiXe, xeMay.getTenPhuongTien(), xeMay.getHangSanXuat(),
                xeMay.getNamSanXuat(), Math.round(xeMay.getGiaTien()), xeMay.getLePhiTruocBa(), xeMay.getTenNguoiKhaiThue(),
                xeMay.getDungTichDongCo());
        // System.out.println(formattedRow);
        System.out.println("\n+---------------------------------------------------------------------------------------------------------------------------------------+");
    }
    static void Xuất(List<Vehicle> vehicles) {
        // In cột
        cot();

        // In các Dong
        int stt = 1;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.loaiXe.equalsIgnoreCase("XeMay")){
                if (vehicle instanceof XeMay) {
                    XeMay xeMay = (XeMay) vehicle;
                    Dong(xeMay, stt);
                    stt++;
                }
            }
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
    private boolean maSoThueChecker(ArrayList<Vehicle> list, String taxId){
        for (Vehicle vehicle : list) {
            if (vehicle.getMaSoThue().equals(taxId)){
                return true;
            }
        }
        return false;
    }
    private static ArrayList<Vehicle> readData() {
        ArrayList<Vehicle> dataFromFile = new ArrayList<>();

        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

            for (Object jsonItem : jsonArray) {
                JSONObject jsonObject = (JSONObject) jsonItem;

                String loaiXe = (String) jsonObject.get("loaiXe");
                String maSoThue = (String) jsonObject.get("maSoThue");
                String tenPhuongTien = (String) jsonObject.get("tenPhuongTien");
                String hangSanXuat = (String) jsonObject.get("hangSanXuat");
                int namSanXuat = Math.toIntExact((long) jsonObject.get("namSanXuat"));
                double giaTien = (double) jsonObject.get("giaTien");
                float lePhiTruocBa = Float.parseFloat(jsonObject.get("lePhiTruocBa").toString());
                String tenNguoiKhaiThue = (String) jsonObject.get("tenNguoiKhaiThue");

                if ("XeMay".equals(loaiXe)) {
                    int dungTichDongCo = Math.toIntExact((long) jsonObject.get("dungTichDongCo"));
                    XeMay xeMay = new XeMay(loaiXe, tenPhuongTien, hangSanXuat, namSanXuat, giaTien, lePhiTruocBa, maSoThue, tenNguoiKhaiThue, dungTichDongCo);
                    dataFromFile.add(xeMay);
                } else if ("Oto".equals(loaiXe)) {
                    int dungTichDongCo = Math.toIntExact((long) jsonObject.get("dungTichDongCo"));
                    int dungTichCop = Math.toIntExact((long) jsonObject.get("dungTichCop"));
                    XeOtoCon xeOtoCon = new XeOtoCon(loaiXe, tenPhuongTien, hangSanXuat, namSanXuat, giaTien, lePhiTruocBa, maSoThue, tenNguoiKhaiThue, dungTichDongCo, dungTichCop);
                    dataFromFile.add(xeOtoCon);
                } else if ("XeOtoBanTai".equals(loaiXe)) {
                int dungTichDongCo = Math.toIntExact((long) jsonObject.get("dungTichDongCo"));
                int taiTrong = Math.toIntExact((long) jsonObject.get("taiTrong"));
                int chieuDaiThungXe = Math.toIntExact((long) jsonObject.get("chieuDaiThungXe"));
                XeOtoBanTai xeOtoBanTai = new XeOtoBanTai(loaiXe, tenPhuongTien, hangSanXuat, namSanXuat, giaTien, lePhiTruocBa, maSoThue, tenNguoiKhaiThue, dungTichDongCo, taiTrong, chieuDaiThungXe);
                dataFromFile.add(xeOtoBanTai);
            }
            }
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi khi đọc tệp tin: " + e.getMessage());
        }

        return dataFromFile;
    }
    public void xoa()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã số thuế của bản ghi cần xóa: ");
        String maSoThueXoa = scanner.nextLine();
        boolean found = false;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getMaSoThue().equals(maSoThueXoa)) {
                vehicles.remove(vehicle);
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("Xóa thành công Vehicle có mã số thuế: " + maSoThueXoa);
        } else {
            System.out.println("Không tìm thấy Vehicle nào với mã số thuế: " + maSoThueXoa);
        }
    }
    public void edit_vehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ma so thue can sua");
        String maSoThue = scanner.next();


        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof XeMay && vehicle.getMaSoThue().equals(maSoThue)) {
                XeMay xeMay = (XeMay) vehicle;
                System.out.println("nhập tên cần sửa");
                String name = scanner.next();
                // Sử dụng các phương thức của lớp XeMay để sửa đổi thông tin
                xeMay.setTenPhuongTien(name);

                System.out.println("nhập dung tích cần sửa");
                int dung_tich = scanner.nextInt();
                xeMay.setDungTichDongCo(dung_tich);

                System.out.println("nhập tên hãng cần sửa");
                String ten_hang = scanner.next();
                xeMay.setHangSanXuat(ten_hang);

                System.out.println("nhập năm sản xuất cần sửa");
                int nsx = scanner.nextInt();
                xeMay.setNamSanXuat(nsx);

                System.out.println("nhập giá tiền cần sửa");
                double gia = scanner.nextDouble();

                xeMay.setGiaTien(gia);

                System.out.println("nhập lệ phí trước bạ cần sửa(%)");
                int le_phi_truoc_ba = scanner.nextInt();

                xeMay.setLePhiTruocBa(le_phi_truoc_ba);

                System.out.println("nhập tên người khai thuế cần sửa");
                String nguoikhaithue = scanner.next();
                xeMay.setTenNguoiKhaiThue(nguoikhaithue);
                // Thực hiện các thay đổi khác tùy theo yêu cầu

                // Hiển thị thông tin xe máy sau khi sửa
                System.out.println("Đã sửa thông tin xủa xe thành công");
                break;
            }
        }
    }
    public void sort(){
//        nhớ ép kiểu từ vehical sang loại phương tiện tương ứng để sử dụng get set của class tương ứng
        System.out.println("--------- Sắp Xếp -----------");
        System.out.println("1. Giá tiền.");
        System.out.println("2. Năm sản xuất.");
        System.out.println("3. Lệ phí trước bạ.");
        System.out.println("0. Thoát.");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                System.out.println("1. Tăng dần.");
                System.out.println("2. Giảm dần.");
                int choice1 = scanner.nextInt();
                switch (choice1) {
                    case 1: {
                        sortGiaTienGiamDan();
                        break;
                    }
                    case 2: {
                        sortGiaTienTangDan();
                        break;
                    }
                    default:
                        break;
                }
                break;
            }
            case 2: {
                System.out.println("1. Tăng dần.");
                System.out.println("2. Giảm dần.");
                int choice2 = scanner.nextInt();
                switch (choice2) {
                    case 1: {
                        sortNamSanXuatGiamDan();
                        break;
                    }
                    case 2: {
                        sortNamSanXuatTangDan();
                        break;
                    }
                    default:
                        break;
                }
                break;
            }
            case 3: {
                System.out.println("1. Tăng dần.");
                System.out.println("2. Giảm dần.");
                int choice3 = scanner.nextInt();
                switch (choice3) {
                    case 1: {
                        sortLePhiTruocBaGiamDan();
                        break;
                    }
                    case 2: {
                        sortLePhiTruocBaTangDan();
                        break;
                    }
                    default:
                        break;
                }
                break;
            }
            case 0: {
                break;
            }
            default:
                break;
        }
        showData();
    }
    public void sortGiaTienGiamDan ()
    {
        for (int i = 0; i < vehicles.size() - 1; i++)
        {
            for (int j = i; j < vehicles.size(); j++)
            {
                if (vehicles.get(i).getGiaTien() < vehicles.get(j).getGiaTien())
                {
                    Vehicle tam = vehicles.get(i);
                    vehicles.set(i,vehicles.get(j));
                    vehicles.set(j,tam);
                }
            }
        }
    }

    public void sortGiaTienTangDan ()
    {
        for (int i = 0; i < vehicles.size() - 1; i++)
        {
            for (int j = i; j < vehicles.size(); j++)
            {
                if (vehicles.get(i).getGiaTien() > vehicles.get(j).getGiaTien())
                {
                    Vehicle tam = vehicles.get(i);
                    vehicles.set(i,vehicles.get(j));
                    vehicles.set(j,tam);
                }
            }
        }
    }

    public void sortNamSanXuatGiamDan ()
    {
        for (int i = 0; i < vehicles.size() - 1; i++)
        {
            for (int j = i; j < vehicles.size(); j++)
            {
                if (vehicles.get(i).getNamSanXuat() < vehicles.get(j).getNamSanXuat())
                {
                    Vehicle tam = vehicles.get(i);
                    vehicles.set(i,vehicles.get(j));
                    vehicles.set(j,tam);
                }
            }
        }
    }

    public void sortNamSanXuatTangDan ()
    {
        for (int i = 0; i < vehicles.size() - 1; i++)
        {
            for (int j = i; j < vehicles.size(); j++)
            {
                if (vehicles.get(i).getNamSanXuat() > vehicles.get(j).getNamSanXuat())
                {
                    Vehicle tam = vehicles.get(i);
                    vehicles.set(i,vehicles.get(j));
                    vehicles.set(j,tam);
                }
            }
        }
    }

    public void sortLePhiTruocBaGiamDan ()
    {
        for (int i = 0; i < vehicles.size() - 1; i++)
        {
            for (int j = i; j < vehicles.size(); j++)
            {
                if (vehicles.get(i).getLePhiTruocBa() > vehicles.get(j).getLePhiTruocBa())
                {
                    Vehicle tam = vehicles.get(i);
                    vehicles.set(i,vehicles.get(j));
                    vehicles.set(j,tam);
                }
            }
        }
    }

    public void sortLePhiTruocBaTangDan ()
    {
        for (int i = 0; i < vehicles.size() - 1; i++)
        {
            for (int j = i; j < vehicles.size(); j++)
            {
                if (vehicles.get(i).getLePhiTruocBa() < vehicles.get(j).getLePhiTruocBa())
                {
                    Vehicle tam = vehicles.get(i);
                    vehicles.set(i,vehicles.get(j));
                    vehicles.set(j,tam);
                }
            }
        }
    }

    public void thongKe(){
//        nhớ ép kiểu từ vehical sang loại phương tiện tương ứng để sử dụng get set của class tương ứng
        System.out.print("Thông kê số lượng xe theo loại phương tiện giao thông: \n"); // làm tạm thống kê loại xe
        thongkeTheoLoaiXe();
        pause();
    }

    public void thongkeTheoLoaiXe() {

        // Mảng chứa dữ liệu thống kê
        int[] sl = new int[vehicles.size()]; // mỗi phần tử = 0
        Arrays.fill(sl, 1); // gán phần tử = 1

        // Tinh chỉnh dữ liệu thống kê
        for (int i = 0; i < vehicles.size(); i++) {
            for (int j = i + 1; j < vehicles.size(); j++) {
                var i_j_cùng_loại = vehicles.get(i).getLoaiXe().equals(vehicles.get(j).getLoaiXe());

                if// nếu
                (i_j_cùng_loại && sl[j] != 0) {
                    sl[i]++;
                    sl[j]--;
                }
            }
        }

        // In dữ liệu thống kê, phân loại ra màn hình
        for (int i = 0; i < sl.length; i++) {
            if (sl[i] != 0) {
                System.out.printf("\n Phương tiện giao thông %s có %d chiếc.", vehicles.get(i).getLoaiXe(), sl[i]);
            }
        }
    }
}
