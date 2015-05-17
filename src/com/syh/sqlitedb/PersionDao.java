package com.syh.sqlitedb;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PersionDao {
	private PersionSqliteDB persionSqliteDB;
    public  final String TGA ="PersionDao";
	public PersionDao(Context context) {
		this.persionSqliteDB = new PersionSqliteDB(context);
	}

	/**
	 * 向数据库中添加数据 Tile:add
	 * 
	 * @param name 名字
	 * @param number 电话号 void
	 */
	public void add(String name, String number) {
		SQLiteDatabase db = persionSqliteDB.getWritableDatabase();
		db.execSQL("insert into persion (name,number) values(?,?)",
				new Object[] { name, number });
		Log.d(TGA,"--add--");
		db.close();
	}

	/**
	 * 查询记录是否存在 Tile:findByName
	 * 
	 * @param name 名字 void
	 */
	public boolean find(String name) {
		boolean result = false;
		SQLiteDatabase db = persionSqliteDB.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from persion where name = ?",
				new String[] { name });
		result = cursor.moveToNext();
		cursor.close();
		db.close();
		return result;
	}

	/**
	 * 修改一条记录 Tile:update
	 * 
	 * @param name 需要更改人的姓名
	 * @param newNumber 新的电话号码 void
	 */
	public void update(String name, String newnumber) {
		SQLiteDatabase db = persionSqliteDB.getWritableDatabase();
		db.execSQL("update persion set number=? where name= ?",new Object[] { newnumber, name });
		Log.d(TGA,"--update--");
		db.close();
	}
	/**
	 * 删除一条记录
	 *Tile:delete
	 * @param name 要删除人的姓名
	 * void
	 */
	public void delete(String name){
		SQLiteDatabase db = persionSqliteDB.getWritableDatabase();
		db.execSQL("delete from persion where name = ?", new String[]{name});
		Log.d(TGA,"--delete--");
		db.close();
	}
	/**
	 * 查询所有数据
	 *Tile:getAll
	 * @return
	 * List<Persion>  以链表形式返回数据
	 */
	public List<Persion> getAll(){
		List<Persion> persionList = new ArrayList<Persion>();
		SQLiteDatabase db = persionSqliteDB.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from persion", null);
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String number = cursor.getString(cursor.getColumnIndex("number"));
			Persion persion = new Persion(id, name, number);
			persionList.add(persion);
			Log.d(TGA,"--Query--");
		}
		cursor.close();
		db.close();
		return persionList;
	}
}
