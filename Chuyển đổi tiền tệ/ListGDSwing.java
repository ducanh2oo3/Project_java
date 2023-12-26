
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListGDSwing {
    private ArrayList<GiaoDich> list;
    private static int tongGDVang, tongGDTienTe;

    // Định nghĩa các thành phần giao diện
    private JFrame frame;
    private JPanel panel;
    private JTextArea textArea;
    private JButton themGDVangButton, themGDTienTeButton, hienthiDSButton, getByDateButton, getByYearButton, sapXepButton;

    public ListGDSwing() {
        list = new ArrayList<>();
        tongGDVang = 0;
        tongGDTienTe = 0;

        // Khởi tạo các thành phần giao diện
        frame = new JFrame("Quản lý Giao Dịch");
        panel = new JPanel();
        textArea = new JTextArea(20, 50);
        JScrollPane scrollPane = new JScrollPane(textArea);
        themGDVangButton = new JButton("Thêm Giao Dịch Vàng");
        themGDTienTeButton = new JButton("Thêm Giao Dịch Tiền Tệ");
        hienthiDSButton = new JButton("Hiển thị danh sách");
        getByDateButton = new JButton("Tìm theo ngày");
        getByYearButton = new JButton("Tìm theo năm");
        sapXepButton = new JButton("Sắp xếp theo ngày");

        // Thêm các thành phần vào panel
        panel.add(scrollPane);
        panel.add(themGDVangButton);
        panel.add(themGDTienTeButton);
        panel.add(hienthiDSButton);
        panel.add(getByDateButton);
        panel.add(getByYearButton);
        panel.add(sapXepButton);

        // Thêm panel vào frame
        frame.add(panel);

        // Đăng ký sự kiện cho các nút
        themGDVangButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Hiển thị hộp thoại nhập thông tin giao dịch vàng
                JTextField maGDField = new JTextField(5);
                JTextField ngayGDField = new JTextField(10);
                JTextField donGiaField = new JTextField(5);
                JTextField soLuongField = new JTextField(5);
                JTextField loaiVangField = new JTextField(10);

                JPanel myPanel = new JPanel();
                myPanel.setLayout(new GridLayout(5, 2));
                myPanel.add(new JLabel("Mã GD:"));
                myPanel.add(maGDField);
                myPanel.add(new JLabel("Ngày GD (dd-mm-yyyy):"));
                myPanel.add(ngayGDField);
                myPanel.add(new JLabel("Đơn Giá:"));
                myPanel.add(donGiaField);
                myPanel.add(new JLabel("Số Lượng:"));
                myPanel.add(soLuongField);
                myPanel.add(new JLabel("Loại Vàng:"));
                myPanel.add(loaiVangField);

                int result = JOptionPane.showConfirmDialog(null, myPanel, "Nhập thông tin Giao Dịch Vàng", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    int magd = Integer.parseInt(maGDField.getText());
                    String ngaygd = ngayGDField.getText();
                    double dongia = Double.parseDouble(donGiaField.getText());
                    int soluong = Integer.parseInt(soLuongField.getText());
                    String loaiVang = loaiVangField.getText();

                    GiaoDich gd = new GiaoDichVang(magd, soluong, ngaygd, dongia, loaiVang);
                    gd.setThanhtien();
                    tongGDVang += gd.getSoLuong();
                    list.add(gd);

                    // Cập nhật textArea
                    textArea.setText("");
                    for (GiaoDich giaoDich : list) {
                        textArea.append(giaoDich.toString() + "\n");
                    }
                }
            }
        });


        themGDTienTeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Hiển thị hộp thoại nhập thông tin giao dịch tiền tệ
                JTextField maGDField = new JTextField(5);
                JTextField ngayGDField = new JTextField(10);
                JTextField donGiaField = new JTextField(5);
                JTextField soLuongField = new JTextField(5);
                JTextField loaiTienTeField = new JTextField(2);
                JTextField tiGiaField = new JTextField(5);

                JPanel myPanel = new JPanel();
                myPanel.setLayout(new GridLayout(6, 2));
                myPanel.add(new JLabel("Mã GD:"));
                myPanel.add(maGDField);
                myPanel.add(new JLabel("Ngày GD (dd-mm-yyyy):"));
                myPanel.add(ngayGDField);
                myPanel.add(new JLabel("Đơn Giá:"));
                myPanel.add(donGiaField);
                myPanel.add(new JLabel("Số Lượng:"));
                myPanel.add(soLuongField);
                myPanel.add(new JLabel("Loại Tiền Tệ (1: VND, 2: Ngoại tệ):"));
                myPanel.add(loaiTienTeField);
                myPanel.add(new JLabel("Tỉ Giá (nếu là ngoại tệ):"));
                myPanel.add(tiGiaField);

                int result = JOptionPane.showConfirmDialog(null, myPanel, "Nhập thông tin Giao Dịch Tiền Tệ", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    int magd = Integer.parseInt(maGDField.getText());
                    String ngaygd = ngayGDField.getText();
                    double dongia = Double.parseDouble(donGiaField.getText());
                    int soluong = Integer.parseInt(soLuongField.getText());
                    int loaiTienTe = Integer.parseInt(loaiTienTeField.getText());
                    double tiGia = loaiTienTe == 2 ? Double.parseDouble(tiGiaField.getText()) : 1.0;

                    GiaoDich gd = new GiaoDichTienTe(magd, soluong, ngaygd, dongia, tiGia, loaiTienTe);
                    gd.setThanhtien();
                    tongGDTienTe += gd.getSoLuong();
                    list.add(gd);

                    // Cập nhật textArea
                    textArea.setText("");
                    for (GiaoDich giaoDich : list) {
                        textArea.append(giaoDich.toString() + "\n");
                    }
                }
            }
        });


        hienthiDSButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xây dựng chuỗi để lưu danh sách giao dịch
                StringBuilder sb = new StringBuilder();
                sb.append("DANH SÁCH GIAO DỊCH\n");
                sb.append("MaGD\tSo Luong\tNgay GD\tDon Gia\tLoai vang/tygia\tTTien\n");

                for (GiaoDich gd : list) {
                    sb.append(gd.toString()).append("\n");
                }

                sb.append("=============================");

                // Cập nhật textArea
                textArea.setText(sb.toString());
            }
        });


        getByDateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ngayCanTim = JOptionPane.showInputDialog("Nhập ngày cần tìm (dd-mm-yyyy):");
                if (ngayCanTim != null && !ngayCanTim.isEmpty()) {
                    // Xây dựng chuỗi để lưu danh sách giao dịch theo ngày
                    StringBuilder sb = new StringBuilder();
                    sb.append("DANH SÁCH GIAO DỊCH Ngay ").append(ngayCanTim).append("\n");
                    sb.append("MaGD\tSo Luong\tNgay GD\tDon Gia\tLoai vang/tygia\tTTien\n");

                    for (GiaoDich gd : list) {
                        if (gd.getNgayGD().equals(ngayCanTim)) {
                            sb.append(gd.toString()).append("\n");
                        }
                    }

                    sb.append("===============");

                    // Cập nhật textArea
                    textArea.setText(sb.toString());
                }
            }
        });


        getByYearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String namCanTim = JOptionPane.showInputDialog("Nhập năm cần tìm:");
                if (namCanTim != null && !namCanTim.isEmpty()) {
                    int namTim = Integer.parseInt(namCanTim);

                    // Xây dựng chuỗi để lưu danh sách giao dịch theo năm
                    StringBuilder sb = new StringBuilder();
                    sb.append("DANH SÁCH GIAO DỊCH Nam ").append(namCanTim).append("\n");
                    sb.append("MaGD\tSo Luong\tNgay GD\tDon Gia\tLoai vang/tygia\tTTien\n");

                    for (GiaoDich gd : list) {
                        int year = Integer.parseInt(gd.getNgayGD().substring(6));
                        if (year == namTim) {
                            sb.append(gd.toString()).append("\n");
                        }
                    }

                    sb.append("===============");

                    // Cập nhật textArea
                    textArea.setText(sb.toString());
                }
            }
        });


        sapXepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Sắp xếp danh sách giao dịch theo ngày
                Collections.sort(list, new Comparator<GiaoDich>() {
                    @Override
                    public int compare(GiaoDich g1, GiaoDich g2) {
                        return g1.getNgayGD().compareTo(g2.getNgayGD());
                    }
                });

                // Xây dựng chuỗi để lưu danh sách giao dịch đã sắp xếp
                StringBuilder sb = new StringBuilder();
                sb.append("DANH SÁCH GIAO DỊCH (ĐÃ SẮP XẾP THEO NGÀY)\n");
                sb.append("MaGD\tSo Luong\tNgay GD\tDon Gia\tLoai vang/tygia\tTTien\n");

                for (GiaoDich gd : list) {
                    sb.append(gd.toString()).append("\n");
                }

                sb.append("===============");

                // Cập nhật textArea
                textArea.setText(sb.toString());
            }
        });


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ListGDSwing();
            }
        });
    }
}