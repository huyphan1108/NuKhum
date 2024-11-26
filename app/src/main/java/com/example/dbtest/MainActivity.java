package com.example.dbtest;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dbManager = new DatabaseManager(this);
        try{
            dbManager.open();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public void btnInsertPressed(View v){
        dbManager.insert("Huy", "Phan", "08/11/2002", "Male", "123456", "phanh4@wit.edu", "Vk Iu");
        dbManager.insert("Huy", "Phan", "08/11/2002", "Male", "123456", "phanh4@wit.edu", "Vk Iu");
        dbManager.insert("Huy", "Phan", "08/11/2002", "Male", "123456", "phanh4@wit.edu", "Vk Iu");
    }

    //Add patient using TextEdit
    /*
    public void btnInsertPressed(View v){
        dbManager.insert(editUserName.getText().toString(), editUserPassword.getText().toString());
    }
     */


    //Print one column
    public void btnFetchOne(View v){
        try (Cursor cursor = dbManager.fetch()) { // Try-with-resources ensures cursor is closed
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String ID = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.ID));
                    String f_name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.F_NAME));

                    Log.i("TEST_TAG"," first name: " + f_name);

                } while (cursor.moveToNext());
            } else {
                Log.w("TEST_TAG", "No data found in database.");
            }
        } catch (Exception e) {
            Log.e("TEST_TAG", "Error while fetching data", e);
        }
    }

    //Print all
    public void btnFetchPressed(View v) {
        try (Cursor cursor = dbManager.fetch()) { // Try-with-resources ensures cursor is closed
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String ID = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.ID));
                    String f_name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.F_NAME));
                    String l_name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.L_NAME));
                    String dob = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DOB));
                    String gender = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.GENDER));
                    String p_number = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PNUMBER));
                    String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.EMAIL));
                    String e_contact = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.E_CONTACT));

                    Log.i("TEST_TAG",
                            "ID: " + ID +
                            " first name: " + f_name +
                            " last name: " + l_name +
                            " date of birth: " + dob +
                            " gender: " + gender +
                            " phone number: " + p_number +
                            " email: " + email +
                            " emergency: " + e_contact);

                } while (cursor.moveToNext());
            } else {
                Log.w("TEST_TAG", "No data found in database.");
            }
        } catch (Exception e) {
            Log.e("TEST_TAG", "Error while fetching data", e);
        }
    }

    /*
    public void btnUpdatePressed(View v){
        dbManager.update(Long.parseLong(editUserID.getText().toString()), editUserName.getText().toString(), editUserPassword.getText().toString());
    }

    public void btnDeletePressed(View v){
        dbManager.delete(Long.parseLong(editUserID.getText().toString()));
    }*/

}