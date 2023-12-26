
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ListGD {
    private ArrayList<GiaoDich> list;
    private static int tonggdv, tonggdtt;
    public ListGD(){
        list = new ArrayList<GiaoDich>();
        tonggdv=0;
        tonggdtt=0;
    }
    private GiaoDich themGD(){
        Scanner in=new Scanner(System.in);
        System.out.println("Nhap thong tin GD: ");
        System.out.println("Ma GD (nhap so): ");
        int magd = in.nextInt();
        GiaoDich gd = new GiaoDich(magd);
        if(list.contains(gd))
            return null;
        else{
            String ngaygd="";
            while(true){
                System.out.println("Ngay Giao Dich(dd-mm-yyyy): ");
                ngaygd = in.next();
                if(ngaygd.matches("\\d{2}-\\d{2}-\\d{4}$"))
                break;
            }
            gd.setNgayGD(ngaygd);
            System.out.println("Don Gia: ");
            double dongia = in.nextDouble();
            gd.setDonGia(dongia);
            System.out.println("So Luong: ");
            int soluong = in.nextInt();
            gd.setSoLuong(soluong);
            return gd;
        }
    }
    public boolean themGDVang(){
        Scanner in=new Scanner(System.in);
        GiaoDich gd = themGD();
        if(gd == null)
            return false;
        else{
            System.out.println("Loai Vang: ");
            String loaivang = in.next();
            GiaoDichVang gdv = new GiaoDichVang(gd.getMaGD(),gd.getSoLuong(), gd.getNgayGD(),gd.getDonGia(), loaivang);
            gdv.setThanhtien();
            tonggdv += gdv.getSoLuong();
            return list.add(gdv);
        }
    }
    public boolean themGDTT(){
        Scanner in=new Scanner(System.in);
        GiaoDich gd = themGD();
        if(gd == null)
            return false;
        else{
            System.out.println("Loai Tien Te (nhap 1:vnd, 2:ngoai te): ");
            int loaitiente=in.nextInt();
            double tigia;
            if(loaitiente==1) tigia=1;
            else{
                System.out.println("Ti Gia: ");
                tigia=in.nextDouble();
            }
            GiaoDichTienTe gdtt = new GiaoDichTienTe(gd.getMaGD(),gd.getSoLuong(), gd.getNgayGD(),gd.getDonGia(), tigia,loaitiente);
            tonggdtt += gdtt.getSoLuong();
            gdtt.setThanhtien();
            return list.add(gdtt);
        }
    }
    public void hienthiDS(){
        System.out.println("==DANH SACH GIAO DICH ==");
        System.out.println("MaGD So Luong Ngay GD Don Gia Loai vang/tygia TTien");
        double tt=0;
        for (GiaoDich gd: list){
            System.out.println(gd.toString());
            tt += gd.getThanhTien();
        }
        System.out.println("=================");
        System.out.println("Tong tien: " + Double.toString(tt));
    }
    public void getByDate(String ngaygd){
        System.out.println("==DANH SACH GIAO DICH Ngay "+ngaygd+"=========");
        System.out.println("MaGD So Luong Ngay GD Don Gia Loai vang/tygia TTien");
        for (GiaoDich gd: list)
            if(gd.getNgayGD().equals(ngaygd))
                System.out.println(gd.toString());
        System.out.println("=============");
    }
    public void getContainDate(String ngaygd){
        System.out.println("=====DANH SACH GIAO DICH Ngay "+ngaygd+"=========");
        System.out.println("MaGD So Luong Ngay GD Don Gia Loai vang/tygia TTien");
        for (GiaoDich gd: list)
            if(gd.getNgayGD().indexOf(ngaygd)>=0)
                System.out.println(gd.toString());
        System.out.println("===============");
        }
    public int tongGGVang(){
        return tonggdv; 
    }
    public int tongGGTienTe(){
        return tonggdtt; 
    }
    public void getBySoLuong(int from,int to){
        System.out.println("=====DANH SACH GIAO DICH Ngay =========");
        System.out.println("MaGD So Luong Ngay GD Don Gia Loai vang/tygia TTien");
        for (GiaoDich gd: list)
            if((gd.getSoLuong()>=from) && (gd.getSoLuong()<=to))
                System.out.println(gd.toString());
        System.out.println("=====================================");
    }

    public void getByYear(int from,int to){
        System.out.println("=====DANH SACH GIAO DICH Ngay =========");
        System.out.println("MaGD So Luong Ngay GD Don Gia Loai vang/tygia TTien");
        for (GiaoDich gd: list)
            if((Integer.parseInt((gd.getNgayGD().substring(6)))>=from)&&Integer.parseInt((gd.getNgayGD().substring(6)))<=to)
                System.out.println(gd.toString());
        System.out.println("=====================================");
    }
    public void sapXepByNgay(){
        Collections.sort(list, new Comparator<GiaoDich>() {
            @Override
            public int compare(GiaoDich g1,GiaoDich g2) {
                return ((g1.getNgayGD().substring(6)+g1.getNgayGD().substring(3,5)+g1.getNgayGD().substring(0,2)).compareTo((g2.getNgayGD().substring(6)+g2.getNgayGD().substring(3,5)+g2.getNgayGD().substring(0,2))));
            }
        });
    }
}
