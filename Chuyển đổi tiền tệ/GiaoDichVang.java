

class GiaoDichVang extends GiaoDich {
    private String loaivang;
    public GiaoDichVang(){
        super();
        this.loaivang="";
    }
    public GiaoDichVang(int ma, int sl,String ngay, double dgia,String lvang){
        super(ma, sl, ngay, dgia);
        this.loaivang=lvang;
    }

    // không cần cài đặt các phương thức getter và setter để lấy các thuộc tính của lớp cha nữa
    // chỉ cài đặt các phương thức getter và setter của thuộc tính lớp mình
    public String getLoaivang() {
        return loaivang;
    }
    public void setLoaivang(String loaivang) {
        this.loaivang = loaivang;
    }

    // viết đè lên phương thức toString() của lớp cha nếu thấy cần
    public String toString(){
        return super.toString() + "\t" +loaivang + "\t\t" + super.getThanhTien();
    }
}

