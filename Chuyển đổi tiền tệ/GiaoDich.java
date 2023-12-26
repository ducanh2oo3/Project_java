
public class GiaoDich{
    protected int MaGD,SoLuong;
    protected String NgayGD;
    protected double DonGia, ThanhTien;
    protected GiaoDich(){
        this.MaGD=0;
        this.NgayGD="";
        this.DonGia=0;
        this.SoLuong=0;
        this.ThanhTien=0;
    }
    protected GiaoDich(int magd){
        this.MaGD=magd;
        this.NgayGD="";
        this.DonGia=0;
        this.SoLuong=0;
        this.ThanhTien=0;
    }
    protected GiaoDich(int ma, int sl, String ngay, double dgia){
        this.MaGD=ma;
        this.NgayGD=ngay;
        this.DonGia=dgia;
        this.SoLuong=sl;
        this.ThanhTien=0;
    }

// Các getter và setter cho các thuộc tính
    public void setThanhtien(double thanhtien){
        this.ThanhTien = thanhtien;
    }
    public void setThanhtien(){
        ThanhTien = DonGia*SoLuong;
    }
    public double getThanhTien(){
        return this.ThanhTien;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }
    
    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }

    public void setNgayGD(String NgayGD) {
        this.NgayGD = NgayGD;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public int getMaGD() {
        return MaGD;
    }

    public String getNgayGD() {
        return NgayGD;
    }

    public double getDonGia() {
        return DonGia;
    }
    @Override
    public String toString() {
        return MaGD + "\t" + SoLuong+ "\t" + NgayGD + "\t" + DonGia;
    }
    
// chỉ cần khi ta gọi phương thức contains
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        // if (getClass() != o.getClass())
        // return false;
        GiaoDich other;
        other = (GiaoDich) o;
        if (MaGD != other.MaGD)
            return false;
        return true;
    }
}
