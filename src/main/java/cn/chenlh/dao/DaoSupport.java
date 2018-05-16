package cn.chenlh.dao;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import cn.chenlh.common.ConfigInfo;
import cn.chenlh.common.LogUtil;


public abstract class DaoSupport {

	
	protected Connection getConnection(){
		try {
			Class.forName(ConfigInfo.driver);
			return DriverManager.getConnection(ConfigInfo.url, ConfigInfo.username, ConfigInfo.password);
		} catch (Exception e) {
			LogUtil.i("连接数据库失败！");
			e.printStackTrace();
		}
		return null;
	}
	
	
	protected <T> List<T> getEntityList(String sql, Class<T> clazz, Object... args){
		List<T> list = new ArrayList<T>();
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			prepareStatement = connection.prepareStatement(sql);
			
			int len = args.length;
			for(int i=0; i<len; i++){
				prepareStatement.setObject(i+1, args[i]);
			}
			resultSet = prepareStatement.executeQuery();
			Field[] fields = clazz.getDeclaredFields();
			while (resultSet.next()) {
				T obj = clazz.newInstance(); 
				for(Field f : fields){
					f.setAccessible(true);
					String name = f.getName();
					Object value = resultSet.getObject(name);
					f.set(obj, value);
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(resultSet);
			close(prepareStatement);
			close(connection);
		}
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> getColumnList(String sql, Class<T> clazz, Object... args) {
		List<T> list = new ArrayList<T>();
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			prepareStatement = connection.prepareStatement(sql);
			
			int len = args.length;
			for(int i=0; i<len; i++){
				prepareStatement.setObject(i+1, args[i]);
			}
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				T obj = (T) resultSet.getObject(1);
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(resultSet);
			close(prepareStatement);
			close(connection);
		}
		return list;
	}
	
	
	protected  List<Map<String, Object>> getMapList(String sql, Object... args){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			prepareStatement = connection.prepareStatement(sql);
			
			int len = args.length;
			for(int i=0; i<len; i++){
				prepareStatement.setObject(i+1, args[i]);
			}
			resultSet = prepareStatement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (resultSet.next()) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				for(int i=1; i<=columnCount; i++){
					String columnName = metaData.getColumnName(i);
					Object columnValue = resultSet.getObject(i);
					map.put(columnName, columnValue);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(resultSet);
			close(prepareStatement);
			close(connection);
		}
		return list;
	}
	
	protected  Map<String, Object> getMap(String sql, Object... args){
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			prepareStatement = connection.prepareStatement(sql);
			
			int len = args.length;
			for(int i=0; i<len; i++){
				prepareStatement.setObject(i+1, args[i]);
			}
			resultSet = prepareStatement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (resultSet.next()) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				for(int i=1; i<=columnCount; i++){
					String columnName = metaData.getColumnName(i);
					Object columnValue = resultSet.getObject(i);
					map.put(columnName, columnValue);
				}
				return map;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(resultSet);
			close(prepareStatement);
			close(connection);
		}
		return new HashMap<String, Object>();
	}
	
	protected void close(Connection connection){
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	protected void close(PreparedStatement prepareStatement){
		if(prepareStatement!=null){
			try {
				prepareStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	protected void close(ResultSet resultSet){
		if(resultSet!=null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
