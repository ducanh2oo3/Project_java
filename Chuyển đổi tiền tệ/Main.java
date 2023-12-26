import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParseException, IOException, ClassNotFoundException{
        Scanner scanner=new Scanner(System.in);

        ListGD listGD=new ListGD();
        while(true){
            System.out.println("1.Thêm giao dịch\n" +
                    "2.Danh sách\n" +
                    "3.Tìm kiếm\n" +
                    "4.Thoát");
            int option1=0;
            option1 = scanner.nextInt();
            switch (option1){
                case 1:
                    System.out.println("1.Vàng 2.Tiền tệ 3.Quay lại");
                    int option2= scanner.nextInt();
                    switch (option2){
                        case 1:
                            listGD.themGDVang();
                            break;
                            
                        case 2:
                            listGD.themGDTT();
                            break;
                    }
                    break;
                case 2:
                    System.out.println("1.Hiển thị toàn bộ danh sách\n" +
                            "2.Sắp xếp theo ngày giao dịch\n" +
                            "3.Quay lại");
                    int option3=scanner.nextInt();
                    switch (option3){
                        case 1:
                            listGD.hienthiDS();
                            break;
                        case 2:
                            listGD.sapXepByNgay();
                            break;
                    }
                    break;
                case 3:
                    System.out.println("1.Hiển thị toàn bộ danh sách\n" +
                            "2.Tìm kiếm số lượng\n" +
                            "3.Tìm kiếm theo ngày giao dịch\n" +
                            "4.Tìm kiếm theo năm\n" +
                            "5.Quay lại");
                    int option4=scanner.nextInt();
                    switch (option4){
                        case 1:
                            listGD.hienthiDS();
                            break;
                        case 2:
                            System.out.println("Nhập khoảng");
                            int min=scanner.nextInt(),max=scanner.nextInt();
                            listGD.getBySoLuong(min,max);
                            break;
                        case 3:
                            System.out.println("Nhập ngày tháng năm");
                            String time=scanner.next();
                            listGD.getByDate(time);
                            break;
                        case 4:
                            System.out.println("Nhập khoảng năm");
                            int minYear=scanner.nextInt(),maxYear=scanner.nextInt();
                            listGD.getByYear(minYear,maxYear);
                            break;
                    }
                    break;
            }
            if(option1==4) break;
        }
    }
}