package edu.sm.dao;

import edu.sm.dto.Csv;
import edu.sm.dto.CsvData;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class CsvDataDAO implements Dao<Integer, Csv> {

    // CSV 데이터 삽입 메서드
    @Override
    public Csv insert(Csv data, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.insertCsvData);
            ps.setInt(1, data.getSerialNumber());
            ps.setString(2, data.getBusinessType());
            ps.setString(3, data.getBusinessName());
            ps.setString(4, data.getAddressRoad());
            ps.setString(5, data.getAddressJibun());
            ps.setString(6, data.getDataDate());
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return data;
    }

    @Override
    public Csv update(Csv csv, Connection con) throws Exception {
        return null;
    }

    // CSV 데이터 업데이트 메서드
    @Override
    public CsvData update(CsvData data, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateCsvData);
            ps.setString(1, data.getBusinessType());
            ps.setString(2, data.getBusinessName());
            ps.setString(3, data.getAddressRoad());
            ps.setString(4, data.getAddressJibun());
            ps.setString(5, data.getDataDate());
            ps.setInt(6, data.getSerialNumber());  // Where 조건으로 ID 사용
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return data;
    }

    // CSV 데이터 삭제 메서드
    @Override
    public Boolean delete(Integer id, Connection con) throws Exception {
        boolean flag = false;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.deleteCsvData);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if (result == 1) {
                flag = true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return flag;
    }

    // 특정 ID에 대한 CSV 데이터 조회 메서드
    @Override
    public CsvData select(Integer id, Connection con) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        CsvData data = null;
        try {
            ps = con.prepareStatement(Sql.selectOneCsvData);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                data = new CsvData(
                        rs.getInt("serialNumber"),
                        rs.getString("businessType"),
                        rs.getString("businessName"),
                        rs.getString("addressRoad"),
                        rs.getString("addressJibun"),
                        rs.getString("dataDate")
                );
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return data;
    }

    // 모든 CSV 데이터를 조회하는 메서드
    @Override
    public List<CsvData> select(Connection con) throws Exception {
        List<CsvData> dataList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Sql.selectAllCsvData);
            rs = ps.executeQuery();
            while (rs.next()) {
                dataList.add(new CsvData(
                        rs.getInt("serialNumber"),
                        rs.getString("businessType"),
                        rs.getString("businessName"),
                        rs.getString("addressRoad"),
                        rs.getString("addressJibun"),
                        rs.getString("dataDate")
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return dataList;
    }

    // 특정 고객 ID로 데이터를 조회하는 메서드 (예시)
    public List<CsvData> selectByCustomerId(Integer customerId, Connection con) throws Exception {
        List<CsvData> dataList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Sql.selectByCustomerId);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                dataList.add(new CsvData(
                        rs.getInt("serialNumber"),
                        rs.getString("businessType"),
                        rs.getString("businessName"),
                        rs.getString("addressRoad"),
                        rs.getString("addressJibun"),
                        rs.getString("dataDate")
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return dataList;
    }
}
