package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.Car;
import util.MySQLHelper;

public class CarDAO {
	public boolean insert(Car car){
		boolean result = false;
		String sql = "insert into car(brand, model, color, price) values(?, ?, ?, ?)";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, car.getBrand());
			ps.setString(2, car.getModel());
			ps.setString(3, car.getColor());
			ps.setInt(4, car.getPrice());
			
			int row = ps.executeUpdate();
			if(row > 0){
				result = true;
			}
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;		
	}
	public boolean update(Car car){
		boolean result = false;
		String sql = "update car set brand = ?, model = ?, color = ?, price = ? "
				+ "where id = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, car.getBrand());
			ps.setString(2, car.getModel());
			ps.setString(3, car.getColor());
			ps.setInt(4, car.getPrice());
			ps.setInt(5, car.getId());
			
			int row = ps.executeUpdate();
			if(row > 0){
				result = true;
			}
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return result;		
	}
	public boolean delete(int id){
		boolean result = false;
		String sql = "delete from car where id = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setInt(1, id);			
			
			int row = ps.executeUpdate();
			if(row > 0){
				result = true;
			}
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return result;		
	}
	public Car selectById(int id){
		Car car = new Car();
		String sql = "select * from car where id = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				car.setId(rs.getInt("id"));
				car.setBrand(rs.getString("brand"));
				car.setModel(rs.getString("model"));
				car.setColor(rs.getString("color"));
				car.setPrice(rs.getInt("price"));					
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return car;		
	}
	public List<Car> selectByBrand(String brand){
		List<Car> cars = new ArrayList<Car>();		
		String sql = "select * from car where brand = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, brand);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Car car = new Car();
				car.setId(rs.getInt("id"));
				car.setBrand(rs.getString("brand"));
				car.setModel(rs.getString("model"));
				car.setColor(rs.getString("color"));
				car.setPrice(rs.getInt("price"));	
				
				cars.add(car);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cars;		
	}
	public Vector selectAll(){
		Vector cars = new Vector();	
		String sql = "select * from car";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);			
			
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()){
				Vector car = new Vector();
				for(int i=1; i<=rsmd.getColumnCount(); i++){
					car.add(rs.getString(i));
				}				
				cars.add(car);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cars;		
	}
	
	public Vector selectCarByBrand(String brand){
		Vector cars = new Vector();	
		String sql = "select * from car where brand = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, brand);
			
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while(rs.next()){
				Vector car = new Vector();
				for(int i=1; i<=rsmd.getColumnCount(); i++){
					car.add(rs.getString(i));
				}
				cars.add(car);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cars;		
	}
}








