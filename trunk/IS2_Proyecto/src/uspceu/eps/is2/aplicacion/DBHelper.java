package uspceu.eps.is2.aplicacion;

import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class DBHelper {
	 
	private static final String TAG = "DBHelper";
 
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "avisos.db";
	private static final String BOOKS_TABLE_NAME = "avisos";
 
	private Context context;
	private SQLiteDatabase db;
	private MyDBOpenHelper openHelper;
 
	public DBHelper(Context context) {
		this.context = context;
		this.openHelper = new MyDBOpenHelper(this.context);
	}
 
	public DBHelper open(){
		this.db = openHelper.getWritableDatabase();		
		return this;
	}
 
	public void close() {
		this.db.close();
	}
 
	public static final class Libros implements BaseColumns {
private Avisos() {}
		
		public static final String nombreAviso = "naviso";
		public static final String tipoAviso = "taviso";
		public static final String descripcionAviso = "daviso";
		public static final int calificacion = 0;
		public static final int votaciones = 0;
		public static final Usuario usuario ;
		public static final Date fechacreacion;
	}
 
	private static class MyDBOpenHelper extends SQLiteOpenHelper {
 
		MyDBOpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
 
		@Override
		public void onCreate(SQLiteDatabase db) {
		    db.execSQL("CREATE TABLE " + BOOKS_TABLE_NAME + " ("
			    + Libros._ID + " INTEGER PRIMARY KEY,"
			    + Libros.AUTOR + " TEXT,"
			    + Libros.TITULO + " TEXT,"
			    + Libros.RESUMEN + " TEXT,"
			    + Libros.EDITORIAL + " TEXT,"
			    + Libros.ANYO + " INTEGER"
			    + ");");
			}
 
			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		    Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
			    + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS "+BOOKS_TABLE_NAME);
		    onCreate(db);
		}
	}
 
	public Libro selectBook(long id) {
		Libro libro = null;
		Cursor cursor = db.query(BOOKS_TABLE_NAME, 
				null, Libros._ID+"=?", new String[] {Long.toString(id)}, 
				null, null, null);
		cursor.moveToFirst();
		libro = new Libro(cursor.getLong(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), 
				cursor.getString(4), cursor.getInt(5));
		return libro;
	}
 
	public ArrayList<Libro> selectAllBooks() {
		ArrayList<Libro> list = new ArrayList<Libro>();
		Cursor cursor = this.db.query(BOOKS_TABLE_NAME, 
				null, null, null, null, null, Libros.TITULO+" ASC");
		if (cursor.moveToFirst()) {
			do {
				Libro libro = new Libro(cursor.getLong(0), cursor.getString(1),
						cursor.getString(2), cursor.getString(3), 
						cursor.getString(4), cursor.getInt(5));
 
				list.add(libro);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return list;
	}
 
	public long insertBook(Libro libro) {
		ContentValues values = new ContentValues();
		values.put(Libros.AUTOR, libro.getAutor());        
		values.put(Libros.TITULO, libro.getTitulo());
		values.put(Libros.RESUMEN, libro.getResumen());
		values.put(Libros.EDITORIAL, libro.getEditorial());
		values.put(Libros.ANYO, libro.getAnyo());
		long id = db.insert(BOOKS_TABLE_NAME, null, values);
		return id;
	}
 
	public void updateBook(Libro libro) {
		ContentValues values = new ContentValues();
		values.put(Libros.AUTOR, libro.getAutor());
		values.put(Libros.TITULO, libro.getTitulo());
		values.put(Libros.RESUMEN, libro.getResumen());
		values.put(Libros.EDITORIAL, libro.getEditorial());
		values.put(Libros.ANYO, libro.getAnyo());
		db.update(BOOKS_TABLE_NAME, values, Libros._ID+"=?", new String[] {Long.toString(libro.getId())});
	}
 
	public void deleteBook(Libro libro){
		db.delete(BOOKS_TABLE_NAME, Libros._ID+"=?", new String[] {Long.toString(libro.getId())});
	}
 
}
