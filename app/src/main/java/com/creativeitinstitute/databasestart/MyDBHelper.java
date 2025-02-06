package com.creativeitinstitute.databasestart;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import java.util.ArrayList;
public class MyDBHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ContactDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CONTACT = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NO = "phone_no";
    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
     //create the contacts table
        String CREATE_CONTACTS_TABLE ="CREATE TABLE " +TABLE_CONTACT + "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME
                + "TEXT," + KEY_PHONE_NO + " TEXT" + ")" ;
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.d("DatabaseOperation", "Table created successfully");
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     * <p>
     * <em>Important:</em> You should NOT modify an existing migration step from version X to X+1
     * once a build has been released containing that migration step.  If a migration step has an
     * error and it runs on a device, the step will NOT re-run itself in the future if a fix is made
     * to the migration step.</p>
     * <p>For example, suppose a migration step renames a database column from {@code foo} to
     * {@code bar} when the name should have been {@code baz}.  If that migration step is released
     * in a build and runs on a user's device, the column will be renamed to {@code bar}.  If the
     * developer subsequently edits this same migration step to change the name to {@code baz} as
     * intended, the user devices which have already run this step will still have the name
     * {@code bar}.  Instead, a NEW migration step should be created to correct the error and rename
     * {@code bar} to {@code baz}, ensuring the error is corrected on devices which have already run
     * the migration step with the error.</p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
       onCreate(db);
    }
    public  void addContact (String name , String phone_no) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues() ;
        values.put(KEY_NAME , name);
        values.put(KEY_PHONE_NO , phone_no) ;
        try {
            long result = db.insert(TABLE_CONTACT, null, values);
            if (result ==-1) {
                Log.e("DatabaseError", "Failed to insert contact: "
                        + name);
            } else {
                Log.d("DatabaseOperation", "Contact added: " + name
                        + ", " + phone_no);
            }
        } catch (Exception e) {
            Log.e("DatabaseError", "Error inserting contact: " +
                    name, e);
        }
}
    public ArrayList<ContactModel> fetchContact() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACT,
                null);
        ArrayList<ContactModel> arrayContact = new ArrayList<>();
        while (cursor.moveToNext()) {
            ContactModel model = new ContactModel();
            model.id = cursor.getInt(0);
            model.name = cursor.getString(1);
            model.contact = cursor.getString(2);
            arrayContact.add(model);
        }
        return arrayContact;
    }



}
