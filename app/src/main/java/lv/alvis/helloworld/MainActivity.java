package lv.alvis.helloworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList selectedItems = new ArrayList();

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
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        Resources res = getResources();
                                        String[] studentsArray = res.getStringArray(R.array.students_array);

                                        if (isChecked) {
                                            selectedItems.add(which);
                                            Toast.makeText(MainActivity.this, "student selected " + studentsArray[which], Toast.LENGTH_SHORT).show();
                                        } else if (selectedItems.contains(which)) {
                                            selectedItems.remove(Integer.valueOf(which));
                                            Toast.makeText(MainActivity.this, "student removed " + studentsArray[which], Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(MainActivity.this, "You clicked OK", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(MainActivity.this, "You closed dialog", Toast.LENGTH_SHORT).show();
                            }
                        });

                builder.create();
                builder.show();
            }
        });
    }
}