package edu.orangecoastcollege.cs273.petprotector;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import java.util.ArrayList;

/**
 * DBHelper class to handle SQLite functions within the program
 */
class DBHelper extends SQLiteOpenHelper {

    //TASK 1: DEFINE THE DATABASE VERSION, NAME AND TABLE NAME
    static final String DATABASE_NAME = "PetProtector";
    private static final String DATABASE_TABLE = "Pet";
    private static final int DATABASE_VERSION = 1;


    //TASK 2: DEFINE THE FIELDS (COLUMN NAMES) FOR THE TABLE
    private static final String KEY_FIELD_ID = "_id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_DETAILS = "details";
    private static final String FIELD_IMAGE_URI= "image_uri";
    private static final String FIELD_PHONE = "phone";
    private static final String[] FIELD_ARRAY = {KEY_FIELD_ID, FIELD_NAME, FIELD_DETAILS, FIELD_IMAGE_URI
            ,FIELD_PHONE};


    /**
     * Creates a new database
     * @param context the current activity
     */
    public DBHelper(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates a new table
     * @param database the current database loaded
     */
    @Override
    public void onCreate (SQLiteDatabase database){

        // TODO:  Write code to create the database
        //CREATE TABLE whereToNext
        //(_id INTEGER PRIMARY KEY, name TEXT, population Integer, tuition REAL, rating REAL, image_name TEXT)
        String table = "CREATE TABLE " + DATABASE_TABLE + "("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_NAME + " TEXT, "
                + FIELD_DETAILS + " TEXT, "
                + FIELD_IMAGE_URI + " TEXT, "
                + FIELD_PHONE + " TEXT" + ")";
        database.execSQL(table);

    }

    /**
     * If the new version exist, delete old table, and make new table
     * @param database current database
     * @param oldVersion old version
     * @param newVersion new version
     */
    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {

        // TODO:  Write code to upgrade the database
        if(oldVersion != newVersion)
        {
            String table = "DROP TABLE" + DATABASE_TABLE;
            database.execSQL(table);
            onCreate(database);
        }

    }

    //********** DATABASE OPERATIONS:  ADD, GETALL

    /**
     * Add a pet to the database
     * @param pet the Pet object to be added
     */
    public void addPet(Pet pet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_NAME, pet.getName());
        values.put(FIELD_DETAILS, pet.getDetails());
        values.put(FIELD_PHONE, pet.getPhone());
        values.put(FIELD_IMAGE_URI, pet.getImageURI());

        db.insert(DATABASE_TABLE,null,values);
        db.close();

        // TODO:  Write code to add a College to the database
        // TODO:  Return the id assigned by the database
    }

    /**
     * returns a list with all the database items
     * @return the newly created list
     */
    public ArrayList<Pet> getAllPets() {
        ArrayList<Pet> petList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(DATABASE_TABLE,FIELD_ARRAY,null,null,null,null,null);
        cursor.moveToFirst();


        while (!cursor.isAfterLast())
        {
            Pet newPet =  new Pet(cursor.getInt(0),cursor.getString(1),cursor.getString(2)
                    , cursor.getString(4),cursor.getString(3));

            petList.add(newPet);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();

        // TODO:  Write the code to get all the colleges from the database.

        return petList;
    }







}
