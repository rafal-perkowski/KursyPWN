package app.model;

import java.util.ArrayList;
import java.util.List;

public class Query {
	
	private List<String> selectAllFrom;
	private List<String> selectAllFromWhere;
	private List<String> selectFrom;
	private List<String> insertIntoValues;
	private List<String> updateSet;
	private List<String> updateSetWhere;
	private List<String> deleteFrom;
	private List<String> deleteFromWhere;

	private void queryConfig() {
		
		selectAllFrom.add("SELECT * FROM %t;");
		
		selectAllFromWhere.add("SELECT * FROM %t WHERE %c=%v;");
		selectAllFromWhere.add("SELECT * FROM %t WHERE %c=%v AND %c=%v;");
		
		selectFrom.add("SELECT %c FROM %t WHERE %c=%v;");
		selectFrom.add("SELECT %c,%c FROM %t WHERE %c=%v;");
		selectFrom.add("SELECT %c,%c,%c FROM %t WHERE %c=%v;");
		selectFrom.add("SELECT %c,%c,%c,%c FROM %t WHERE %c=%v;");
		selectFrom.add("SELECT %c,%c,%c,%c,%c FROM %t WHERE %c=%v;");
		selectFrom.add("SELECT %c,%c,%c,%c,%c,%c FROM %t WHERE %c=%v;");
		selectFrom.add("SELECT %c,%c,%c,%c,%c,%c,%c FROM %t WHERE %c=%v;");
		selectFrom.add("SELECT %c,%c,%c,%c,%c,%c,%c,%c FROM %t WHERE %c=%v;");
		selectFrom.add("SELECT %c,%c,%c,%c,%c,%c,%c,%c,%c FROM %t WHERE %c=%v;");
		selectFrom.add("SELECT %c,%c,%c,%c,%c,%c,%c,%c,%c,%c FROM %t WHERE %c=%v;");
		
		insertIntoValues.add("INSERT INTO %t (%c) VALUES (%v);");
		insertIntoValues.add("INSERT INTO %t (%c,%c) VALUES (%v,%v);");
		insertIntoValues.add("INSERT INTO %t (%c,%c,%c) VALUES (%v,%v,%v);");
		insertIntoValues.add("INSERT INTO %t (%c,%c,%c,%c) VALUES (%v,%v,%v,%v);");
		insertIntoValues.add("INSERT INTO %t (%c,%c,%c,%c,%c) VALUES (%v,%v,%v,%v,%v);");
		insertIntoValues.add("INSERT INTO %t (%c,%c,%c,%c,%c,%c) VALUES (%v,%v,%v,%v,%v,%v);");
		insertIntoValues.add("INSERT INTO %t (%c,%c,%c,%c,%c,%c,%c) VALUES (%v,%v,%v,%v,%v,%v,%v);");
		insertIntoValues.add("INSERT INTO %t (%c,%c,%c,%c,%c,%c,%c,%c) VALUES (%v,%v,%v,%v,%v,%v,%v,%v);");
		insertIntoValues.add("INSERT INTO %t (%c,%c,%c,%c,%c,%c,%c,%c,%c) VALUES (%v,%v,%v,%v,%v,%v,%v,%v,%v);");
		insertIntoValues.add("INSERT INTO %t (%c,%c,%c,%c,%c,%c,%c,%c,%c,%c) VALUES (%v,%v,%v,%v,%v,%v,%v,%v,%v,%v);");
		
	    updateSet.add("UPDATE %t SET %c=%v;");
	    
	    updateSetWhere.add("UPDATE %t SET %c=%v WHERE %c=%v;");
	    updateSetWhere.add("UPDATE %t SET %c=%v WHERE %c=%v AND %c=%v;");
	    
	    deleteFrom.add("DELETE FROM %t;");
	    
	    deleteFromWhere.add("DELETE FROM %t WHERE %c=%v;");
	    deleteFromWhere.add("DELETE FROM %t WHERE %c=%v AND %c=%v;");

	}
	
