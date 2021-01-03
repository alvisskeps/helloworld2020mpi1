package lv.alvis.helloworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList selectedItems = new ArrayList();
    String[] yourArray = getResources().getStringArray(R.array.students_array);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goToSecond = (Button) findViewById(R.id.go_to_second);

        goToSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        Button openDialog = (Button) findViewById(R.id.open_dialog);

        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Dialog title")
                        .setMultiChoiceItems(R.array.students_array, null,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which,
                                                        boolean isChecked) {
                                        if (isChecked) {
                                            selectedItems.add(which);
                                            Toast.makeText(MainActivity.this, "student selected " + yourArray[which], Toast.LENGTH_SHORT).show();
                                        } else if (selectedItems.contains(which)) {
                                            selectedItems.remove(Integer.valueOf(which));
                                            Toast.makeText(MainActivity.this, "student removed " + yourArray[which], Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // FIRE ZE MISSILES!
                            }
                        })
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });

                builder.create();
                builder.show();
            }
        });
    }
}