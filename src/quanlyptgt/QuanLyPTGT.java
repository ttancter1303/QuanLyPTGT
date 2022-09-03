/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quanlyptgt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author 01689
 */
public class QuanLyPTGT {
    public static float thueXeMay=5/100;
    public static float thueOtoCon=12/100;
    public static float thueOtoBanTai=(float) (7.2/100);
    private static String name;
    private static String hangSanXuat;
    private static String namSanXuat;
    private static double giaTien;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<XeDap> xeDaps = new ArrayList<>();
        ArrayList<XeMay> xeMays = new ArrayList<>();
        ArrayList<XeOtoCon> xeOtoCons = new ArrayList<>();
        ArrayList<XeOtoBanTai> xeOtoBanTais = new ArrayList<>();
        int luaChon;
        do {
            Scanner input = new Scanner(System.in);
            System.out.println("1,Them 1 phuong tien");
            System.out.println("2,Xuat thong tin");
            System.out.println("3,Tim kiem phuong tien theo hang san xuat");
            System.out.println("4,Tinh tien thue truoc ba");
            System.out.println("0,Thoat chuong trinh");
            System.out.print("Nhap vao lua chon cua ban: ");
            luaChon = input.nextInt();
            switch (luaChon) {
                case 1:
                    int luaChon2;
                    do {
                        System.out.println("1,Them Xe Dap");
                        System.out.println("2,Them Xe May");
                        System.out.println("3,Them Xe O To Con");
                        System.out.println("4,Them xe O To Ban Tai");
                        System.out.println("0,Thoat chuong trinh");
                        Scanner input2 = new Scanner(System.in);
                        System.out.print("Nhap vao lua chon cua ban: ");
                        luaChon2 = input2.nextInt();   
                        switch (luaChon2) {
                            case 1:
                                Scanner sc = new Scanner(System.in);
                                XeDap xeDap = new XeDap();
                                System.out.println("Nhap ten xe dap : ");
                                name=sc.nextLine();
                                xeDap.setName(name);
                                System.out.println("Nhap hang san xuat : ");
                                hangSanXuat=sc.nextLine();
                                xeDap.setHangSanXuat(hangSanXuat);
                                System.out.println("Nhap nam san xuat : ");
                                namSanXuat=sc.nextLine();
                                xeDap.setHangSanXuat(namSanXuat);
                                System.out.println("Nhap gia tien : ");
                                giaTien=sc.nextDouble();
                                xeDap.setGiaTien(giaTien);
                                xeDaps.add(xeDap);
                                break;
                            case 2:
                                for(int i=0;i<xeDaps.size();i++){
                                   xeDaps.get(i).toString();
                                }
                                break;
                            case 3:
                                
                                break;
                            case 4:
                                
                                break;                 
                            default:
                                throw new AssertionError();
                        }
                    } while (luaChon2==0);
                    break;
                case 2:
                    
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    System.out.println("Tam biet ! ");
                    break;
                default:
                    throw new AssertionError();
            }
            input.close();
        } while (luaChon==0);
    }
    
}
