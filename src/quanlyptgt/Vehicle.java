/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyptgt;

/**
 *
 * @author 01689
 */
public abstract class Vehicle {
    private String name;
    private String hangSanXuat;
    private int namSanXuat;
    private double giaTien;
    private float thueTruocBa;

    @Override
    public String toString() {
        return "Vehicle{" + "name=" + name + ", hangSanXuat=" + hangSanXuat + ", namSanXuat=" + namSanXuat + ", giaTien=" + giaTien + '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHangSanXuat() {
        return hangSanXuat;
    }

    public void setHangSanXuat(String hangSanXuat) {
        this.hangSanXuat = hangSanXuat;
    }

    public int getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(int namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public float getThueTruocBa() {
        return thueTruocBa;
    }

    public void setThueTruocBa(float thueTruocBa) {
        this.thueTruocBa = thueTruocBa;
    }
    
    public double TinhThueTruocBa(double giaTien,float thueTruocBa){
        double Thue = giaTien*thueTruocBa;
        return Thue;
    }
}