	public Query() {
		super();
		
		this.selectAllFrom = new ArrayList<String>();
		this.selectAllFromWhere = new ArrayList<String>();
		this.selectFrom = new ArrayList<String>();
		this.insertIntoValues = new ArrayList<String>();
		this.updateSet = new ArrayList<String>();
		this.updateSetWhere = new ArrayList<String>();
		this.deleteFrom = new ArrayList<String>();
		this.deleteFromWhere = new ArrayList<String>();
		
		queryConfig();
	}

	public Query(List<String> selectAllFrom, List<String> selectAllFromWhere, List<String> selectFrom,
			List<String> insertIntoValues, List<String> updateSet, List<String> updateSetWhere, List<String> deleteFrom,
			List<String> deleteFromWhere) {
		super();
		
		this.selectAllFrom = selectAllFrom;
		this.selectAllFromWhere = selectAllFromWhere;
		this.selectFrom = selectFrom;
		this.insertIntoValues = insertIntoValues;
		this.updateSet = updateSet;
		this.updateSetWhere = updateSetWhere;
		this.deleteFrom = deleteFrom;
		this.deleteFromWhere = deleteFromWhere;
	}

	public List<String> getSelectAllFrom() {
		return selectAllFrom;
	}

	public void setSelectAllFrom(List<String> selectAllFrom) {
		this.selectAllFrom = selectAllFrom;
	}

	public List<String> getSelectAllFromWhere() {
		return selectAllFromWhere;
	}

	public void setSelectAllFromWhere(List<String> selectAllFromWhere) {
		this.selectAllFromWhere = selectAllFromWhere;
	}

	public List<String> getSelectFrom() {
		return selectFrom;
	}

	public void setSelectFrom(List<String> selectFrom) {
		this.selectFrom = selectFrom;
	}

	public List<String> getInsertIntoValues() {
		return insertIntoValues;
	}

	public void setInsertIntoValues(List<String> insertIntoValues) {
		this.insertIntoValues = insertIntoValues;
	}

	public List<String> getUpdateSet() {
		return updateSet;
	}

	public void setUpdateSet(List<String> updateSet) {
		this.updateSet = updateSet;
	}

	public List<String> getUpdateSetWhere() {
		return updateSetWhere;
	}

	public void setUpdateSetWhere(List<String> updateSetWhere) {
		this.updateSetWhere = updateSetWhere;
	}

	public List<String> getDeleteFrom() {
		return deleteFrom;
	}

	public void setDeleteFrom(List<String> deleteFrom) {
		this.deleteFrom = deleteFrom;
	}

	public List<String> getDeleteFromWhere() {
		return deleteFromWhere;
	}

	public void setDeleteFromWhere(List<String> deleteFromWhere) {
		this.deleteFromWhere = deleteFromWhere;
	}

	@Override
	public String toString() {
		return "Query [selectAllFrom=" + selectAllFrom + ", selectAllFromWhere=" + selectAllFromWhere + ", selectFrom="
				+ selectFrom + ", insertIntoValues=" + insertIntoValues + ", updateSet=" + updateSet
				+ ", updateSetWhere=" + updateSetWhere + ", deleteFrom=" + deleteFrom + ", deleteFromWhere="
				+ deleteFromWhere + "]";
	}
	
	public String genStatement(String sqlString, List<String> sqlList){
		
		boolean flagWhileBool = true;
		int sqlListInt = 0;
		int indexInt = -1;
		
		while(flagWhileBool) {
			indexInt = sqlString.indexOf("%");
			if(indexInt!=-1 && indexInt<(sqlString.length()-1)) {
				if(sqlList.get(sqlListInt) == null) {
					sqlString = sqlString.substring(0, indexInt) + "null" + sqlString.substring(indexInt+2, sqlString.length());
				}else if (sqlString.charAt(indexInt+1) == 'v') {
					sqlString = sqlString.substring(0, indexInt) + "'" + sqlList.get(sqlListInt) + "'" + sqlString.substring(indexInt+2, sqlString.length());
				}
				else {
					sqlString = sqlString.substring(0, indexInt) + sqlList.get(sqlListInt) + sqlString.substring(indexInt+2, sqlString.length());
				}
			}
			else {
				flagWhileBool = false;
			}
			sqlListInt++;
		}
		
		return sqlString;
	}

}
