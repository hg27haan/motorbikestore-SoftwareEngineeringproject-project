/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.GioHang;
import entity.FeedBack;
import entity.SoLuongXeDaBan;
import entity.TongChiTieuMuaHang;
import entity.Supplier;
//import entity.Account;
import entity.DanhMuc;
import entity.HoaDon;
import entity.XeMay;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<XeMay> getAllXeMay() {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<SoLuongXeDaBan> getTop10SanPhamBanChay() {
        List<SoLuongXeDaBan> list = new ArrayList<>();
        String query = "select top(10) *\r\n"
        		+ "from SoLuongXeDaBan\r\n"
        		+ "order by soLuongDaBan desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SoLuongXeDaBan(rs.getInt(1),
                        rs.getInt(2)
                  ));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    public List<DanhMuc> getAllCategory() {
        List<DanhMuc> list = new ArrayList<>();
        String query = "select * from DanhMuc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DanhMuc(rs.getInt(1),rs.getString(2)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    public List<HoaDon> getAllInvoice() {
        List<HoaDon> list = new ArrayList<>();
        String query = "select * from HoaDon";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new HoaDon(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getDate(4)
                       ));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public int countAllProductBySellID(int sell_ID) {
        String query = "select count(*) from Product where sell_ID=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, sell_ID);
            rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getInt(1);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return 0;
    }
    
    public int getSellIDByProductID(int productID) {
        String query = "select sell_ID\r\n"
        		+ "from Product\r\n"
        		+ "where [id]=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getInt(1);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return 0;
    }
    
    public double totalMoneyDay(int day) {
        String query = "select \r\n"
        		+ "	SUM(tongTien) \r\n"
        		+ "from HoaDon\r\n"
        		+ "where DATEPART(dw,[ngayThanhToan]) = ?\r\n"
        		+ "Group by ngayThanhToan ";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, day);
            rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getDouble(1);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return 0;
    }
    
    public double totalMoneyMonth(int month) {
        String query = "select SUM(tongTien) from HoaDon\r\n"
        		+ "where MONTH(ngayThanhToan)=?\r\n"
        		+ "Group by MONTH(ngayThanhToan)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, month);
            rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getDouble(1);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return 0;
    }
    
    public int countAllXeMay() {
        String query = "select count(*) from XeMay";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getInt(1);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return 0;
    }
    
    public double sumAllInvoice() {
        String query = "select SUM(tongGia) from sumAllInvoice";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getDouble(1);
            }
        } catch (Exception e) {
        	//e.printStackTrace();
            //System.out.println("Có lỗi");
        }
        return 0;
    }
    
    public int countAllReview() {
        String query = "select count(*) from Review";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getInt(1);
            }
        } catch (Exception e) {
        	//e.printStackTrace();
            //System.out.println("Có lỗi");
        }
        return 0;
    }
    
    public int getmaDanhMucBymaXe(String maXe) {
        String query = "select [maDanhMuc] from XeMay\r\n"
        		+ "where [maXe] =?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, maXe);
            rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getInt(1);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return 0;
    }
    
    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String query = "select * from Account";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                		rs.getString(5)));
            }
        } catch (Exception e) {
        	//e.printStackTrace();
            //System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<Supplier> getAllSupplier() {
        List<Supplier> list = new ArrayList<>();
        String query = "select * from Supplier";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Supplier(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                		rs.getInt(6)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
   
    public List<TongChiTieuMuaHang> getTop5KhachHang() {
        List<TongChiTieuMuaHang> list = new ArrayList<>();
        String query = "select top(5) *\r\n"
        		+ "from TongChiTieuMuaHang\r\n"
        		+ "order by tongChiTieu desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TongChiTieuMuaHang(rs.getInt(1),
                        rs.getDouble(2)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<TongChiTieuMuaHang> getTop5NhanVien() {
        List<TongChiTieuMuaHang> list = new ArrayList<>();
        String query = "select top(5) *\r\n"
        		+ "from TongChiTieuBanHang\r\n"
        		+ "order by TongBanHang desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TongChiTieuMuaHang(rs.getInt(1),
                        rs.getDouble(2)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }

    public List<XeMay> getTop3() {
        List<XeMay> list = new ArrayList<>();
        String query = "select top 3 * from XeMay";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }

    public List<XeMay> getNext3XeMay(int soLuong) {
        List<XeMay> list = new ArrayList<>();
        String query = "SELECT *\n"
                + "  FROM XeMay\n"
                + " ORDER BY maXe\n"
                + "OFFSET ? ROWS\n"
                + " FETCH NEXT 3 ROWS ONLY";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, soLuong);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> getNext4VisionProduct(int soLuong) {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay\r\n"
        		+ "where maDanhMuc=2\r\n"
        		+ "order by maXe desc\r\n"
        		+ "offset ? rows\r\n"
        		+ "fetch next 4 rows only";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, soLuong);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    public List<XeMay> getAllProduct() {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    public List<XeMay> getNext4AirBladeProduct(int soLuong) {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay\r\n"
        		+ "where maDanhMuc=1\r\n"
        		+ "order by maXe desc\r\n"
        		+ "offset ? rows\r\n"
        		+ "fetch next 4 rows only";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, soLuong);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> getXeMayBymaDanhMuc(String maDanhMuc) {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay\n"
                + "where maDanhMuc = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, maDanhMuc);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }

    public List<XeMay> getProductBySellIDAndIndex(int id, int indexPage) {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay \r\n"
        		+ "where sell_ID = ?\r\n"
        		+ "order by [id]\r\n"
        		+ "offset ? rows\r\n"
        		+ "fetch next 5 rows only";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, (indexPage-1)*5);
            rs = ps.executeQuery();
            while (rs.next()) {
            	 list.add(new XeMay(rs.getInt(1),
                         rs.getString(2),
                         rs.getString(3),
                         rs.getDouble(4),
                         rs.getString(5),
                         rs.getString(6),
                         rs.getString(8),
                         rs.getString(9),
                         rs.getString(10),
                         rs.getString(11),
                         rs.getString(12),
                         rs.getString(13),
                         rs.getString(14),
                         rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> getXeMayByIndex(int indexPage) {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay \r\n"
        		+ "order by [maXe]\r\n"
        		+ "offset ? rows\r\n"
        		+ "fetch next 9 rows only";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, (indexPage-1)*9);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }

    public List<XeMay> searchBytenXe(String txtSearch) {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay\n"
                + "where [tenXe] like ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<HoaDon> searchByNgayXuat(String ngayXuat) {
        List<HoaDon> list = new ArrayList<>();
        String query = "select * from HoaDon\r\n"
        		+ "where [ngayThanhToan] ='"+ngayXuat+"'";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new HoaDon(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getDate(4)
                       ));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> searchgiaTienUnder14() {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay\r\n"
        		+ "where [giaTien] < 10000000";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> searchgiaTien14To20() {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay\r\n"
        		+ "where [giaTien] >= 14000000 and [giaTien]<= 20000000";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> searchColorWhite() {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from Product\r\n"
        		+ "where color = 'White'";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	 list.add(new XeMay(rs.getInt(1),
                         rs.getString(2),
                         rs.getString(3),
                         rs.getDouble(4),
                         rs.getString(5),
                         rs.getString(6),
                         rs.getString(8),
                         rs.getString(9),
                         rs.getString(10),
                         rs.getString(11),
                         rs.getString(12),
                         rs.getString(13),
                         rs.getString(14),
                         rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> searchColorGray() {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from Product\r\n"
        		+ "where color = 'Gray'";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	 list.add(new XeMay(rs.getInt(1),
                         rs.getString(2),
                         rs.getString(3),
                         rs.getDouble(4),
                         rs.getString(5),
                         rs.getString(6),
                         rs.getString(8),
                         rs.getString(9),
                         rs.getString(10),
                         rs.getString(11),
                         rs.getString(12),
                         rs.getString(13),
                         rs.getString(14),
                         rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> searchColorBlack() {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from Product\r\n"
        		+ "where color = 'Black'";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	 list.add(new XeMay(rs.getInt(1),
                         rs.getString(2),
                         rs.getString(3),
                         rs.getDouble(4),
                         rs.getString(5),
                         rs.getString(6),
                         rs.getString(8),
                         rs.getString(9),
                         rs.getString(10),
                         rs.getString(11),
                         rs.getString(12),
                         rs.getString(13),
                         rs.getString(14),
                         rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> searchColorYellow() {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from Product\r\n"
        		+ "where color = 'Yellow'";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	 list.add(new XeMay(rs.getInt(1),
                         rs.getString(2),
                         rs.getString(3),
                         rs.getDouble(4),
                         rs.getString(5),
                         rs.getString(6),
                         rs.getString(8),
                         rs.getString(9),
                         rs.getString(10),
                         rs.getString(11),
                         rs.getString(12),
                         rs.getString(13),
                         rs.getString(14),
                         rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    public List<XeMay> searchBygiaTienMinToMax(String giaTienMin,String giaTienMax) {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay\r\n"
        		+ "where [giaTien] >= ? and [giaTien]<=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, giaTienMin);
            ps.setString(2, giaTienMax);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> searchPriceAbove20() {
        List<XeMay> list = new ArrayList<>();
        String query = "select * from XeMay\r\n"
        		+ "where [giaTien] > 20000000";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> getRelatedXeMay(int maDanhMucXeMayDetail) {
        List<XeMay> list = new ArrayList<>();
        String query = "select top 4 * from XeMay\r\n"
        		+ "where [maDanhMuc] =?\r\n"
        		+ "order by maXe desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maDanhMucXeMayDetail);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    
    public List<FeedBack> getAllFeedBackBymaXe(String maXe) {
        List<FeedBack> list = new ArrayList<>();
        String query = "select * from FeedBack\r\n"
        		+ "where [maXe] =?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, maXe);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new FeedBack(rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getDate(5)
                       ));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }

    public XeMay getXeMayBymaXe(String maXe) {
        String query = "select *from XeMay\n"
                + "where maXe = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, maXe);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return null;
    }
    
    public List<GioHang> getGioHangBymaAccount(int maAccount) {
    	 List<GioHang> list = new ArrayList<>();
        String query = "select * from GioHang where maAccount = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maAccount);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new GioHang(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }

    public GioHang checkGioHangExist(int maAccount,int maXe) {

       String query = "select * from GioHang\r\n"
       		+ "where [maAccount] = ? and [maXe] = ?";
       try {
           conn = new DBContext().getConnection();//mo ket noi voi sql
           ps = conn.prepareStatement(query);
           ps.setInt(1, maAccount);
           ps.setInt(2, maXe);
           rs = ps.executeQuery();
           while (rs.next()) {
               return new GioHang(rs.getInt(1),
                       rs.getInt(2),
                       rs.getInt(3),
                       rs.getInt(4));
           }
       } catch (Exception e) {
    	   e.printStackTrace();
    	    System.out.println("Có lỗi");
       }
      return null;
   }
    
    public int checkAccountAdmin(int userID) {

        String query = "select isAdmin from Account where [maAccount]=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);

            rs = ps.executeQuery();  
            while (rs.next()) {
            	 return rs.getInt(1);
             }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return 0;
    }
    
    public TongChiTieuMuaHang checkTongChiTieuMuaHangExist(int maAccount) {

        String query = "select * from TongChiTieuMuaHang where [maAccount]=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maAccount);
           
            rs = ps.executeQuery();
            while (rs.next()) {
                return new TongChiTieuMuaHang(rs.getInt(1),
                        rs.getDouble(2));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
       return null;
    }
    
    public SoLuongXeDaBan checkSoLuongXeDaBanExist(int maXe) {

        String query = "select * from SoLuongXeDaBan where maXe = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maXe);
           
            rs = ps.executeQuery();
            while (rs.next()) {
                return new SoLuongXeDaBan(rs.getInt(1),
                        rs.getInt(2)
                       );
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
       return null;
    }
    
    
    
    public List<DanhMuc> getAllDanhMuc() {
        List<DanhMuc> list = new ArrayList<>();
        String query = "select * from DanhMuc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DanhMuc(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    
//
    public XeMay getLast() {
        String query = "select top 1 * from XeMay\n"
                + "order by maXe desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new XeMay(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return null;
    }
    
    public List<XeMay> get8Last() {
    	List<XeMay> list = new ArrayList<>();
        String query = "select top 8 * from XeMay order by maXe desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	list.add(new XeMay(rs.getInt(1),
            			rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> get4VisionLast() {
    	List<XeMay> list = new ArrayList<>();
        String query = "select top 4 * from XeMay\r\n"
        		+ "where maDanhMuc = 2\r\n"
        		+ "order by maXe desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	list.add(new XeMay(rs.getInt(1),
            			rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }
    
    public List<XeMay> get4AirBladeLast() {
    	List<XeMay> list = new ArrayList<>();
        String query = "select top 4 * from XeMay\r\n"
        		+ "where maDanhMuc = 1\r\n"
        		+ "order by maXe desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	list.add(new XeMay(rs.getInt(1),
            			rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15)));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return list;
    }

    public Account login(String user, String pass) {
        String query = "select * from Account\n"
                + "where [username] = ?\n"
                + "and password = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                		rs.getString(5));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return null;
    }

    public Account checkAccountExist(String username) {
        String query = "select * from Account\n"
                + "where [username] = ?\n";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                		rs.getString(5));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return null;
    }
    
    public Account checkAccountExistByusernameAndemail(String username, String email) {
        String query = "select * from Account where [username]=? and [email]=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                		rs.getString(5));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
        return null;
    }
    
    public FeedBack getNewFeedBack(int maAccount, int maXe) {
        String query = "select top 1 * from FeedBack\r\n"
        		+ "where maAccount = ? and maXe = ?\r\n"
        		+ "order by maFeedBack desc";
        FeedBack fb = new FeedBack();
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maAccount);
            ps.setInt(2, maXe);
            rs = ps.executeQuery();
            while (rs.next()) {
                return fb = new FeedBack(rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getDate(5));
            }
        } catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("co loi");
        }
        return null;
    }

    public void singup(String user, String pass, String email) {
        String query = "insert into Account\n"
                + "values(?,?,0,0,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, email);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }

    public void deleteInvoiceByAccountId(String id) {
        String query = "delete from Invoice\n"
                + "where [accountID] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteTongChiTieuBanHangByUserID(String id) {
        String query = "delete from TongChiTieuBanHang\n"
                + "where [userID] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    
    public void deleteProduct(String pid) {
        String query = "delete from Product\n"
                + "where [id] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteProductBySellID(String id) {
        String query = "delete from Product\n"
                + "where [sell_ID] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteGioHangBymaAccount(int maAccount) {
        String query = "delete from GioHang \r\n"
        		+ "where [maAccount]=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maAccount);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteCartByProductID(String productID) {
        String query = "delete from Cart where [productID]=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteSoLuongDaBanByProductID(String productID) {
        String query = "delete from SoLuongDaBan where [productID]=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteReviewByProductID(String productID) {
        String query = "delete from Review where [productID] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteReviewByAccountID(String id) {
        String query = "delete from Review where [accountID] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteAccount(String id) {
        String query = "delete from Account where uID= ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteSupplier(String idSupplier) {
        String query = "delete from Supplier\r\n"
        		+ "where idSupplier=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, idSupplier);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void deleteGioHang(int maXe) {
        String query = "delete from GioHang where maXe = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maXe);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }

    public void insertProduct(String name, String image, String price,
            String title, String description, String category, int sid, String model, String color, String delivery, String image2, String image3, String image4) {
        String query = "insert Product([name],[image],[price],[title],\r\n"
        		+ "[description],[cateID],[sell_ID],[model],\r\n"
        		+ "[color],[delivery],[image2],[image3],[image4])\r\n"
        		+ "values(N'"+name+"','"+image+"','"+price+"',N'"+title+"',N'"+description+"','"+category+"','"+sid+"',N'"+model+"',N'"+color+"',N'"+delivery+"','"+image2+"','"+image3+"','"+image4+"')";
        try {
        	
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
			/*
			 * ps.setString(1, name); ps.setString(2, image); ps.setString(3, price);
			 * ps.setString(4, title); ps.setString(5, description); ps.setString(6,
			 * category); ps.setInt(7, sid); ps.setString(8, model); ps.setString(9, color);
			 * ps.setString(10, delivery); ps.setString(11, image2); ps.setString(12,
			 * image3); ps.setString(13, image4);
			 */
            ps.executeUpdate();
           
        } catch (Exception e) {
        	
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void insertAccount(String user, String pass, String isSell,
    		String isAdmin, String email) {
        String query = "insert Account([user], pass, isSell, isAdmin, email)\r\n"
        		+ "values(?,?,?,?,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, isSell);
            ps.setString(4, isAdmin);
            ps.setString(5, email);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void insertTongChiTieuMuaHang(int maAccount, double tongChiTieu) {
        String query = "insert TongChiTieuMuaHang(maAccount,TongChiTieu)\r\n"
        		+ "values(?,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maAccount);
            ps.setDouble(2, tongChiTieu);;
        
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void insertSoLuongDaBan(int maXe, int soLuongDaBan) {
        String query = "insert SoLuongXeDaBan(maXe,soLuongDaBan)\r\n"
        		+ "values(?,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maXe);
            ps.setInt(2, soLuongDaBan);
           
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void insertSupplier(String nameSupplier, String phoneSupplier, String emailSupplier, String addressSupplier, String cateID) {
        String query = "insert Supplier(nameSupplier, phoneSupplier, emailSupplier, addressSupplier, cateID) \r\n"
        		+ "values(?,?,?,?,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, nameSupplier);
            ps.setString(2, phoneSupplier);
            ps.setString(3, emailSupplier);
            ps.setString(4, addressSupplier);
            ps.setString(5, cateID);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }
  
    public void insertFeedBack(int maAccount, int maXe, String noiDung) {
        String query = "insert FeedBack(maAccount, maXe, noiDung, ngayDanhGia)\r\n"
        		+ "values(?,?,?,?)";

        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maAccount);
            ps.setInt(2, maXe);
            ps.setString(3, noiDung);
            ps.setDate(4,getCurrentDate());
            ps.executeUpdate();
           
        } catch (Exception e) {	
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void insertHoaDon(int maAccount, double tongTien) {
        String query = "insert HoaDon(maAccount,tongTien,ngayThanhToan)\r\n"
        		+ "values(?,?,?)";

        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maAccount);
            ps.setDouble(2, tongTien);
            ps.setDate(3,getCurrentDate());
            ps.executeUpdate();
           
        } catch (Exception e) {	
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void insertGioHang(int maAccount, int maXe, int soLuong) {
        String query = "insert GioHang(maAccount, maXe, soLuong)\r\n"
        		+ "values(?,?,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maAccount);
            ps.setInt(2, maXe);
            ps.setInt(3, soLuong);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }

    public void editProduct(String ptenXe, String phinhAnh1, String pgiaTien, String ptitle, 
    		String pgioiThieu, String pdanhMuc, 
    		String pkhoiLuong, String pdaixRongxCao, 
    		String pdungTichXiLanh, String ptiSoNen, String pdungTichBinhXang, 
    		String phinhAnh2, String phinhAnh3, String phinhAnh4, String pmaXe) {
        String query = "update XeMay\r\n"
        		+ "set [tenXe] = ?,\r\n"
        		+ "[hinhAnh1] = ?,\r\n"
        		+ "giaTien = ?,\r\n"
        		+ "title = ?,\r\n"
        		+ "[gioiThieu] = ?,\r\n"
        		+ "maDanhMuc = ?,\r\n"
        		+ "khoiLuong = ?,\r\n"
        		+ "daiRongCao = ?,\r\n"
        		+ "dungTichXiLanh = ?,\r\n"
        		+ "tiSoNen = ?,\r\n"
        		+ "dungTichBinhXang = ?,\r\n"
        		+ "hinhAnh2 =?,\r\n"
        		+ "hinhAnh3 =?,\r\n"
        		+ "hinhAnh4 =?\r\n"
        		+ "where [maXe] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, ptenXe);
            ps.setString(2, phinhAnh1);
            ps.setString(3, pgiaTien);
            ps.setString(4, ptitle);
            ps.setString(5, pgioiThieu);
            ps.setString(6, pdanhMuc);
            ps.setString(7, pkhoiLuong);
            ps.setString(8, pdaixRongxCao);
            ps.setString(9, pdungTichXiLanh);
            ps.setString(10, ptiSoNen);
            ps.setString(11, pdungTichBinhXang);
            ps.setString(12, phinhAnh2);
            ps.setString(13, phinhAnh3);
            ps.setString(14, phinhAnh4);
            ps.setString(15, pmaXe);
            ps.executeUpdate();
           
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    
    public void editProfile(String username, String password, String email, int maAccount) {
        String query = "update Account set [username]=?,\r\n"
        		+ "[password]=?,\r\n"
        		+ "[email]=?\r\n"
        		+ "where [maAccount] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setInt(4, maAccount);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void editTongChiTieu(int maAccount, double totalMoneyVAT) {
        String query = "exec dbo.proc_CapNhatTongChiTieu ?,?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, maAccount);
            ps.setDouble(2, totalMoneyVAT);
          
            ps.executeUpdate();
            
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void editSoLuongDaBan(int productID, int soLuongBanThem) {
        String query = "exec dbo.proc_CapNhatSoLuongXeDaBan ?,?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ps.setInt(2, soLuongBanThem);
          
            ps.executeUpdate();
            
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
//    public void editTongBanHang(int sell_ID, double tongTienBanHangThem) {
//        String query = "exec dbo.proc_CapNhatTongBanHang ?,?";
//        try {
//            conn = new DBContext().getConnection();//mo ket noi voi sql
//            ps = conn.prepareStatement(query);
//            ps.setInt(1, sell_ID);
//            ps.setDouble(2, tongTienBanHangThem);
//          
//            ps.executeUpdate();
//            
//        } catch (Exception e) {
//        	e.printStackTrace();
//            System.out.println("Có lỗi");
//        }
//    }
    
    public void editsoLuongGioHang(int maAccount, int maXe, int soLuong) {
        String query = "update GioHang set [soLuong]=?\r\n"
        		+ "where [maAccount]=? and [maXe]=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, soLuong);
            ps.setInt(2, maAccount);
            ps.setInt(3, maXe);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }
    
    public void editsoLuongGioHang(int maAccount, int maXe, int soLuong) {
        String query = "update GioHang set [soLuong]=?\r\n"
        		+ "where [maAccount]=? and [maXe]=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, soLuong);
            ps.setInt(2, maAccount);
            ps.setInt(3, maXe);
            ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Có lỗi");
        }
    }

   public static void main(String[] args) {
        DAO dao = new DAO();
//        List<Review> list = 
//        	dao.insertProduct("Giày Bóng Đá Nam Bitis Hunter Football","https://product.hstatic.net/1000230642/product/02400vag__1__5d559f914caf4864ad99a37c18cc1a1b_1024x1024.jpg",
//        					"535","Giày Bóng Đá Nam Biti Hunter Football","Với thiết kế năng động, Giày bóng đá Biti’s Hunter được tung ra với 5 màu sắc nổi bật tạo điểm nhấn trên sân đấu.",
//        					"3",1,"G39","Yellow","Ho Chi Minh","https://product.hstatic.net/1000230642/product/02400vag__3__3a83e45335054285a27fba37cafb23c1_1024x1024.jpg",
//        					"https://product.hstatic.net/1000230642/product/02400vag__4__d3693ef3babe4fc3a2908d8eb2df6e3b_1024x1024.jpg","https://product.hstatic.net/1000230642/product/02400vag__4__d3693ef3babe4fc3a2908d8eb2df6e3b_1024x1024.jpg");
//        dao.editProduct("Giay chay du lich 2","https://giaygiare.vn/upload/sanpham/nike-sb-dunk-low-eire-net-deep-orange.jpg","301","title 3",
//       		"desciption desciption 3", "1", "G66", "Blue", "Ho Chi Minh", "https://giaygiare.vn/upload/sanpham/nike-sb-dunk-low-eire-net-deep-orange.jpg",
//       		"https://giaygiare.vn/upload/sanpham/nike-sb-dunk-low-eire-net-deep-orange.jpg",
//        		"https://giaygiare.vn/upload/sanpham/nike-sb-dunk-low-eire-net-deep-orange.jpg", "3");

//        List<Invoice> list = dao.searchByNgayXuat("2021-11-20");
//        for (Invoice o : list) 
//        { 
//        	System.out.println(o); 
//        }
//      int s = dao.checkAccountAdmin(1);
//      System.out.println(s);
//      System.out.println("da chay xong");

		/*
		 * for (Review o : list) { System.out.println(o); }
		 */
   }

}
